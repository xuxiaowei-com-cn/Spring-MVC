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
package cn.com.xuxiaowei.web.service.impl;

import cn.com.xuxiaowei.entity.Person;
import cn.com.xuxiaowei.web.service.WebServiceTestService;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 测试 WebService 服务实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class WebServiceTestServiceImpl implements WebServiceTestService {

    /**
     * 自增序列号
     */
    private final AtomicLong counter = new AtomicLong();

    /**
     * 测试接口
     *
     * @param username 测试接口参数
     * @return 返回测试数据
     */
    @Override
    public Person getPerson(String username) {

        Person person = new Person();
        person.setUserId(counter.incrementAndGet());
        person.setUsername(username);
        person.setPassword(UUID.randomUUID().toString());

        return person;
    }

}
