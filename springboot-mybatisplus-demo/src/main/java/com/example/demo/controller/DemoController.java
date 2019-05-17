package com.example.demo.controller;

import com.example.demo.entity.SysUser;
import com.example.demo.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhangjie
 * @date 2019/5/17 15:11
 */
@Controller
public class DemoController {

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "Hello World!";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/getUserList")
    @ResponseBody
    public List<SysUser> getUserList(){
        return sysUserService.getSysUserList();
    }
}
