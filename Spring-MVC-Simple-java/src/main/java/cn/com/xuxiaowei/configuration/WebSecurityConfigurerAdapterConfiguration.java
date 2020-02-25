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
package cn.com.xuxiaowei.configuration;

import cn.com.xuxiaowei.handler.CustomSavedRequestAwareAuthenticationSuccessHandler;
import cn.com.xuxiaowei.properties.SecurityProperties;
import cn.com.xuxiaowei.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * WebSecurity 配置
 * <p>
 * {@link EnableGlobalMethodSecurity#prePostEnabled()} 为 true 开启基于方法的声明式权限控制，默认不开启
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurerAdapterConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Security 属性文件
     */
    private SecurityProperties securityProperties;

    /**
     * 注入加载用户特定数据的核心接口实现类注册
     *
     * @see UserDetailsServiceImpl
     */
    private UserDetailsService userDetailsService;

    /**
     * 注入密码编码器
     *
     * @see PasswordEncoderConfiguration
     */
    private PasswordEncoder passwordEncoder;

    /**
     * 自定义 认证成功策略
     */
    private CustomSavedRequestAwareAuthenticationSuccessHandler customSavedRequestAwareAuthenticationSuccessHandler;

    @Autowired
    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setCustomSavedRequestAwareAuthenticationSuccessHandler(CustomSavedRequestAwareAuthenticationSuccessHandler customSavedRequestAwareAuthenticationSuccessHandler) {
        this.customSavedRequestAwareAuthenticationSuccessHandler = customSavedRequestAwareAuthenticationSuccessHandler;
    }

    /**
     * @see LazyCsrfTokenRepository
     * @see CookieCsrfTokenRepository
     * @see CsrfFilter
     * @see AbstractAuthenticationProcessingFilter#successfulAuthentication(HttpServletRequest, HttpServletResponse, FilterChain, Authentication) 登录成功后执行
     * @see AbstractRememberMeServices#rememberMeRequested(HttpServletRequest, String) 记住我：true、on、yes、1，不区分大小写
     * @see AbstractRememberMeServices#autoLogin(HttpServletRequest, HttpServletResponse) 自动登录
     * @see AbstractRememberMeServices#decodeCookie(String) 从 Cookie 中解析用户信息
     * @see super#configure(HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 指定支持基于表单的身份验证。
        http.formLogin()
                // 定制登录页面的访问 URL
                .loginPage(securityProperties.getLoginPage())
                // 定制登录请求的访问 URL
                .loginProcessingUrl(securityProperties.getLoginProcessingUrl())
                // 定制登录失败后转向的 URL
                .failureForwardUrl(securityProperties.getFailureForwardUrl())
                // 定制登录成功后转向的 URL
                // 使用自定义，如下所示：
                // .defaultSuccessUrl(securityProperties.getDefaultTargetUrl(), securityProperties.getAlwaysUseDefaultTargetUrl())
                .permitAll()
        ;

        // 授权登录成功后处理
        customSavedRequestAwareAuthenticationSuccessHandler.setDefaultTargetUrl(securityProperties.getDefaultTargetUrl());
        customSavedRequestAwareAuthenticationSuccessHandler.setAlwaysUseDefaultTargetUrl(securityProperties.getAlwaysUseDefaultTargetUrl());
        http.formLogin().successHandler(customSavedRequestAwareAuthenticationSuccessHandler);

        // 使用 logout 方法定制注销行为
        http.logout()
                // 定制注销 URL（行为）
                .logoutUrl(securityProperties.getLogoutUrl())
                // 注销成功后跳转的URL
                .logoutSuccessUrl(securityProperties.getLogoutSuccessUrl())
                .permitAll();

        // 开启 Cookie 储存用户信息
        http.rememberMe()
                // 指定 Cookie 有效时间
                .tokenValiditySeconds(securityProperties.getTokenValiditySeconds())
                // 默认：remember-me
                .rememberMeParameter(securityProperties.getRememberMeParameter())
                // key 指定 Cookie 中的私钥
                .key(securityProperties.getKey())
        ;

        // 管理员权限
        // 如果权限存在交集，请调整顺序，将小路径放在前面
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        // 用户权限
        // 必须（至少指定一个，防止错误，Caused by: java.lang.IllegalStateException: permitAll only works with HttpSecurity.authorizeRequests()）
        http.authorizeRequests().antMatchers("/user/**").hasRole("USER");

        // 允许授权配置异常处理，错误页面
        http.exceptionHandling().accessDeniedPage(securityProperties.getAccessDeniedPage());

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 查询用户与密码比较
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }

    @Override
    public void configure(WebSecurity web) {

        // 静态资源不拦截
        web.ignoring().mvcMatchers("/static/css/**");
        web.ignoring().mvcMatchers("/static/js/**");

    }

}
