package cn.com.xuxiaowei.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * {@link ThymeleafViewResolver} 视图解析器
 * <p>
 * {@link ThymeleafViewResolverConfiguration} 与 {@link InternalResourceViewResolverConfiguration} 激活一个
 * <p>
 * Configuration
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class ThymeleafViewResolverConfiguration {

    /**
     * Bean
     */
    @Bean
    public SpringResourceTemplateResolver springResourceTemplateResolver() {

        SpringResourceTemplateResolver servletContextTemplateResolver = new SpringResourceTemplateResolver();
        servletContextTemplateResolver.setPrefix("/WEB-INF/templates/");
        servletContextTemplateResolver.setSuffix(".html");
        servletContextTemplateResolver.setCharacterEncoding("UTF-8");
        servletContextTemplateResolver.setOrder(2);
        servletContextTemplateResolver.setTemplateMode(TemplateMode.HTML);
        servletContextTemplateResolver.setCacheable(false);

        return servletContextTemplateResolver;
    }

    /**
     * Bean
     */
    @Bean
    public SpringTemplateEngine springTemplateEngine() {

        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(springResourceTemplateResolver());

        return springTemplateEngine;
    }

    /**
     * Bean
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {

        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(springTemplateEngine());
        thymeleafViewResolver.setCharacterEncoding("UTF-8");

        return thymeleafViewResolver;
    }

}
