/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.xuxiaowei.entity;

import cn.com.xuxiaowei.configuration.SpringContextConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * XML Bean 测试类
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringContextConfiguration.class})
@WebAppConfiguration
public class UserInfoTests {

    private XmlMapper xmlMapper = new XmlMapper();

    /**
     * XML 与 Bean 相互转化
     */
    @Test
    public void xmlBean() throws JsonProcessingException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(5L);
        userInfo.setUsername("徐晓伟");
        userInfo.setPassword(UUID.randomUUID().toString().replace("-", ""));

        String writeValueAsString = xmlMapper.writeValueAsString(userInfo);

        log.debug(writeValueAsString);

        UserInfo readValue = xmlMapper.readValue(writeValueAsString, UserInfo.class);

        log.debug(String.valueOf(readValue));
    }

    /**
     * XML 与 List 相互转化
     */
    @Test
    public void xmlBeanList() throws JsonProcessingException {

        List<UserInfo> userInfoList = new ArrayList<>();

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserId(5L);
        userInfo1.setUsername("徐晓伟");
        userInfo1.setPassword(UUID.randomUUID().toString().replace("-", ""));

        userInfoList.add(userInfo1);

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setUserId(2L);
        userInfo2.setUsername("XXW");
        userInfo2.setPassword(UUID.randomUUID().toString().replace("-", ""));

        userInfoList.add(userInfo2);

        String writeValueAsString = xmlMapper.writeValueAsString(userInfoList);

        log.debug(writeValueAsString);

        @SuppressWarnings("all")
        List<UserInfo> readValue = xmlMapper.readValue(writeValueAsString, List.class);

        log.debug(String.valueOf(readValue));
    }

}
