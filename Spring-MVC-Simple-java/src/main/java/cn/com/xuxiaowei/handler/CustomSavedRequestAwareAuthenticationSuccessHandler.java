package cn.com.xuxiaowei.handler;

import cn.com.xuxiaowei.controller.LoginRestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义 认证成功策略
 * <p>
 * 主要是实现认证完成后，重定向到 {@link AbstractAuthenticationTargetUrlRequestHandler#setDefaultTargetUrl(String)}，
 * 并且不删除 {@link RequestCache}（在响应登录结果时，获取到 {@link RequestCache} 响应到客户端并删除）
 * <p>
 * 改变 {@link RequestCache} 删除时机，参见：{@link LoginRestController#loginSuccess(HttpServletRequest, HttpServletResponse)}
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Component
public class CustomSavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * @see super#onAuthenticationSuccess(HttpServletRequest, HttpServletResponse, Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        String targetUrl = determineTargetUrl(request, response);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

}
