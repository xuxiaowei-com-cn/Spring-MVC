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
package cn.com.xuxiaowei.service;

import cn.com.xuxiaowei.configuration.SpringContextConfiguration;
import cn.com.xuxiaowei.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;

/**
 * user 服务测试类
 *
 * @author xuxiaowei
 * @see <a href="https://spring.io/blog/2014/03/25/spring-mvc-test-with-htmlunit#creating-mockmvc">Creating MockMvc</a>
 * @see <a href="https://spring.io/blog/2014/05/23/preview-spring-security-test-web-security">Preview Spring Security Test: Web Security</a>
 * @since 0.0.1
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringContextConfiguration.class})
@WebAppConfiguration
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void getByUsername() {
        User user = userService.getByUsername("徐晓伟");
        log.debug(String.valueOf(user));
    }

    @Test
    public void removeByUsername() {
        boolean removeByUsername = userService.removeByUsername("徐晓伟");
        log.debug(String.valueOf(removeByUsername));
    }

    @Test
    public void save() {
        User user = new User();
        user.setUsername("xxw" + System.currentTimeMillis());
        user.setPassword(UUID.randomUUID().toString().replace("-", ""));

        boolean save = userService.save(user);

        log.debug(String.valueOf(save));
        log.debug(String.valueOf(user));
    }

    @Test
    public void updateByUserId() {
        User user = new User();
        user.setUserId(8L);
        user.setUsername("xxw" + System.currentTimeMillis());
        user.setPassword(UUID.randomUUID().toString().replace("-", ""));

        User updateByUsername = userService.updateByUserId(user);

        log.debug(String.valueOf(updateByUsername));
        log.debug(String.valueOf(user));
    }


    @Test
    public void testTransactional() {
        userService.testTransactional();
    }

}
