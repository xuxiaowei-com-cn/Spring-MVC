package cn.com.xuxiaowei.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义 认证成功策略
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
