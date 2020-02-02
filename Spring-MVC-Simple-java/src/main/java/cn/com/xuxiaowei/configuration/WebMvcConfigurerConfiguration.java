/*
 * Copyright 2019 the original author or authors.
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

import cn.com.xuxiaowei.handlerinterceptor.IndexHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 启用MVC配置
 * <p>
 * spring-mvc.xml
 *
 * @author xuxiaowei
 * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-stereotype-annotations">@Component and Further Stereotype Annotations</a>@Component、@Service、@Controller、@Repository
 * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-config-enable">Enable MVC Configuration</a>启用MVC配置
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cn.com.xuxiaowei")
public class WebMvcConfigurerConfiguration implements WebMvcConfigurer {

    /**
     * 默认Servlet
     *
     * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-default-servlet-handler">Default Servlet</a>
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

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

    /**
     * 拦截器
     *
     * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-config-interceptors">mvc-config-interceptors</a>
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 拦截页面：/，/index
        // 不拦截页面：/admin/**
        registry.addInterceptor(indexHandlerInterceptor()).addPathPatterns("/", "/index").excludePathPatterns("/admin/**");

    }

    /**
     * 自动将 {@link RequestMapping} 包括 {@link RequestMapping} 的拓展注解 增加 URL 后缀名，并根据后缀名去请求解析数据
     * <p>
     * 要求：
     * 使用某个后缀名时，需要 {@link RequestMapping} 包括 {@link RequestMapping} 的拓展注解 和 下方的 {@link MediaType} 有相同的值，
     * 或者 {@link RequestMapping} 包括 {@link RequestMapping} 的拓展注解不使用 {@link MediaType}
     *
     * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-config-content-negotiation">mvc-config-content-negotiation</a>
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer.mediaType("json", MediaType.APPLICATION_JSON);
        configurer.mediaType("xml", MediaType.APPLICATION_XML);

    }

    /**
     * 将拦截器注册为 {@link Bean}，可在拦截器中使用 {@link Autowired}
     */
    @Bean
    public IndexHandlerInterceptor indexHandlerInterceptor() {
        return new IndexHandlerInterceptor();
    }

}
