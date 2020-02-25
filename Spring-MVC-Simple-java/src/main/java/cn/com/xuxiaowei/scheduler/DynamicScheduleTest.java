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
package cn.com.xuxiaowei.scheduler;

import cn.com.xuxiaowei.entity.Cron;
import cn.com.xuxiaowei.service.CronService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

/**
 * 动态定时器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Configuration
@EnableScheduling
public class DynamicScheduleTest implements SchedulingConfigurer {

    /**
     * 定时器 服务层
     */
    private CronService cronService;

    @Autowired
    public void setCronService(CronService cronService) {
        this.cronService = cronService;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        // 定时器执行内容
        // 2、根据第一次查询出的定时器不为空，第一次执行定时器里的内容
        // 4、根据第二次查询出的定时器不为空，第二次执行定时器里的内容
        // 以此类推，直至定时器为空
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.debug("执行动态定时器");
            }
        };

        // 创建定时器执行时间
        Trigger trigger = new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {

                // 定时器执行时间
                Date date = null;

                // 查询定时器
                // 1、运行项目开始，第一次查询定时器
                // 3、第一次执行定时器里的内容，第二次查询定时器
                // 5、第二次执行定时器里的内容，第三次查询定时器
                // 以此类推，直至定时器为空
                Cron cron = cronService.selectbyId(1L);

                // 从数据库查询出来了定时器
                if (cron != null) {

                    // 定时器表达式
                    String expression = cron.getExpression();

                    // 定时器表达式不为空
                    if (expression != null) {

                        // 从默认时区中提供的模式生成{@link CronTrigger}
                        // 一个时间字段的空间分隔列表，遵循cron表达式约定
                        CronTrigger trigger = new CronTrigger(expression);

                        // 根据当前时间和定时器表达式，获取下一次执行的具体时间
                        date = trigger.nextExecutionTime(triggerContext);

                    } else {

                        // 定时器表达式为空，获取定时器执行的具体时间
                        Date cronDate = cron.getCronDate();

                        // 现在时间在定时器执行具体时间之前，设置程序执行时间
                        if (new Date().before(cronDate)) {
                            date = cronDate;
                        }

                    }

                }

                return date;
            }
        };

        TriggerTask triggerTask = new TriggerTask(runnable, trigger);

        taskRegistrar.addTriggerTask(triggerTask);
    }

}
