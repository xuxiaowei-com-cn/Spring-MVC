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
