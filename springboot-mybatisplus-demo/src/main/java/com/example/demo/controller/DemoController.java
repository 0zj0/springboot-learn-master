package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.SysUser;
import com.example.demo.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @RequestMapping("/getUserPage")
    @ResponseBody
    public IPage<SysUser> getUserList(HttpServletRequest request){
        int pageCurrent = Integer.parseInt(request.getParameter("pageCurrent"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        IPage<SysUser> page = new Page<>(pageCurrent,pageSize);
        return sysUserService.getUserPage(page,new SysUser());
    }

    @RequestMapping("/getUserList2")
    @ResponseBody
    public List<SysUser> getUserList2(SysUser sysUser){
        return sysUserService.getSysUserList2(sysUser);
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public int addUser(SysUser sysUser){
        if(sysUser == null){
            return 0;
        }
        return sysUserService.addUser(sysUser);
    }

    @RequestMapping("/addBatchUser")
    @ResponseBody
    public int addBatchUser(){
        List<SysUser> list = new ArrayList<>();
        SysUser sysUser = new SysUser();
        sysUser.setRealName("张三");
        sysUser.setPassword("e00cf25ad42683b3df678c61f42c6bda");
        sysUser.setSuName("zhangsan");
        list.add(sysUser);
        sysUser = new SysUser();
        sysUser.setRealName("李四");
        sysUser.setPassword("e00cf25ad42683b3df678c61f42c6bda");
        sysUser.setSuName("lisi");
        list.add(sysUser);
        return sysUserService.addBatchUser(list);
    }
}
