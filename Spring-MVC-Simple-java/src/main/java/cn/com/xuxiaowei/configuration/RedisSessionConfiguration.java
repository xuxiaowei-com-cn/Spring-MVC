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

import cn.com.xuxiaowei.util.Constants;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
     * Redis 缓存管理
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate<?, ?> redisTemplate) {

        // 从 RedisTemplate 中获取连接
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();

        // 检查 RedisConnectionFactory 是否为 null
        RedisConnectionFactory redisConnectionFactory = Objects.requireNonNull(connectionFactory);

        // 检查 RedisConnectionFactory 是否为 null
        // 创建新的无锁 RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        // 获取 RedisTemplate 的序列化
        RedisSerializer<?> valueSerializer = redisTemplate.getValueSerializer();

        // 序列化对
        RedisSerializationContext.SerializationPair<?> serializationPair = RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer);

        // 获取默认缓存配置
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

        // 设置序列化
        RedisCacheConfiguration redisCacheConfigurationSerialize = redisCacheConfiguration.serializeValuesWith(serializationPair);

        // 创建并返回 Redis 缓存管理
        return new RedisCacheManager(redisCacheWriter, redisCacheConfigurationSerialize);
    }

    /**
     * 注意：如果要使用注解 {@link Autowired} 管理 {@link RedisTemplate}，
     * 则需要将 {@link RedisTemplate} 的 {@link Bean} 缺省泛型
     */
    @Bean
    @SuppressWarnings("rawtypes")
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        // Helper类简化了 Redis 数据访问代码
        RedisTemplate<Object, Object> template = new RedisTemplate<>();

        // 设置连接工厂。
        template.setConnectionFactory(redisConnectionFactory);

        // 可以使用读写JSON
        Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // ObjectMapper 提供了从基本 POJO（普通旧Java对象）或从通用 JSON 树模型（{@link JsonNode}）读取和写入 JSON 的功能，
        // 以及执行转换的相关功能。
        ObjectMapper objectMapper = new ObjectMapper();

        // 枚举，定义影响Java对象序列化方式的简单开/关功能。
        // 默认情况下启用功能，因此默认情况下日期/时间序列化为时间戳。
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 如果启用，上下文<code> TimeZone </ code>将基本上覆盖任何其他TimeZone信息;如果禁用，则仅在值本身不包含任何TimeZone信息时使用。
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        // 注册使用Jackson核心序列化{@code java.time}对象的功能的类。
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        // 添加序列化
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_FORMAT)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_TIME_FORMAT)));

        // 添加反序列化
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(Constants.DEFAULT_TIME_FORMAT)));

        // 用于注册可以扩展该映射器提供的功能的模块的方法; 例如，通过添加自定义序列化程序和反序列化程序的提供程序。
        objectMapper.registerModule(javaTimeModule);

        objectMapper.registerModule(new ParameterNamesModule());

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 序列化时带全限定名
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 1
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // 2
        template.setKeySerializer(new StringRedisSerializer());

        template.afterPropertiesSet();

        return template;
    }

}
