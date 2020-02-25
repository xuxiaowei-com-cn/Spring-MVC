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
package cn.com.xuxiaowei.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 加载 Redis 集群属性文件
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Configuration
@PropertySource(value = {"classpath:redis-cluster.properties"})
public class RedisClusterProperties {

    @Value("${redis.host1}")
    private String host1;

    @Value("${redis.port1}")
    private Integer port1;

    @Value("${redis.host2}")
    private String host2;

    @Value("${redis.port2}")
    private Integer port2;

    @Value("${redis.host3}")
    private String host3;

    @Value("${redis.port3}")
    private Integer port3;

    @Value("${redis.host4}")
    private String host4;

    @Value("${redis.port4}")
    private Integer port4;

    @Value("${redis.host5}")
    private String host5;

    @Value("${redis.port5}")
    private Integer port5;

    @Value("${redis.host6}")
    private String host6;

    @Value("${redis.port6}")
    private Integer port6;

    @Value("${redis.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.maxActive}")
    private Integer maxActive;

    @Value("${redis.maxWait}")
    private Integer maxWait;

    @Value("${redis.testOnBorrow}")
    private Boolean testOnBorrow;

}
