package cn.com.xuxiaowei.scheduler;

import cn.com.xuxiaowei.properties.JdbcProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器 测试类
 * <p>
 * 多线程执行
 *
 * @author xuxiaowei
 * @see Scheduled
 * @since 0.0.1
 */
@Slf4j
@Component
@EnableAsync
@EnableScheduling
public class SchedulerTest {

    /**
     * 用于测试 {@link Autowired} 使用注入
     */
    private JdbcProperties jdbcProperties;

    /**
     * 标准注入
     */
    @Autowired
    public void setJdbcProperties(JdbcProperties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
    }

    /**
     * 每 5 秒执行一次
     * <p>
     * Scheduled(cron = "0/5 * * * * *")
     */
    @Async
    public void job() {
        log.info("线程：{}，注入：{}", Thread.currentThread().getName(), jdbcProperties.toString());
    }

}
