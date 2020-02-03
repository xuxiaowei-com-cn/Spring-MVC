package cn.com.xuxiaowei.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import static cn.com.xuxiaowei.util.Constants.CHARACTER_ENCODING;

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

        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setPrefix("/WEB-INF/templates/");
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setCharacterEncoding(CHARACTER_ENCODING);
        springResourceTemplateResolver.setOrder(2);
        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        springResourceTemplateResolver.setCacheable(false);

        return springResourceTemplateResolver;
    }

    /**
     * Bean
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringResourceTemplateResolver springResourceTemplateResolver) {

        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();

        springTemplateEngine.setTemplateResolver(springResourceTemplateResolver);
        thymeleafViewResolver.setTemplateEngine(springTemplateEngine);
        thymeleafViewResolver.setCharacterEncoding(CHARACTER_ENCODING);

        return thymeleafViewResolver;
    }

}
