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
