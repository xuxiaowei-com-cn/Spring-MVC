package cn.com.xuxiaowei.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static cn.com.xuxiaowei.util.Constants.*;

/**
 * WebSecurity 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurerAdapterConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * @see super#configure(HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 指定支持基于表单的身份验证。
        http.formLogin()
                // 定制登录页面的访问 URL
                .loginPage(LOGIN)
                // 定制登录请求的访问 URL
                .loginProcessingUrl(LOGIN_JSON)
                // 定制登录失败后转向的 URL
                .failureForwardUrl(LOGIN_FAIL_JSON)
                // 定制登录成功后转向的 URL
                .defaultSuccessUrl(LOGIN_SUCCESS_JSON)
                .permitAll()
        ;

        // 使用 logout 方法定制注销行为
        http.logout()
                // 定制注销 URL（行为）
                .logoutUrl(LOGOUT_JSON)
                // 注销成功后跳转的URL
                .logoutSuccessUrl(LOGOUT_SUCCESS_JSON)
                .permitAll();

        // 开启 Cookie 储存用户信息
        http.rememberMe()
                // 指定 Cookie 有效时间
                .tokenValiditySeconds(TOKEN_VALIDITY_SECONDS)
                // 默认：remember-me
                .rememberMeParameter(REMEMBER_ME_PARAMETER)
                // key 指定 Cookie 中的私钥
                .key(KEY)
        ;

        // 所有页面均需要 USER 角色
        // 必须（至少指定一个，防止错误，Caused by: java.lang.IllegalStateException: permitAll only works with HttpSecurity.authorizeRequests()）
        http.authorizeRequests().antMatchers("/**").hasRole("USER");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) {

        // 静态资源不拦截
        web.ignoring().mvcMatchers("/static/css/**");
        web.ignoring().mvcMatchers("/static/js/**");

    }

}
