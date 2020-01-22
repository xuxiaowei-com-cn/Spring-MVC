package cn.com.xuxiaowei.scheduler;

import cn.com.xuxiaowei.properties.JdbcProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时器 测试类
 *
 * @author xuxiaowei
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/annotation/Repeatable.html">Java 8 @Repeatable</a>
 * @since 0.0.1
 */
@Slf4j
@Component
public class SchedulerTest {

    /**
     * 用于测试 {@link Autowired} 使用注入
     */
    private JdbcProperties jdbcProperties;

    /**
     * 标准注入
     */
    @Autowired
    public void setJdbcProperties(JdbcProperties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
    }

    /**
     * 使用 XML 配置
     */
    public void job() {
        log.info(jdbcProperties.toString());
    }

}
