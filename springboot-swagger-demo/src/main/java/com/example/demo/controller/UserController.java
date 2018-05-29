package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private UserService userService;


	@ApiOperation(value="添加用户", notes="添加用户信息")
	@ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String")
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insertUser(User user){
		userService.insert(user);
		return "添加成功";
	}

	@RequestMapping(value = "find", method = RequestMethod.POST)
	public String findUser(User user){
		List<User> list = userService.finds(user);
		list.forEach((s) -> System.out.println(s.toString()));
		return "查找成功";
	}

}
