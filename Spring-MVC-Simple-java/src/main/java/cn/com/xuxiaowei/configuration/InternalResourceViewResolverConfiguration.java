package cn.com.xuxiaowei.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * {@link InternalResourceViewResolver} 视图解析器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class InternalResourceViewResolverConfiguration {

    /**
     * JSP 视图解析器
     *
     * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-view-freemarker">FreeMarker</a>
     * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-view-jsp">JSP and JSTL</a>
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {

        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();

        // 设置视图类
        internalResourceViewResolver.setViewClass(InternalResourceView.class);

        // 视图文件放置目录
        internalResourceViewResolver.setPrefix("/WEB-INF/");

        // 需要解析的视图返回文件名前缀
        internalResourceViewResolver.setViewNames("jsp/*");

        // 需要解析的视图文件后缀
        internalResourceViewResolver.setSuffix(".jsp");

        // 是否启用缓存
        internalResourceViewResolver.setCache(false);

        // 设置视图类型
        internalResourceViewResolver.setContentType(MediaType.TEXT_HTML_VALUE);

        return internalResourceViewResolver;
    }

}
