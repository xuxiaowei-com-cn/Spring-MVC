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
package cn.com.xuxiaowei.service.impl;

import cn.com.xuxiaowei.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 测试 服务实现层
 *
 * @author xuxiaowei
 * @see Serializable 用于 Session 共享的序列化
 * @since 0.0.1
 */
@Slf4j
@Service
public class TestServiceImpl implements ITestService, Serializable {

    /**
     * 用于 Session 共享的序列化
     */
    private static final long serialVersionUID = -6912053335832096974L;

    /**
     * 测试方法
     *
     * @return 返回测试内容
     */
    @Override
    public String hi() {
        return "你好，已注入成功";
    }

}
