package cn.com.xuxiaowei.controller;

import cn.com.xuxiaowei.configuration.WebMvcConfigurerConfiguration;
import cn.com.xuxiaowei.util.Constants;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static cn.com.xuxiaowei.util.Constants.*;

/**
 * 登录 RestController
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
public class LoginRestController {

    /**
     * 登录请求
     *
     * @see LoginController#login(HttpServletRequest, HttpServletResponse, Model)
     * @see Constants#LOGIN
     * @see Constants#LOGIN_JSON
     * @see RequestMethod#POST
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     */
    @PostMapping(LOGIN)
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = new HashMap<>(4);
        Map<String, Object> data = new HashMap<>(4);
        map.put("data", data);

        map.put(CODE, CODE_OK);
        map.put(MSG, "登录成功");

        return map;
    }

    /**
     * 登录失败
     *
     * @see Constants#LOGIN_FAIL
     * @see Constants#LOGIN_FAIL_JSON
     * @see RequestMethod#POST
     * @see WebMvcConfigurerConfiguration#configureContentNegotiation(ContentNegotiationConfigurer)
     */
    @PostMapping(LOGIN_FAIL)
    public Map<String, Object> loginFail(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = new HashMap<>(4);
        Map<String, Object> data = new HashMap<>(4);
        map.put("data", data);

        map.put(CODE, CODE_ERR);
        map.put(MSG, "登录失败");

        return map;
    }

}
