package cn.com.xuxiaowei.service;

import cn.com.xuxiaowei.configuration.SpringContextConfiguration;
import cn.com.xuxiaowei.entity.Cron;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * 定时器 服务测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringContextConfiguration.class})
@WebAppConfiguration
public class CronServiceTests {

    @Autowired
    private CronService cronService;

    @Test
    public void selectbyId() {
        Cron cron = cronService.selectbyId(1L);
        log.debug(String.valueOf(cron));
    }

    @Test
    public void selectByDeleted() {
        List<Cron> crons = cronService.selectByDeleted(false);
        log.debug(String.valueOf(crons));
    }

}
