package cn.com.xuxiaowei.configuration;

import cn.com.xuxiaowei.properties.RedisClusterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * Redis 集群配置
 * <p>
 * {@link RedisSingleConfiguration} 与 {@link RedisClusterConfiguration} 使用一个
 * <p>
 * {@link Configuration}
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class RedisClusterConfiguration {

    /**
     * Redis 集群属性文件
     */
    private RedisClusterProperties redisClusterProperties;

    /**
     * {@link Autowired}
     */
    public void setRedisClusterProperties(RedisClusterProperties redisClusterProperties) {
        this.redisClusterProperties = redisClusterProperties;
    }


    /**
     * 公共部分，Lettuce 与 Jedis 连接池所需
     */
    private org.springframework.data.redis.connection.RedisClusterConfiguration redisClusterConfiguration() {

        // 类名重复，使用全限定名
        org.springframework.data.redis.connection.RedisClusterConfiguration redisClusterConfiguration =
                new org.springframework.data.redis.connection.RedisClusterConfiguration();

        RedisNode redisNode1 = new RedisNode(redisClusterProperties.getHost1(), redisClusterProperties.getPort1());
        RedisNode redisNode2 = new RedisNode(redisClusterProperties.getHost2(), redisClusterProperties.getPort2());
        RedisNode redisNode3 = new RedisNode(redisClusterProperties.getHost3(), redisClusterProperties.getPort3());
        RedisNode redisNode4 = new RedisNode(redisClusterProperties.getHost4(), redisClusterProperties.getPort4());
        RedisNode redisNode5 = new RedisNode(redisClusterProperties.getHost5(), redisClusterProperties.getPort5());
        RedisNode redisNode6 = new RedisNode(redisClusterProperties.getHost6(), redisClusterProperties.getPort6());

        redisClusterConfiguration.addClusterNode(redisNode1);
        redisClusterConfiguration.addClusterNode(redisNode2);
        redisClusterConfiguration.addClusterNode(redisNode3);
        redisClusterConfiguration.addClusterNode(redisNode4);
        redisClusterConfiguration.addClusterNode(redisNode5);
        redisClusterConfiguration.addClusterNode(redisNode6);

        return redisClusterConfiguration;
    }

    /**
     * 配置 Lettuce Redis 集群连接器
     * <p>
     * {@link #redisConnectionFactory()} 与 {@link #jedisConnectionFactory()} 启用一个
     *
     * <p>
     * {@link Bean}
     */
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisClusterConfiguration());
    }

    /**
     * 配置 Jedis Redis 集群连接器
     * <p>
     * {@link #redisConnectionFactory()} 与 {@link #jedisConnectionFactory()} 启用一个
     *
     * <p>
     * {@link Bean}
     */
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(redisClusterConfiguration());
    }

}
