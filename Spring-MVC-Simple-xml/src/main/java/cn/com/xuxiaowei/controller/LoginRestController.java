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

import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static cn.com.xuxiaowei.util.Constants.*;

/**
 * 登录 RestController
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
public class LoginRestController {

    /**
     * 保存的请求
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 登录成功
     * <p>
     * 只能使用 {@link RequestMethod#GET}
     *
     * @see WebAttributes
     * @see SavedRequestAwareAuthenticationSuccessHandler#onAuthenticationSuccess(HttpServletRequest, HttpServletResponse, Authentication) 授权后重定向
     * @see LoginController#login(HttpServletRequest, HttpServletResponse, Model)
     * @see RequestMethod#GET
     */
    @GetMapping("/login/success")
    public Map<String, Object> loginSuccess(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = new HashMap<>(4);
        Map<String, Object> data = new HashMap<>(4);
        map.put(DATA, data);

        map.put(CODE, CODE_OK);
        map.put(MSG, "登录成功");

        // 获取登录授权前的 URL
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        String redirectUrl;

        if (savedRequest == null) {
            redirectUrl = "";
        } else {
            redirectUrl = savedRequest.getRedirectUrl();

            requestCache.removeRequest(request, response);
        }

        data.put("redirectUrl", redirectUrl);

        return map;
    }

    /**
     * 登录失败
     *
     * @see RequestMethod#POST
     */
    @PostMapping("/login/fail")
    public Map<String, Object> loginFail(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = new HashMap<>(4);
        Map<String, Object> data = new HashMap<>(4);
        map.put(DATA, data);

        map.put(CODE, CODE_ERR);
        map.put(MSG, "登录失败");

        return map;
    }

}
