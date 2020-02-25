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
package cn.com.xuxiaowei.mapper;

import cn.com.xuxiaowei.entity.Cron;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 定时器 Mapper 接口
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface CronMapper {

    /**
     * 根据 定制器 ID 查询定时器
     *
     * @param cronId 定时器 ID
     * @return 返回定时器
     */
    Cron selectbyId(@Param("cronId") Long cronId);

    /**
     * 查询未删除的定时器
     *
     * @param deleted 是否删除
     * @return 返回未删除的定时器
     */
    List<Cron> selectByDeleted(@Param("deleted") Boolean deleted);

}
