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
package cn.com.xuxiaowei.service;

import cn.com.xuxiaowei.entity.User;

/**
 * 用户 Service 服务类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface UserService {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名，唯一
     * @return 用户信息，可为空，非 List
     */
    User getByUsername(String username);

}
