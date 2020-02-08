package cn.com.xuxiaowei.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static cn.com.xuxiaowei.util.Constants.*;

/**
 * 注销 RestController
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RestController
public class LogoutRestController {

    /**
     * 注销登录成功
     *
     * @see RequestMethod#GET
     */
    @GetMapping("/logout/success")
    public Map<String, Object> logoutSuccess(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = new HashMap<>(4);
        Map<String, Object> data = new HashMap<>(4);
        map.put(DATA, data);

        map.put(CODE, CODE_OK);
        map.put(MSG, "注销登录成功");

        return map;
    }

}
