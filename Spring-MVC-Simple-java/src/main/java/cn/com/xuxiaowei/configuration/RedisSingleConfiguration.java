package cn.com.xuxiaowei.configuration;

import cn.com.xuxiaowei.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * Redis 单机版配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class RedisSingleConfiguration {

    /**
     * Redis 属性文件
     */
    private RedisProperties redisProperties;

    @Autowired
    public void setRedisProperties(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    /**
     * 配置 Lettuce Redis 连接器
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        // 默认地址：localhost
        // 默认端口：6379
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                redisProperties.getHostName(), redisProperties.getPort());

        // 默认数据库：0
        redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

}
