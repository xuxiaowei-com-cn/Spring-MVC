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
package cn.com.xuxiaowei.mapper;

import cn.com.xuxiaowei.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户 Mapper 接口
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface UserMapper {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名，唯一
     * @return 用户信息，可为空，非 List
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 根据用户名删除用户
     *
     * @param username 用户名，唯一
     * @return 删除结果，条数
     */
    int deleteByUsername(@Param("username") String username);

    /**
     * 插入用户数据
     *
     * @param user 用户信息
     * @return 返回插入条数
     */
    int insert(@Param("user") User user);

}
