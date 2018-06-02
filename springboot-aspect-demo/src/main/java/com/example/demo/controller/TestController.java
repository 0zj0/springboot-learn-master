package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: zhangjie
 * @Date: 2018/5/30 16:11
 */
@RestController
public class TestController {

    @RequestMapping("index")
    public String index(String name){
        return "Hello "+name;
    }

}
