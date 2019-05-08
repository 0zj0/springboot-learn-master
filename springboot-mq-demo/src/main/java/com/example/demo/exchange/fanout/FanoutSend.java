package com.example.demo.exchange.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * fanout(广播模式) 发送消息
 * 它会把所有发送到该Exchange的消息路由到所有与它绑定的Queue中。
 * @author zhangjie
 * @date 2019/4/17 16:40
 */
@Configuration
public class FanoutSend {

    //交换机名称
    private static final String FIRST_EXCHANGE_NAME = "ex_test_one";

    private static final String SECOND_EXCHANGE_NAME = "ex_second_one";


    @Resource(name="firstRabbitTemplate")
    private RabbitTemplate firstRabbitTemplate;

    @Resource(name="secondRabbitTemplate")
    private RabbitTemplate secondRabbitTemplate;

    public void fanoutSend1(){
        System.out.println("开始发送fanout模式消息(1)...");
        firstRabbitTemplate.convertAndSend(FIRST_EXCHANGE_NAME,"","发送fanout模式消息-交换机：ex_test_one");
        System.out.println("发送fanout模式消息(1)完成...");
    }

    public void fanoutSend2(){
        System.out.println("开始发送fanout模式消息(2)...");
        secondRabbitTemplate.convertAndSend(SECOND_EXCHANGE_NAME,"","发送fanout模式消息-路由：ex_second_one");
        System.out.println("发送fanout模式消息(2)完成...");
    }


}
