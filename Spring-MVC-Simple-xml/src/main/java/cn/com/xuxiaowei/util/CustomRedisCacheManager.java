package cn.com.xuxiaowei.util;

import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.util.Objects;

/**
 * 自定义 缓存管理器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class CustomRedisCacheManager extends RedisCacheManager {

    /**
     * 初始化 缓存管理器
     */
    public CustomRedisCacheManager(RedisTemplate<?, ?> redisTemplate) {
        super(RedisCacheWriter.nonLockingRedisCacheWriter(Objects.requireNonNull(redisTemplate.getConnectionFactory())), RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer())));
    }

}
