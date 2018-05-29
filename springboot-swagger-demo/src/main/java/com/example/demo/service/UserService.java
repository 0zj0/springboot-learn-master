package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService{  

	/**
	 * 根据条件查询 user记录
	 * @param user
	 * @date 2018-5-29 17:19:47
	 * @author QingFang-Wang
	 * @return
	 */
	public List<User> finds(User user);
	
	
	/**
	 * 新增user记录
	 * @param user
	 * @date 2018-5-29 17:19:47
	 * @author zhangjie
	 * @return
	 */
	public User insert(User user);
	
	/**
	 * 修改user记录
	 * @param user
	 * @date 2018-5-29 17:19:47
	 * @author QingFang-Wang
	 * @return
	 */
	public int update(User user);
	
	/**
	 * 删除user记录
	 * @param user
	 * @date 2018-5-29 17:19:47
	 * @author QingFang-Wang
	 * @return
	 */
	public int delete(User user);

}