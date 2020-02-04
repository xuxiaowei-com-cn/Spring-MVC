package cn.com.xuxiaowei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.com.xuxiaowei.util.Constants.LOGIN;

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
     *
     * @see LoginRestController#loginSuccess(HttpServletRequest, HttpServletResponse)
     * @see RequestMethod#GET
     */
    @GetMapping(LOGIN)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "html/login";
    }


}
