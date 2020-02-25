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
package cn.com.xuxiaowei.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 加载 Security 属性文件
 * <p>
 * 用于测试 {@link Autowired} 使用注入
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Configuration
@PropertySource(value = {"classpath:security.properties"})
public class SecurityProperties {

    @Value("${security.login-page}")
    private String loginPage;

    @Value("${security.login-processing-url}")
    private String loginProcessingUrl;

    @Value("${security.failure-forward-url}")
    private String failureForwardUrl;

    @Value("${security.default-target-url}")
    private String defaultTargetUrl;

    @Value("${security.always-use-default-target-url}")
    private Boolean alwaysUseDefaultTargetUrl;

    @Value("${security.logout-url}")
    private String logoutUrl;

    @Value("${security.logout-success-url}")
    private String logoutSuccessUrl;

    @Value("${security.token-validity-seconds}")
    private Integer tokenValiditySeconds;

    @Value("${security.remember-me-parameter}")
    private String rememberMeParameter;

    @Value("${security.key}")
    private String key;

    @Value("${security.access-denied-page}")
    private String accessDeniedPage;

}
