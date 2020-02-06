package cn.com.xuxiaowei.service;

import cn.com.xuxiaowei.entity.Cron;

import java.util.List;

/**
 * 定时器 服务层
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public interface CronService {

    /**
     * 根据 定制器 ID 查询定时器
     *
     * @param cronId 定时器 ID
     * @return 返回定时器
     */
    Cron selectbyId(Long cronId);

    /**
     * 查询未删除的定时器
     *
     * @param deleted 是否删除
     * @return 返回未删除的定时器
     */
    List<Cron> selectByDeleted(Boolean deleted);

}
