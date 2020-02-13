package cn.com.xuxiaowei.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static cn.com.xuxiaowei.util.Constants.*;

/**
 * 测试 HttpServlet 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@WebServlet(urlPatterns = {"/httpServletTest"})
public class HttpServletTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> map = new HashMap<String, Object>(4);
        Map<String, Object> data = new HashMap<String, Object>(4);
        map.put("data", data);

        map.put(CODE, CODE_OK);
        map.put(MSG, "HttpServlet 测试成功！");

        resp.setContentType("text/json;charset=UTF-8");
        resp.getWriter().println(map);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.flushBuffer();

    }

}
