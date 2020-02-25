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

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户测试 XML Bean 转化
 * <p>
 * 自定义父节点名称
 * 自定义命名空间
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@JacksonXmlRootElement(localName = "User", namespace = "http://www.xuxiaowei.com.cn")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 4191739916188118828L;

    /**
     * 放在父节点上
     */
    @JacksonXmlProperty(isAttribute = true)
    private Long userId;

    /**
     * 使用 CDATA 返回数据
     */
    @JacksonXmlCData
    private String username;

    /**
     * 自定义转化为 XML 时的属性名
     */
    @JacksonXmlProperty(localName = "pw")
    private String password;

}
