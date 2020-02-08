package cn.com.xuxiaowei.controller;

import cn.com.xuxiaowei.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

import static cn.com.xuxiaowei.util.Constants.ACCESS_DENIED;

/**
 * 授权错误页面 Controller
 *
 * @author xuxiaowei
 * @see FilterSecurityInterceptor#invoke(FilterInvocation) 通过过滤器实现 HTTP 资源进行 Security 处理
 * @see AbstractSecurityInterceptor#beforeInvocation(Object)
 * @see AffirmativeBased#decide(Authentication, Object, Collection)
 * @see AbstractAccessDecisionManager
 * @see AccessDeniedHandlerImpl
 * @since 0.0.1
 */
@Slf4j
@Controller
public class AccessDeniedController {

    /**
     * 提示语
     */
    private static final String MSG = "您无权限访问此页面，此页面所需权限：%s，如需访问，请联系管理员添加权限！";

    /**
     * 错误页面
     *
     * @param request        请求
     * @param response       响应
     * @param model          页面中的值
     * @param authentication 当前用户的权限
     * @return 页面的位置
     * @see AccessDeniedHandlerImpl#handle(HttpServletRequest, HttpServletResponse, AccessDeniedException)
     */
    @GetMapping(ACCESS_DENIED)
    public String accessDenied(HttpServletRequest request, HttpServletResponse response, Model model, Authentication authentication) {

        int status = response.getStatus();
        AccessDeniedException accessDeniedException = (AccessDeniedException) request.getAttribute(WebAttributes.ACCESS_DENIED_403);

        response.setStatus(HttpServletResponse.SC_OK);

        model.addAttribute("status", status);
        model.addAttribute(Constants.MSG, String.format(MSG, "未知"));
        model.addAttribute("accessDeniedException", accessDeniedException);

        return "html/accessDenied";
    }

}
