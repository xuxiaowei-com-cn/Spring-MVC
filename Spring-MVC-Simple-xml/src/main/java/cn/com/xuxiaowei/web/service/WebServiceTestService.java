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
package cn.com.xuxiaowei.web.service;

import cn.com.xuxiaowei.entity.Person;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * 测试 WebService 服务类
 * <p>
 * 可使用注解：{@link SOAPBinding#style()} 设置为 {@link Style#RPC}
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@WebService
public interface WebServiceTestService {

    /**
     * 测试接口
     *
     * @param username 测试接口参数
     * @return 返回测试数据
     */
    Person getPerson(@WebParam(name = "username") String username);

}
