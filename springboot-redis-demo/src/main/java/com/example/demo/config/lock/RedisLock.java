package com.example.demo.config.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangjie
 * @date 2019/6/11 19:31
 */
public class RedisLock implements DistributedLock {

    private RedissonClient redissonClient;

    @Override
    public void lock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.lock();
    }

    @Override
    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }

    @Override
    public void lock(String key, int timeOut) {
        RLock lock = redissonClient.getLock(key);
        lock.lock(timeOut,TimeUnit.SECONDS);
    }

    @Override
    public void lock(String key, TimeUnit unit, int timeout) {
        RLock lock = redissonClient.getLock(key);
        lock.lock(timeout, unit);
    }

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
}
