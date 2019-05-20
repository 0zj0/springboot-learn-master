package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.GlobalEnum;
import com.example.demo.service.IGlobalEnumService;
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
public class TestController {

    @Autowired
    private IGlobalEnumService globalEnumService;

    @RequestMapping("/selectById")
    @ResponseBody
    public GlobalEnum selectById(){
        return globalEnumService.selectById(1);
    }

    @RequestMapping("/selectList")
    @ResponseBody
    public List<GlobalEnum> selectList(){
        return globalEnumService.selectList();
    }

    @RequestMapping("/selectPage")
    @ResponseBody
    public IPage<GlobalEnum> selectPage(){
        return globalEnumService.selectPage(new Page());
    }
}
