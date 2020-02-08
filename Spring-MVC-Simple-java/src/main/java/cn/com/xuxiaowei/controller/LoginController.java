package cn.com.xuxiaowei.controller;

import cn.com.xuxiaowei.configuration.WebMvcConfigurerConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

import static cn.com.xuxiaowei.util.Constants.ROLE_ANONYMOUS;
import static org.thymeleaf.spring4.view.ThymeleafViewResolver.REDIRECT_URL_PREFIX;

/**
 * 登录 Controller
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Controller
public class LoginController {

    /**
     * 登录页面
     * <p>
     * 登录页面登录后不可访问
     *
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     * @see LoginRestController#loginSuccess(HttpServletRequest, HttpServletResponse)
     * @see RequestMethod#GET
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication != null) {

            String name = authentication.getName();
            Object credentials = authentication.getCredentials();
            Object principal = authentication.getPrincipal();
            Object details = authentication.getDetails();

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            for (GrantedAuthority grantedAuthority : authorities) {

                String authority = grantedAuthority.getAuthority();

                // 当前用户的权限不是匿名用户
                if (!ROLE_ANONYMOUS.equals(authority)) {

                    // 重定向到首页
                    return REDIRECT_URL_PREFIX + "/";
                }

            }
        }

        return "html/login";
    }

}
