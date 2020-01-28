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
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 启用MVC配置
 * <p>
 * spring-mvc.xml
 * <p>
 * 在使用 JDK 6 时，推荐使用 {@link WebMvcConfigurer} 的抽象类 {@link WebMvcConfigurerAdapter} 来代替 {@link WebMvcConfigurer}
 *
 * @author xuxiaowei
 * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-stereotype-annotations">@Component and Further Stereotype Annotations</a>@Component、@Service、@Controller、@Repository
 * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-config-enable">Enable MVC Configuration</a>启用MVC配置
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cn.com.xuxiaowei")
public class WebMvcConfigurerConfiguration extends WebMvcConfigurerAdapter {

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
    InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setSuffix(".html");
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
     * 将拦截器注册为 {@link Bean}，可在拦截器中使用 {@link Autowired}
     */
    @Bean
    IndexHandlerInterceptor indexHandlerInterceptor() {
        return new IndexHandlerInterceptor();
    }

}
