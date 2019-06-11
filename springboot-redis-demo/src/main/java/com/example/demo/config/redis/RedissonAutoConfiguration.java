package com.example.demo.config.redis;

import com.example.demo.config.lock.DistributedLock;
import com.example.demo.config.lock.RedisLock;
import com.example.demo.config.lock.RedissLockUtil;
import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Date: 2018/4/23 10:42
 * @Version: 1.0
 * @Author : YOUJIN
 */
@Configuration
@ConditionalOnClass(Config.class)
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfiguration {

    @Autowired
    private RedissonProperties redssionProperties;

    /**
     * 哨兵模式自动装配
     * @return
     */
    RedissonClient redissonSentinel() {
        Config config = new Config();
        String[] address=redssionProperties.getSentinelAddresses();
        System.out.println("redis ######### address");
        for (String add:address){
            System.out.println("address############"+add);
        }
        SentinelServersConfig serverConfig = config.useSentinelServers().addSentinelAddress(redssionProperties.getSentinelAddresses())
                .setMasterName(redssionProperties.getMasterName())
                .setTimeout(redssionProperties.getTimeout())
                .setMasterConnectionPoolSize(redssionProperties.getMasterConnectionPoolSize())
                .setSlaveConnectionPoolSize(redssionProperties.getSlaveConnectionPoolSize());

        if(StringUtil.isNotEmpty(redssionProperties.getPassword())) {
            serverConfig.setPassword(redssionProperties.getPassword());
        }
        return Redisson.create(config);
    }

    /**
     * 单机模式自动装配
     * @return
     */
    RedissonClient redissonSingle() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redssionProperties.getAddress())
                .setTimeout(redssionProperties.getTimeout())
                .setConnectionPoolSize(redssionProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redssionProperties.getConnectionMinimumIdleSize());

        if(StringUtil.isNotEmpty(redssionProperties.getPassword())) {
            serverConfig.setPassword(redssionProperties.getPassword());
        }

        return Redisson.create(config);
    }

    /**
     * 装配locker类，并将实例注入到RedissLockUtil中
     * @return
     */
    @Bean
    DistributedLock distributedLocker() {
        RedisLock locker = new RedisLock();
        locker.setRedissonClient(getRedissonClient());
        RedissLockUtil.setLocker(locker);
        return locker;
    }

    public RedissonClient getRedissonClient(){
        if(StringUtil.isNotEmpty(redssionProperties.getAddress())){
            return redissonSingle();
        }
        if(redssionProperties.getSentinelAddresses()!=null && redssionProperties.getSentinelAddresses().length>0){
            return redissonSentinel();
        }
        return null;
    }

}
