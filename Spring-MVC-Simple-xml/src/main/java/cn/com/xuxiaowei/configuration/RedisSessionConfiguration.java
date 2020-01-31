package cn.com.xuxiaowei.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * 开启 Redis 注解
 *
 * @author xuxiaowei
 * @see <a href="https://docs.spring.io/spring-session/docs/current/reference/html5/#httpsession-spring-configuration">httpsession-spring-configuration</a>
 * @since 0.0.1
 */
@Configuration
@EnableCaching
public class RedisSessionConfiguration {

}
