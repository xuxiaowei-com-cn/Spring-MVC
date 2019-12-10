package cn.com.xuxiaowei.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * Redis 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class RedisConfiguration {

    /**
     * 配置 Lettuce Redis 连接器
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        // 默认地址：localhost
        // 默认端口：6379
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();

        // 默认数据库：0
        redisStandaloneConfiguration.setDatabase(1);

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

}
