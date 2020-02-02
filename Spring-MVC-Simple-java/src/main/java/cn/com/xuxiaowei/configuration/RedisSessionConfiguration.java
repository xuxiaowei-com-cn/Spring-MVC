package cn.com.xuxiaowei.configuration;

import cn.com.xuxiaowei.properties.RedisProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 开启 Redis Session 缓存
 * <p>
 * Redis 开启声明缓存支持
 *
 * @author xuxiaowei
 * @see <a href="https://docs.spring.io/spring-session/docs/current/reference/html5/#httpsession-spring-configuration">httpsession-spring-configuration</a>
 * @since 0.0.1
 */
@Configuration
@EnableCaching
@EnableRedisHttpSession
public class RedisSessionConfiguration {

    /**
     * Redis 属性
     */
    private RedisProperties redisProperties;

    @Autowired
    public void setRedisProperties(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    /**
     * 配置 Jedis Redis 连接器
     */
    @Bean
    protected JedisConnectionFactory redisConnectionFactory() {

        // hostName：默认值：localhost
        // port：默认值：6379
        // password：默认值：
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

        jedisConnectionFactory.setHostName(redisProperties.getHostName());
        jedisConnectionFactory.setPort(redisProperties.getPort());
        jedisConnectionFactory.setDatabase(redisProperties.getDatabase());

        return jedisConnectionFactory;
    }

    /**
     *
     */
    @Bean
    protected RedisCacheManager redisCacheManager(RedisTemplate<?, ?> redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    /**
     *
     */
    @Bean
    protected RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        // Helper类简化了 Redis 数据访问代码
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();

        // 设置连接工厂。
        template.setConnectionFactory(redisConnectionFactory);

        // 可以使用读写JSON
        Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

        // ObjectMapper 提供了从基本 POJO（普通旧Java对象）或从通用 JSON 树模型（{@link JsonNode}）读取和写入 JSON 的功能，
        // 以及执行转换的相关功能。
        ObjectMapper objectMapper = new ObjectMapper();

        // 枚举，定义影响Java对象序列化方式的简单开/关功能。
        // 默认情况下启用功能，因此默认情况下日期/时间序列化为时间戳。
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 如果启用，上下文<code> TimeZone </ code>将基本上覆盖任何其他TimeZone信息;如果禁用，则仅在值本身不包含任何TimeZone信息时使用。
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 序列化时带全限定名
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 1
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // 2
        template.setKeySerializer(new StringRedisSerializer());

        template.afterPropertiesSet();

        return template;
    }

}
