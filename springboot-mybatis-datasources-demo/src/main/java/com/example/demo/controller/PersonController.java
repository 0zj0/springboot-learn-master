package com.example.demo.controller;


import com.example.demo.service.IPersonService;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangjie
 * @since 2019-05-28
 */
@RestController
@RequestMapping("/demo/person")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @Autowired
    private IStudentService studentService;

    @GetMapping("/test")
    public String test(){
        personService.getList();
        studentService.getList();
        return "111";
    }

}

