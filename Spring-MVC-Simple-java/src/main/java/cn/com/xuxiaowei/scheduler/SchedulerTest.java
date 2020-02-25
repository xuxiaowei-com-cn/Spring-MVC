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

import cn.com.xuxiaowei.properties.JdbcProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器 测试类
 * <p>
 * 由于 {@link Scheduled} 使用了 Java 8 的 @Repeatable 注解，构建编译时，使用 JDK 6 可能会出现警告
 * <p>
 * 多线程执行
 *
 * @author xuxiaowei
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/annotation/Repeatable.html">Java 8 @Repeatable</a>
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
     * Async
     * Scheduled(cron = "0/5 * * * * *")
     */
    public void job() {
        log.info("线程：{}，注入：{}", Thread.currentThread().getName(), jdbcProperties.toString());
    }

}
