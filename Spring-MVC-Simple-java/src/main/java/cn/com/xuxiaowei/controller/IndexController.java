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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Index Controller
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
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
     * Index
     */
    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

        request.getSession();

        logger.debug(this.getClass() + "debug 日志测试。");

        User user = userService.getByUsername("徐晓伟");

        logger.debug(user);

        // 仅支持 info 及以上等级的日志
        logger.info(user);

        return "index";
    }

}
