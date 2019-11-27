package cn.com.xuxiaowei.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

/**
 * Redis 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(value = {"classpath:/spring-context.xml"})
public class RedisTests {

    @Test
    public void jedisLocalhost() {

        // 记录操作次数
        int i = 0;

        try (Jedis jedis = new Jedis("localhost", 6379)) {
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
        }

        System.out.println("Redis每秒操作次数：" + i + "次");
    }

}
