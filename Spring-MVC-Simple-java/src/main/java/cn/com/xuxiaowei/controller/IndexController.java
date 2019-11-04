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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Index Controller
 *
 * @author xuxiaowei
 */
@Controller
public class IndexController {

    private final static Log logger = LogFactory.getLog(IndexController.class);

    /**
     * Index
     */
    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {

        if (logger.isDebugEnabled()) {
            logger.debug("Index IndexController debug 日志测试。");
        }

        return "index";
    }

}
