package cn.com.xuxiaowei.service;

import cn.com.xuxiaowei.configuration.SpringContextConfiguration;
import cn.com.xuxiaowei.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * user 服务测试类
 *
 * @author xuxiaowei
 * @see <a href="https://spring.io/blog/2014/03/25/spring-mvc-test-with-htmlunit#creating-mockmvc">Creating MockMvc</a>
 * @see <a href="https://spring.io/blog/2014/05/23/preview-spring-security-test-web-security">Preview Spring Security Test: Web Security</a>
 * @since 0.0.1
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringContextConfiguration.class})
@WebAppConfiguration
public class UserServiceTests {

    @Resource
    private UserService userService;

    @Test
    public void getByUsername() {
        User user = userService.getByUsername("徐晓伟");
        log.debug(String.valueOf(user));
    }

    @Test
    public void removeByUsername() {
        boolean removeByUsername = userService.removeByUsername("徐晓伟");
        log.debug(String.valueOf(removeByUsername));
    }

    @Test
    public void save() {
        User user = new User();
        user.setUsername("xxw" + System.currentTimeMillis());
        user.setPassword(UUID.randomUUID().toString().replace("-", ""));

        boolean save = userService.save(user);

        log.debug(String.valueOf(save));
        log.debug(String.valueOf(user));
    }

    @Test
    public void updateByUserId() {
        User user = new User();
        user.setUserId(8L);
        user.setUsername("xxw" + System.currentTimeMillis());
        user.setPassword(UUID.randomUUID().toString().replace("-", ""));

        User updateByUsername = userService.updateByUserId(user);

        log.debug(String.valueOf(updateByUsername));
        log.debug(String.valueOf(user));
    }


    @Test
    public void testTransactional() {
        userService.testTransactional();
    }

}
