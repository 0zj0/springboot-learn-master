package com.example.demo.controller;

import com.example.demo.entity.MsgContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangjie
 * @date 2019/6/21 15:20
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public MsgContext test(){
        //系统异常
        System.out.println(1111);
        return new MsgContext();
    }

}
