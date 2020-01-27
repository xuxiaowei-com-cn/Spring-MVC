package cn.com.xuxiaowei.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 加载 Redis 属性文件
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Configuration
@PropertySource(value = {"classpath:redis.properties"})
public class RedisProperties {

    @Value("${redis.hostName}")
    private String hostName;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.password}")
    private String password;


    @Value("${redis.database}")
    private int database;

}
