/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.xuxiaowei.filter;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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
public class LogHttpFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

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

        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {

    }

}
