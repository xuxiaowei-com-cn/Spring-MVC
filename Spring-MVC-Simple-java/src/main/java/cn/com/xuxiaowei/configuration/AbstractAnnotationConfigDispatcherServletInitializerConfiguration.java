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

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;

/**
 * 注册{@code DispatcherServlet}并使用基于Java的Spring配置
 * <p>
 * web.xml
 * <p>
 * 在Servlet 3.0之前的环境中，只能继承 {@link org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer}
 * 在Servlet 3.0+环境中，可实现 {@link org.springframework.web.WebApplicationInitializer}，二者选一
 *
 * @author xuxiaowei
 * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-servlet-context-hierarchy">Context Hierarchy</a>上下文层次结构
 * @see <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-container-config">Servlet Config</a>Servlet配置
 * @since 0.0.1
 */
public class AbstractAnnotationConfigDispatcherServletInitializerConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 用于“根”应用程序上下文（非Web基础结构）配置。
     *
     * @see super#getRootConfigClasses()
     */
    @Override
    public Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringContextConfiguration.class};
    }

    /**
     * 用于{@code DispatcherServlet}应用程序上下文（Spring MVC基础结构）配置。
     *
     * @see super#getServletConfigClasses()
     */
    @Override
    public Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfigurerConfiguration.class};
    }

    /**
     * 交付 Spring MVC 处理的 URL
     *
     * @see super#getServletMappings()
     */
    @Override
    public String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 注册一个 {@link ContextLoaderListener} 针对给定的servlet上下文。
     *
     * @see super#registerContextLoaderListener(ServletContext)
     */
    @Override
    protected void registerContextLoaderListener(ServletContext servletContext) {
        super.registerContextLoaderListener(servletContext);
    }

}
