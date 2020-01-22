package cn.com.xuxiaowei.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 加载 JDBC 属性文件
 * <p>
 * 用于测试 {@link Autowired} 使用注入
 * <p>
 * 由于 {@link PropertySource} 使用了 Java 8 的 @Repeatable 注解，构建编译时，使用 JDK 6 可能会出现警告
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@Configuration
@PropertySource(value = {"classpath:jdbc.properties"})
public class JdbcProperties {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

}
