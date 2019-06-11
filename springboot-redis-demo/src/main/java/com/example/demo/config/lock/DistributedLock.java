package com.example.demo.config.lock;

import java.util.concurrent.TimeUnit;

/**
 * 定义lock的接口类
 * @author zhangjie
 * @date 2019/6/11 19:28
 */
public interface DistributedLock {

    /**
     * 开启锁
     * @param key
     */
    void lock(String key);

    /**
     * 释放锁
     * @param key
     */
    void unlock(String key);

    /**
     * 开启锁
     * @param key
     * @param timeOut 超时释放时间
     */
    void lock(String key, int timeOut);

    void lock(String key, TimeUnit unit , int timeout);
}
