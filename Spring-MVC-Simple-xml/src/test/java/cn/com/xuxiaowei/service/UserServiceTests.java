package cn.com.xuxiaowei.service;

import cn.com.xuxiaowei.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * user 服务测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(value = {"classpath:/spring-context.xml"})
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void getByUsername() {
        User user = userService.getByUsername("徐晓伟");
        log.debug(String.valueOf(user));
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
    public void testTransactional() {
        userService.testTransactional();
    }

}
