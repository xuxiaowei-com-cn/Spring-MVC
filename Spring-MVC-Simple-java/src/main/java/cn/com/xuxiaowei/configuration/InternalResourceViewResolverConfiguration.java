package cn.com.xuxiaowei.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * {@link InternalResourceViewResolver} 视图解析器
 * <p>
 * {@link ThymeleafViewResolverConfiguration} 与 {@link InternalResourceViewResolverConfiguration} 激活一个
 * <p>
 * Configuration
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class InternalResourceViewResolverConfiguration {

    /**
     * 视图解析器
     *
     * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-view-freemarker">FreeMarker</a>
     * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-view-jsp">JSP and JSTL</a>
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();

        internalResourceViewResolver.setPrefix("/");
        internalResourceViewResolver.setSuffix(".html");

        // 是否启用缓存
        internalResourceViewResolver.setCache(false);

        return internalResourceViewResolver;
    }

}
