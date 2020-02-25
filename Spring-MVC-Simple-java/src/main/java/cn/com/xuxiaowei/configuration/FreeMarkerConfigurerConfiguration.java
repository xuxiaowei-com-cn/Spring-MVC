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
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import static cn.com.xuxiaowei.util.Constants.UTF_8;

/**
 * {@link FreeMarkerViewResolver} 视图解析器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class FreeMarkerConfigurerConfiguration {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {

        // FreeMarker 配置
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();

        freeMarkerConfigurer.setDefaultEncoding(UTF_8);

        // 视图文件放置目录
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/");

        return freeMarkerConfigurer;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {

        // FreeMarker的模板
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();

        // 设置视图类
        freeMarkerViewResolver.setViewClass(FreeMarkerView.class);

        // 需要解析的视图返回文件名前缀
        freeMarkerViewResolver.setViewNames("ftlh/*");

        // 需要解析的视图文件后缀
        freeMarkerViewResolver.setSuffix(".ftlh");

        // 是否启用缓存
        freeMarkerViewResolver.setCache(false);

        // 设置视图类型
        freeMarkerViewResolver.setContentType("text/html;charset=utf-8");

        return freeMarkerViewResolver;
    }

}
