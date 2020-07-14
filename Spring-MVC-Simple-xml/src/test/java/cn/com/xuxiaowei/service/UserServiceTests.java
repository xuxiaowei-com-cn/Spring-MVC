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

import cn.com.xuxiaowei.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * user 服务测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(value = {"classpath:spring-context.xml"})
public class UserServiceTests {

    @Resource
    private UserService userService;

    @Test
    public void getByUsername() {
        User user = userService.getByUsername("徐晓伟");
        log.debug(String.valueOf(user));
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
    public void testTransactional() {
        userService.testTransactional();
    }

}
