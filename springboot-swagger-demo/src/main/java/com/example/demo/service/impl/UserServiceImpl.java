package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserMapper userMapper;

	public List<User> finds(User user) {
		return userMapper.finds(user);
	}
	
	public User insert(User user) {
		int n = userMapper.insert(user);
		if(n > 0){
			return user;
		}
		return null;
	}

	public int update(User user) {
		return userMapper.update(user);
	}

	public int delete(User user) {
		return userMapper.delete(user);
	}

}
