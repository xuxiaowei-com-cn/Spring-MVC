package cn.com.xuxiaowei.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.HashSet;
import java.util.Set;

import static cn.com.xuxiaowei.util.Constants.CHARACTER_ENCODING;

/**
 * {@link ThymeleafViewResolver} 视图解析器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class ThymeleafViewResolverConfiguration {

    @Bean
    public SpringResourceTemplateResolver springResourceTemplateResolver() {

        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();

        // 视图文件放置目录
        springResourceTemplateResolver.setPrefix("/WEB-INF/");

        // 需要解析的视图文件后缀
        springResourceTemplateResolver.setSuffix(".html");

        // 视图编码
        springResourceTemplateResolver.setCharacterEncoding(CHARACTER_ENCODING);

        // 设置视图解析器解析模板
        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);

        // 是否启用缓存
        springResourceTemplateResolver.setCacheable(false);

        return springResourceTemplateResolver;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringResourceTemplateResolver springResourceTemplateResolver) {

        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();

        // 页面权限
        SpringSecurityDialect springSecurityDialect = new SpringSecurityDialect();

        Set<IDialect> dialects = new HashSet<>();
        dialects.add(springSecurityDialect);

        springTemplateEngine.setAdditionalDialects(dialects);
        springTemplateEngine.setTemplateResolver(springResourceTemplateResolver);
        thymeleafViewResolver.setTemplateEngine(springTemplateEngine);

        // 视图编码
        thymeleafViewResolver.setCharacterEncoding(CHARACTER_ENCODING);

        // 需要解析的视图返回文件名前缀
        thymeleafViewResolver.setViewNames(new String[]{"html/*"});

        return thymeleafViewResolver;
    }

}
