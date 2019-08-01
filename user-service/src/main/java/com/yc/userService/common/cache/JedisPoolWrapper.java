package com.yc.userService.common.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * @program: star
 * @description: redis连接池
 * @author: Zwx
 * @create: 2019-07-03 11:19
 **/
@Component
@Slf4j
public class JedisPoolWrapper {
    private JedisPool jedisPool=null;

    @Value("${redis.host}")
    private String redisHost;
    @Value("${redis.port}")
    private int redisPort;
    @Value("${redis.max-idle}")
    private int redisMaxTotal;
    @Value("${redis.max-total}")
    private int redisMaxIdle;
    @Value("${redis.max-wait-millis}")
    private int redisMaxWaitMillis;

    @PostConstruct
    public void init() throws Exception {
        try {
            JedisPoolConfig config=new JedisPoolConfig();
            config.setMaxWaitMillis(redisMaxWaitMillis);
            config.setMaxIdle(redisMaxIdle);
            config.setMaxTotal(redisMaxTotal);

            jedisPool=new redis.clients.jedis.JedisPool(config,redisHost,redisPort,2000);
        } catch (Exception e) {
            log.error("Fail to initialize redis",e);
            throw new Exception("初始化redis失败");
        }
    }

    public redis.clients.jedis.JedisPool getJedisPool() {
        return jedisPool;
    }
}
