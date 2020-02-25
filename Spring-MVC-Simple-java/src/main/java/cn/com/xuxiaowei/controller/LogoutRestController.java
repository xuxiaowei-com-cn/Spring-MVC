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
package cn.com.xuxiaowei.controller;

import cn.com.xuxiaowei.configuration.WebMvcConfigurerConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static cn.com.xuxiaowei.util.Constants.*;

/**
 * 注销 RestController
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
public class LogoutRestController {

    /**
     * 注销登录成功
     *
     * @see RequestMethod#GET
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     */
    @GetMapping("/logout/success")
    public Map<String, Object> logoutSuccess(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = new HashMap<String, Object>(4);
        Map<String, Object> data = new HashMap<String, Object>(4);
        map.put(DATA, data);

        map.put(CODE, CODE_OK);
        map.put(MSG, "注销登录成功");

        return map;
    }

}
