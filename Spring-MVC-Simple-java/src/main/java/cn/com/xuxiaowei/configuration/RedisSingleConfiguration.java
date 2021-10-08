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
package cn.com.xuxiaowei.configuration;

import cn.com.xuxiaowei.properties.RedisSingleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * Redis 单机版配置
 * <p>
 * {@link RedisSingleConfiguration} 与 {@link RedisClusterConfiguration} 使用一个
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class RedisSingleConfiguration {

    /**
     * Redis 单机版属性文件
     */
    private RedisSingleProperties redisSingleProperties;

    @Autowired
    public void setRedisProperties(RedisSingleProperties redisSingleProperties) {
        this.redisSingleProperties = redisSingleProperties;
    }

    /**
     * 配置 Lettuce Redis 连接器
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        // 默认地址：localhost
        // 默认端口：6379
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                redisSingleProperties.getHostName(), redisSingleProperties.getPort());

        // 默认数据库：0
        redisStandaloneConfiguration.setDatabase(redisSingleProperties.getDatabase());

        // 密码
        redisStandaloneConfiguration.setPassword(redisSingleProperties.getPassword());

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

}
