package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: zhangjie
 * @Date: 2018/6/6 14:11
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userdao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<User> finds(User user) {
        return null;
    }


    /**
     * 获取城市逻辑：
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     */
    @Override
    public User getUser(int id) {
        String key="user_"+id;
        //redisTemplate.
        return userdao.getUser(id);
    }

    @Override
    public User insert(User user) {
        userdao.insert(user);
        return user;
    }

    @Override
    public int update(User user) {
        return userdao.update(user);
    }

    @Override
    public int delete(User user) {
        return 0;
    }
}
