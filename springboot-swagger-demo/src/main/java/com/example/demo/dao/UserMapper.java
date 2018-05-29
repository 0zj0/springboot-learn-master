package com.example.demo.dao;

import com.example.demo.entity.User;

import java.util.List;

public interface UserMapper{

	/**
	 * 根据条件查询 user 记录
	 * @param user
	 * @date 2018-5-29 17:19:47
	 * @author QingFang-Wang
	 * @return
	 */
	public List<User> finds(User user);
	
	/**
	 * 新增 user 记录
	 * @param user
	 * @date 2018-5-29 17:19:47
	 * @author QingFang-Wang
	 * @return
	 */
	public int insert(User user);
	
	/**
	 * 修改 user 记录
	 * @param user
	 * @date 2018-5-29 17:19:47
	 * @author QingFang-Wang
	 * @return
	 */
	public int update(User user);
	
	/**
	 * 删除 user 记录
	 * @param user
	 * @date 2018-5-29 17:19:47
	 * @author QingFang-Wang
	 * @return
	 */
	public int delete(User user);

}