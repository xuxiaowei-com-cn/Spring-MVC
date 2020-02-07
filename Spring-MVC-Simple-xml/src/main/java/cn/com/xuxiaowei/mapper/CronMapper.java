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
