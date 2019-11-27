package cn.com.xuxiaowei.service;

import cn.com.xuxiaowei.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * user 服务测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(value = {"classpath:/spring-context.xml"})
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void selectByUsername() {
        User user = userService.selectByUsername("徐晓伟");
        System.out.println(user);
    }

}
