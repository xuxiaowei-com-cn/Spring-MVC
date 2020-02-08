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
 * @since 0.0.1
 */
public final class Constants {

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
