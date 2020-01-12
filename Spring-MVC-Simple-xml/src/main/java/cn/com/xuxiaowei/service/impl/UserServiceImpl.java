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
package cn.com.xuxiaowei.service.impl;

import cn.com.xuxiaowei.controller.IndexController;
import cn.com.xuxiaowei.entity.User;
import cn.com.xuxiaowei.mapper.UserMapper;
import cn.com.xuxiaowei.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户 Service 服务类实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Log logger = LogFactory.getLog(IndexController.class);

    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名，唯一
     * @return 用户信息，可为空，非 List
     */
    @Override
    public User getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * 保存用户数据
     *
     * @param user 用户信息
     * @return 返回是否保存成功
     */
    @Override
    public boolean save(User user) {
        return userMapper.insert(user) == 1;
    }

    /**
     * 测试 事务
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTransactional() {

        User user1 = new User();
        user1.setUsername("user1");

        User user2 = new User();
        user2.setUsername("user2");

        boolean save1 = save(user1);
        logger.debug("抛异常前：save1：" + save1);

        int i = 1 / 0;

        boolean save2 = save(user2);
        logger.debug("抛异常后：save2：" + save2);

    }

}
