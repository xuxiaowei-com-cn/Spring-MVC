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

import cn.com.xuxiaowei.entity.User;
import cn.com.xuxiaowei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 查询登录用户 服务实现层
 *
 * @author xuxiaowei
 * @see Serializable 用于 Session 共享的序列化
 * @since 0.0.1
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {

    private static final long serialVersionUID = -8516366996649935635L;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 权限
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        String password;

        // 用户名为空
        if (username == null || "".equals(username)) {

            // 用户名，用于比较
            username = UUID.randomUUID().toString();

            // 密码，用于比较（如果用户未空，返回随机密码（不可预测））
            password = UUID.randomUUID().toString();

        } else {

            User byUsername = userService.getByUsername(username);

            // 未查询到用户
            if (byUsername == null) {

                // 密码，用于比较（如果用户未空，返回随机密码（不可预测））
                password = UUID.randomUUID().toString();

            } else {

                password = byUsername.getPassword();

                // 用户默认权限，正式环境请查询数据库
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }

        }

        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }

}
