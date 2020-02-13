package cn.com.xuxiaowei.web.service.impl;

import cn.com.xuxiaowei.entity.Person;
import cn.com.xuxiaowei.web.service.WebServiceTestService;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 测试 WebService 服务实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Service
public class WebServiceTestServiceImpl implements WebServiceTestService {

    /**
     * 自增序列号
     */
    private final AtomicLong counter = new AtomicLong();

    /**
     * 测试接口
     *
     * @param username 测试接口参数
     * @return 返回测试数据
     */
    @Override
    public Person getPerson(String username) {

        Person person = new Person();
        person.setUserId(counter.incrementAndGet());
        person.setUsername(username);
        person.setPassword(UUID.randomUUID().toString());

        return person;
    }

}
