package cn.com.xuxiaowei.util;

import org.springframework.web.context.ContextLoader;

/**
 * Spring 工具类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class SpringUtils {

    /**
     * 根据 类 获取 Bean
     *
     * @param requiredType 类
     * @param <T>          类泛型
     * @return 返回 根据 类 获取 Bean
     */
    public static <T> T getBean(Class<T> requiredType) {
        return ContextLoader.getCurrentWebApplicationContext().getBean(requiredType);
    }

}
