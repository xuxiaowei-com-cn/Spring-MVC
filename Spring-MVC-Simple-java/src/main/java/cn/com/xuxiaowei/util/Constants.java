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

import java.nio.charset.Charset;

/**
 * 常量
 *
 * @author xuxiaowei
 * @see org.springframework.http.MediaType spring-web，从 3.0 开始
 * @see org.springframework.util.MimeTypeUtils spring-core，从 4.0 开始
 * @see org.springframework.http.HttpHeaders spring-web，从 3.0 开始
 * @see com.google.common.net.HttpHeaders guava，从 11.0 开始
 * @see javax.servlet.http.HttpServletResponse
 * @see org.springframework.web.context.support.XmlWebApplicationContext spring-web
 * @see org.springframework.context.ApplicationContextAware spring-context，可获取 {@link org.springframework.context.ApplicationContext}，非常量相关
 * @see org.springframework.web.util.JavaScriptUtils spring-web，从 1.1.1 开始，将JavaScript特殊字符转换为转义字符，非常量相关
 * @see org.springframework.web.servlet.view.json.MappingJackson2JsonView spring-webmvc，从 3.1.2 开始
 * @see org.thymeleaf.util.ContentTypeUtils thymeleaf，从 3.0.6 开始
 * @see org.springframework.web.context.WebApplicationContext
 * @since 0.0.1
 */
public final class Constants {

    /**
     * Spring format 使用格式
     *
     * @see String#format(String, Object...)
     */
    public static final String COMMA = "%s,%s";

    /**
     * 编码
     *
     * @see Charset#defaultCharset()
     */
    public static final String UTF_8 = "UTF-8";

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
     * 响应 数据 说明
     */
    public static final String DATA = "data";

    /**
     * 必须先与服务器确认返回的响应是否被更改，然后才能使用该响应来满足后续对同一个网址的请求。
     * 因此，如果存在合适的验证令牌 (ETag)，no-cache 会发起往返通信来验证缓存的响应，如果资源未被更改，可以避免下载。
     *
     * @see com.google.common.net.HttpHeaders#CACHE_CONTROL
     * @see org.springframework.http.HttpHeaders#CACHE_CONTROL
     */
    public static final String NO_CACHE = "no-cache";

    /**
     * 所有内容都不会被缓存到缓存或 Internet 临时文件中
     *
     * @see com.google.common.net.HttpHeaders#CACHE_CONTROL
     * @see org.springframework.http.HttpHeaders#CACHE_CONTROL
     */
    public static final String NO_STORE = "no-store";

    /**
     * @see com.google.common.net.HttpHeaders#CACHE_CONTROL
     * @see org.springframework.http.HttpHeaders#CACHE_CONTROL
     */
    public static final String NO_CACHE_NO_STORE = String.format(COMMA, NO_CACHE, NO_STORE);

    /**
     * Session 中的图片验证码
     */
    public static final String PATCHCA = "patchca";

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
