package com.company.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

@Configuration
@ConditionalOnClass(RedissonClient.class)
@ConditionalOnProperty({"spring.redis.host"})
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient getRedissonClient() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Config config = new Config();
        SingleServerConfig singleServerConfig= config.useSingleServer();
        singleServerConfig.setAddress("redis://" + host + ":" + port)
                .setDatabase(database)
                .setTimeout(timeout);
        //密码不为空才设置，否则默认yml注入的空串会导致创建redisson不成功
        if(!StringUtils.isEmpty(password)){
            singleServerConfig.setPassword(password);
        }
        //指定默认序列化
        Codec codec=(Codec) ClassUtils.forName("org.redisson.codec.JsonJacksonCodec", ClassUtils.getDefaultClassLoader()).newInstance();
        config.setCodec(codec);
        //设置看门狗的超时时间
        config.setLockWatchdogTimeout(10000);
        return Redisson.create(config);
    }
}

