package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: zhangjie
 * @Date: 2018/6/615:25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/insert",method = RequestMethod.POST)
    public String insert(User user){
        User user1 = userService.insert(user);
        return user1.toString();
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public String update(User user){
        return userService.update(user)+"";
    }

    @RequestMapping(value="/get/{id}",method = RequestMethod.GET)
    public String get(@PathVariable("id") int id){
        return userService.getUser(id).toString();
    }


}
