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
package cn.com.xuxiaowei.redis;

import cn.com.xuxiaowei.configuration.SpringContextConfiguration;
import cn.com.xuxiaowei.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Redis 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringContextConfiguration.class})
@WebAppConfiguration
public class RedisTests {

    @Autowired
    private RedisTemplate<String, User> redisTemplateUser;

    /**
     * 操作字符串 {@link RedisTemplate#opsForValue()}
     *
     * @see RedisTemplate#opsForHash() 操作有序 Hash
     * @see RedisTemplate#opsForList() 操作有序 {@link List}
     * @see RedisTemplate#opsForSet() 操作有序 {@link Set}
     * @see RedisTemplate#opsForZSet() 操作有序 Set
     */
    @Test
    public void opsForValue() {

        String username = "xxw";

        ValueOperations<String, User> valueOperations = redisTemplateUser.opsForValue();

        User user = new User();
        user.setUserId(666L);
        user.setUsername(username);
        user.setPassword("xxw123");

        valueOperations.set(username, user);

        User userRedis = valueOperations.get(username);

        log.debug(String.valueOf(userRedis));
    }


    @Test
    public void jedisLocalhost() {

        // 记录操作次数
        int i = 0;

        try (Jedis jedis = new Jedis("localhost", 6379)) {
            // jedis.auth("密码");

            long start = System.currentTimeMillis();

            while (true) {

                long end = System.currentTimeMillis();

                if (end - start >= 1000) {
                    break;
                } else {
                    i++;

                    jedis.set("test_" + i, i + "");
                }
            }
        }

        log.debug("Redis每秒操作 {} 次", i);
    }

}
