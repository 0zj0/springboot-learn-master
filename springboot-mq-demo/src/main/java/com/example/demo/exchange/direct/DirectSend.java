package com.example.demo.exchange.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * direct(直连模式) 发送消息
 *  direct类型的Exchange路由规则也很简单，它会把消息路由到那些binding key与routing key完全匹配的Queue中。
 * @author zhangjie
 * @date 2019/4/17 11:21
 */
@Configuration
public class DirectSend {

    //交换机名称
    private static final String EXCHANGE_NAME = "ex_test_one";

    private static final String SECOND_EXCHANGE_NAME = "ex_second_one";

    private static final String ROUTING_KEY_1 = "rout_test_one";

    private static final String ROUTING_KEY_2 = "rout_test_one_2";

    private static final String SECOND_ROUTING_KEY_1 = "routing_second_one";

    private static final String SECOND_ROUTING_KEY_2 = "routing_second_two";

    @Resource(name="firstRabbitTemplate")
    private RabbitTemplate firstRabbitTemplate;

    @Resource(name="secondRabbitTemplate")
    private RabbitTemplate secondRabbitTemplate;

    public void directSend1(){
        System.out.println("开始发送direct模式消息(1)...");
        firstRabbitTemplate.convertAndSend(EXCHANGE_NAME,ROUTING_KEY_1,"发送direct模式消息-路由：rout_test_one");
        System.out.println("发送direct模式消息(1)完成...");
    }

    public void directSend2(){
        System.out.println("开始发送direct模式消息(2)...");
        firstRabbitTemplate.convertAndSend(EXCHANGE_NAME,ROUTING_KEY_2,"发送direct模式消息-路由：rout_test_one_2");
        System.out.println("发送direct模式消息(2)完成...");
    }

    public void directSend3(){
        System.out.println("开始发送direct模式消息(3)...");
        secondRabbitTemplate.convertAndSend(SECOND_EXCHANGE_NAME,SECOND_ROUTING_KEY_1,"发送direct模式消息-路由：routing_second_one");
        System.out.println("发送direct模式消息(3)完成...");
    }

    public void directSend4(){
        System.out.println("开始发送direct模式消息(4)...");
        secondRabbitTemplate.convertAndSend(SECOND_EXCHANGE_NAME,SECOND_ROUTING_KEY_2,"发送direct模式消息-路由：routing_second_two");
        System.out.println("发送direct模式消息(4)完成...");
    }

}
