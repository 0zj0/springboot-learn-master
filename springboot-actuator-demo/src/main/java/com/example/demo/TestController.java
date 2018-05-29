package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: zhangjie
 * @Date: 2018/5/29 10:58
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/index")
    public String index(){
        return "This is index message";
    }
}
