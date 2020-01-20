package cn.com.xuxiaowei.filter;

import org.slf4j.MDC;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 日志 过滤器
 * <p>
 * 用户在日志输出时，动态添加指定的信息，如：用户唯一标识，IP等
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class LogHttpFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        // 如果没有 Session，将获取到空
        HttpSession session = req.getSession(false);

        String sessionId;

        // 用户未登陆（为了测试方便）
        if (session == null) {
            sessionId = "未登录";
        } else {
            // 用户已登陆（为了测试方便）
            sessionId = "用户标识：" + session.getId();
        }

        // IP
        String remoteHost = req.getRemoteHost();

        // 此处放置 Session ID 用于测试
        // 使用时应获取用户到用户唯一标识（如：用户名）
        MDC.put("sessionId", sessionId);
        // IP，需要根据实际情况（代理）进行获取
        MDC.put("IP", remoteHost);

        super.doFilter(req, res, chain);
    }

}
