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

import cn.com.xuxiaowei.entity.Cron;
import cn.com.xuxiaowei.mapper.CronMapper;
import cn.com.xuxiaowei.service.CronService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 定时器 服务实现层
 *
 * @author xuxiaowei
 * @see Serializable 用于 Session 共享的序列化
 * @since 0.0.1
 */
@Slf4j
@Service
public class CronServiceImpl implements CronService, Serializable {

    private static final long serialVersionUID = 7819314047491786811L;

    @Resource
    private CronMapper cronMapper;

    /**
     * 根据 定制器 ID 查询定时器
     *
     * @param cronId 定时器 ID
     * @return 返回定时器
     */
    @Override
    public Cron selectbyId(Long cronId) {
        return cronMapper.selectbyId(cronId);
    }

    /**
     * 查询未删除的定时器
     *
     * @param deleted 是否删除
     * @return 返回未删除的定时器
     */
    @Override
    public List<Cron> selectByDeleted(Boolean deleted) {
        return cronMapper.selectByDeleted(deleted);
    }

}
