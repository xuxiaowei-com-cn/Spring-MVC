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

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

}
