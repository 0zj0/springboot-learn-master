package com.example.demo.rabbitmq.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Auther: zhangjie
 * @Date: 2018/12/14 18:37
 */
@RestController
public class TestFanoutController {

    @Resource(name="firstRabbitTemplate")
    private RabbitTemplate firstRabbitTemplate;

    private static final String EXCHANGE_NAME = "ex_test_one";

    @GetMapping("/test")
    public void test(HttpServletRequest request){
        firstRabbitTemplate.convertAndSend(EXCHANGE_NAME,"","Hello RabbitMQ!");
    }

}
