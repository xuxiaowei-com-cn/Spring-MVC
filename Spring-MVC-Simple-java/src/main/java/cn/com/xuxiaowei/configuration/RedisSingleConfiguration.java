package cn.com.xuxiaowei.configuration;

import cn.com.xuxiaowei.properties.RedisSingleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Redis 单机版配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class RedisSingleConfiguration {

    /**
     * Redis 属性
     */
    private RedisSingleProperties redisSingleProperties;

    @Autowired
    public void setRedisProperties(RedisSingleProperties redisSingleProperties) {
        this.redisSingleProperties = redisSingleProperties;
    }

    /**
     * 配置 Jedis Redis 连接器
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {

        // hostName：默认值：localhost
        // port：默认值：6379
        // password：默认值：
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

        jedisConnectionFactory.setHostName(redisSingleProperties.getHostName());
        jedisConnectionFactory.setPort(redisSingleProperties.getPort());
        jedisConnectionFactory.setDatabase(redisSingleProperties.getDatabase());

        return jedisConnectionFactory;
    }

}
