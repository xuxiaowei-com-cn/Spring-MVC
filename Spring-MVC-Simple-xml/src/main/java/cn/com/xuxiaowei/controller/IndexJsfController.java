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

import cn.com.xuxiaowei.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * JSF Controller
 *
 * @author xuxiaowei
 * @see Serializable 用于 Session 共享的序列化
 * @see ManagedBean  托管的 {@link Bean}，可自定义 {@link Bean} 的名字
 * @see SessionScoped
 * @since 0.0.1
 */
@Slf4j
@Component
@Scope
public class IndexJsfController implements Serializable {

    /**
     * 用于 Session 共享的序列化
     */
    private static final long serialVersionUID = -8377204181936446253L;

    private ITestService testService;

    @Autowired
    public void setTestService(ITestService testService) {
        this.testService = testService;
    }

    public String getIndex() {

        log.debug(testService.hi());

        return this.toString();
    }

    public String getUuid() {
        return String.join("：", "Controller 中的值", UUID.randomUUID().toString());
    }

    public Date getNow() {
        return new Date();
    }

}
