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

import cn.com.xuxiaowei.mapper.MybatisPlusTestMapper;
import cn.com.xuxiaowei.service.ITestService;
import cn.com.xuxiaowei.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 在 Listener 中获取 Bean
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Component
public class SpringServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        log.info("在 Listener 中获取 Bean 开始：");

        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());

        MybatisPlusTestMapper mybatisPlusTestMapper = webApplicationContext.getBean(MybatisPlusTestMapper.class);
        log.info(String.valueOf(mybatisPlusTestMapper));

        ITestService iTestService = webApplicationContext.getBean(ITestService.class);
        log.info(String.valueOf(iTestService));

        // 也可是使用 name 获取，默认：类名首字母小写，参见 Bean 的 @Service
        UserService userService = (UserService) webApplicationContext.getBean("userServiceImpl");
        log.info(String.valueOf(userService));

        log.info("在 Listener 中获取 Bean 结束。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
