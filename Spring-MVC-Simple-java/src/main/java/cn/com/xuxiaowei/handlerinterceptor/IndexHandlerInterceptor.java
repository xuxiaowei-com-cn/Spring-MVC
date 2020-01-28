package cn.com.xuxiaowei.handlerinterceptor;

import cn.com.xuxiaowei.properties.JdbcProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
public class IndexHandlerInterceptor implements HandlerInterceptor {

    private JdbcProperties jdbcProperties;

    @Autowired
    public void setJdbcProperties(JdbcProperties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
    }

    /**
     * 1、在执行实际的处理程序之前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.debug("访问 {} 进入 {} 拦截器 preHandle", request.getRequestURI(), this.getClass());

        log.debug("进入 {} 拦截器 preHandle，测试注入 {}", this.getClass(), jdbcProperties);

        return true;
    }

    /**
     * 2、执行处理程序后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.debug("访问 {} 进入 {} 拦截器 postHandle", request.getRequestURI(), this.getClass());

    }

    /**
     * 3、完成完整的请求后
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.debug("访问 {} 进入 {} 拦截器 afterCompletion", request.getRequestURI(), this.getClass());

    }

}
