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
package cn.com.xuxiaowei.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class Cron implements Serializable {

    private static final long serialVersionUID = 3919106962393408344L;

    /**
     * 定时器主键
     */
    private Long cronId;

    /**
     * 定时器表达式
     * <p>
     * 与 {@link #cronDate} 二选一
     */
    private String expression;

    /**
     * 定时器执行的具体时间
     * <p>
     * 与 {@link #expression} 二选一
     */
    private Date cronDate;

    /**
     * 定时器是否删除，0 未删除，1 删除，默认值未删除 0
     */
    private Double deleted;

}
