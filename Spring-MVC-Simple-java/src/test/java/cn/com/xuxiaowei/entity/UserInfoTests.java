package cn.com.xuxiaowei.entity;

import cn.com.xuxiaowei.configuration.SpringContextConfiguration;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * XML Bean 测试类
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringContextConfiguration.class})
@WebAppConfiguration
public class UserInfoTests {

    private XmlMapper xmlMapper = new XmlMapper();

    /**
     * XML 与 Bean 相互转化
     */
    @Test
    public void xmlBean() throws IOException {
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
    public void xmlBeanList() throws IOException {

        List<UserInfo> userInfoList = new ArrayList<UserInfo>();

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
