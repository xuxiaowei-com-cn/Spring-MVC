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
package cn.com.xuxiaowei.util;

import cn.com.xuxiaowei.configuration.WebMvcConfigurerConfiguration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

/**
 * 常量
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public final class Constants {

    /**
     * 编码
     */
    public static final String CHARACTER_ENCODING = "UTF-8";

    /**
     * 登录页面、登录请求
     *
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     */
    public static final String LOGIN = "/login";

    /**
     * 登录请求
     *
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     */
    public static final String LOGIN_JSON = "/login.json";

    /**
     * 登录失败
     *
     * @see #LOGIN_FAIL_JSON
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     */
    public static final String LOGIN_FAIL = "/login/fail";

    /**
     * 登录失败
     *
     * @see #LOGIN_FAIL
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     */
    public static final String LOGIN_FAIL_JSON = "/login/fail.json";

    /**
     * 登录成功
     *
     * @see #LOGIN_SUCCESS_JSON
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     */
    public static final String LOGIN_SUCCESS = "/login/success";

    /**
     * 登录成功
     *
     * @see #LOGIN_SUCCESS
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     */
    public static final String LOGIN_SUCCESS_JSON = "/login/success.json";

    /**
     * 注销登录
     *
     * @see #LOGOUT_JSON
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     */
    public static final String LOGOUT = "/logout";

    /**
     * 注销登录
     *
     * @see #LOGOUT
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     */
    public static final String LOGOUT_JSON = "/logout.json";

    /**
     * 注销登录成功
     *
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     */
    public static final String LOGOUT_SUCCESS = "/logout/success";

    /**
     * 注销登录成功
     *
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     */
    public static final String LOGOUT_SUCCESS_JSON = "/logout/success.json";

    /**
     * Cookie 有效期
     */
    public static final int TOKEN_VALIDITY_SECONDS = 60 * 60;

    /**
     * 记住我参数名
     * <p>
     * Cookie
     */
    public static final String REMEMBER_ME_PARAMETER = "rememberMe";

    /**
     * Cookie Key
     */
    public static final String KEY = "NV!lK12FhRM-Key+NV!lK12FhRM-Key+";

    /**
     * 错误页面
     */
    public static final String ACCESS_DENIED = "accessDenied";

    /**
     * 匿名用户的权限
     */
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    /**
     * 响应 Code
     */
    public static final String CODE = "code";

    /**
     * 正确响应 Code
     */
    public static final int CODE_OK = 0;

    /**
     * 错误响应 Code
     */
    public static final int CODE_ERR = 1;

    /**
     * 响应 Code 说明
     */
    public static final String MSG = "msg";

    /**
     * 日期时间格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 时间格式
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

}
