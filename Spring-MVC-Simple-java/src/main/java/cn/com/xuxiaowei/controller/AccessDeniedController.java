package cn.com.xuxiaowei.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.com.xuxiaowei.util.Constants.ACCESS_DENIED;

/**
 * 错误页面 Controller
 *
 * @author xuxiaowei
 * @see AccessDeniedHandlerImpl
 * @since 0.0.1
 */
@Slf4j
@Controller
public class AccessDeniedController {
    /**
     * 错误页面
     *
     * @param request  请求
     * @param response 响应
     * @param model    页面中的值
     * @return 页面的位置
     * @see AccessDeniedHandlerImpl#handle(HttpServletRequest, HttpServletResponse, AccessDeniedException)
     */
    @GetMapping(ACCESS_DENIED)
    public String accessDenied(HttpServletRequest request, HttpServletResponse response, Model model) {

        int status = response.getStatus();
        AccessDeniedException accessDeniedException = (AccessDeniedException) request.getAttribute(WebAttributes.ACCESS_DENIED_403);

        response.setStatus(HttpServletResponse.SC_OK);

        model.addAttribute("status", status);
        model.addAttribute("accessDeniedException", accessDeniedException);

        return "html/accessDenied";
    }

}
