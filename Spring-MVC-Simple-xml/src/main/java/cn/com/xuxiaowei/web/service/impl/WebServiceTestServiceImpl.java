package cn.com.xuxiaowei.web.service.impl;

import cn.com.xuxiaowei.entity.Person;
import cn.com.xuxiaowei.web.service.WebServiceTestService;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 测试 WebService 服务实现类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class WebServiceTestServiceImpl implements WebServiceTestService {

    /**
     * 自增序列号
     */
    private final AtomicLong counter = new AtomicLong();

    @Override
    public Person getPerson(String username) {

        Person person = new Person();
        person.setUserId(counter.incrementAndGet());
        person.setUsername(username);
        person.setPassword(UUID.randomUUID().toString());

        return person;
    }

}
