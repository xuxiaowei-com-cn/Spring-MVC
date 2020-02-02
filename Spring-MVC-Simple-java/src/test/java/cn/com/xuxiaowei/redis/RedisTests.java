package cn.com.xuxiaowei.redis;

import cn.com.xuxiaowei.configuration.SpringContextConfiguration;
import cn.com.xuxiaowei.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Redis 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringContextConfiguration.class})
@WebAppConfiguration
public class RedisTests {

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    /**
     * 操作字符串 {@link RedisTemplate#opsForValue()}
     *
     * @see RedisTemplate#opsForHash() 操作有序 Hash
     * @see RedisTemplate#opsForList() 操作有序 {@link List}
     * @see RedisTemplate#opsForSet() 操作有序 {@link Set}
     * @see RedisTemplate#opsForZSet() 操作有序 Set
     */
    @Test
    public void opsForValue() {

        String username = "xxw";

        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();

        User user = new User();
        user.setUserId(666L);
        user.setUsername(username);
        user.setPassword("xxw123");

        valueOperations.set(username, user);

        User userRedis = valueOperations.get(username);

        log.debug(String.valueOf(userRedis));
    }

    @Test
    public void jedisLocalhost() {

        // 记录操作次数
        int i = 0;

        Jedis jedis = new Jedis("localhost", 6379);

        // jedis.auth("密码");

        long start = System.currentTimeMillis();

        while (true) {

            long end = System.currentTimeMillis();

            if (end - start >= 1000) {
                break;
            } else {
                i++;

                jedis.set("test_" + i, i + "");
            }
        }

        log.debug("Redis每秒操作 {} 次", i);

        jedis.close();

    }

}
