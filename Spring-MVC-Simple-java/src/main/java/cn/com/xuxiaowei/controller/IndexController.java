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
package cn.com.xuxiaowei.controller;

import cn.com.xuxiaowei.entity.User;
import cn.com.xuxiaowei.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Index Controller
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Controller
public class IndexController {

    private final static Log logger = LogFactory.getLog(IndexController.class);

    /**
     * 用户 Service 服务
     */
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 默认 Index
     * <p>
     * 使用 {@link ThymeleafViewResolver} 视图解析器
     */
    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

        request.getSession();

        logger.debug(this.getClass() + "debug 日志测试。");

        User user = userService.getByUsername("徐晓伟");

        logger.debug(user);
        logger.info(user);

        log.debug(String.valueOf(user));
        log.info(String.valueOf(user));

        return "templates/index";
    }

    /**
     * {@link ThymeleafViewResolver} 视图解析器解析的页面
     */
    @RequestMapping("/html/index")
    public String indexHtml(HttpServletRequest request, HttpServletResponse response, Model model) {
        return index(request, response, model);
    }

    /**
     * {@link FreeMarkerViewResolver} 视图解析器解析的页面
     */
    @RequestMapping("/ftlh/index")
    public ModelAndView indexFtlh(HttpServletRequest request, HttpServletResponse response, Model model) {
        return new ModelAndView("ftlh/index");
    }

    /**
     * {@link InternalResourceViewResolver} 视图解析器解析的页面
     */
    @RequestMapping("/jsp/index")
    public String indexJsp(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "jsp/index";
    }

}
