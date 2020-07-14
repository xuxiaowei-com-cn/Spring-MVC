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
package cn.com.xuxiaowei.service;

import cn.com.xuxiaowei.configuration.SpringContextConfiguration;
import cn.com.xuxiaowei.entity.Cron;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * 定时器 服务测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@ExtendWith(SpringExtension.class)
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
