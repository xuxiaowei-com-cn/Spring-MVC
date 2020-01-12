package cn.com.xuxiaowei.service;

import cn.com.xuxiaowei.configuration.SpringContextConfiguration;
import cn.com.xuxiaowei.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;

/**
 * user 服务测试类
 *
 * @author xuxiaowei
 * @see <a href="https://spring.io/blog/2014/03/25/spring-mvc-test-with-htmlunit#creating-mockmvc">Creating MockMvc</a>
 * @see <a href="https://spring.io/blog/2014/05/23/preview-spring-security-test-web-security">Preview Spring Security Test: Web Security</a>
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringContextConfiguration.class})
@WebAppConfiguration
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void getByUsername() {
        User user = userService.getByUsername("徐晓伟");
        System.out.println(user);
    }

    @Test
    public void save() {
        User user = new User();
        user.setUsername("xxw" + System.currentTimeMillis());
        user.setPassword(UUID.randomUUID().toString().replace("-", ""));

        boolean save = userService.save(user);

        System.out.println(save);
        System.out.println(user);
    }

    @Test
    public void testTransactional() {
        userService.testTransactional();
    }

}
