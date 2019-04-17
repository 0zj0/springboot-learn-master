package com.example.demo.exchange.direct;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangjie
 * @date 2019/4/17 14:31
 */
@Configuration
public class DirectConsume {

    @RabbitListener(queues = "queue_test_one",containerFactory = "firstContainerFactory")
    public void queueConsume1(Message msg, Channel channel){
        //System.out.println("消费direct模式消息(1)...");
        System.out.println("queueConsume1:"+msg.toString());
        SimpleMessageConverter d=new SimpleMessageConverter();
        Object messObj=d.fromMessage(msg);
        System.out.println("queueConsume1:"+messObj.toString());
    }

    @RabbitListener(queues = "queque_test_one_2",containerFactory = "firstContainerFactory")
    public void queueConsume2(Message msg, Channel channel) throws IOException, TimeoutException {
        //System.out.println("消费direct模式消息(2)...");
        System.out.println("queueConsume2:"+msg.toString());
        SimpleMessageConverter d=new SimpleMessageConverter();
        Object messObj=d.fromMessage(msg);
        System.out.println("queueConsume2:"+messObj.toString());
        //消息应答
        //msg.getMessageProperties().getDeliveryTag() routing_key 若一个交换机通过两个路由连接指定队列，则这两个路由线编号为1,2递增
        //channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        //消息拒绝应答 deliveryTag:该消息的index；multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。；requeue：被拒绝的是否重新入队列
        //channel.basicNack(msg.getMessageProperties().getDeliveryTag(),false,true);

        //消息拒绝应答  deliveryTag:该消息的index;requeue：被拒绝的是否重新入队列
        //channel.basicReject(msg.getMessageProperties().getDeliveryTag(), false);
        //channel.close();
    }

    @RabbitListener(queues = "queue_second_one",containerFactory = "secondContainerFactory")
    public void queueConsume3(Message msg, Channel channel){
        //System.out.println("消费direct模式消息(3)...");
        System.out.println("queueConsume3:"+msg.toString());
        SimpleMessageConverter d=new SimpleMessageConverter();
        Object messObj=d.fromMessage(msg);
        System.out.println("queueConsume3:"+messObj.toString());
    }

    @RabbitListener(queues = "queue_second_two",containerFactory = "secondContainerFactory")
    public void queueConsume4(Message msg, Channel channel){
        //System.out.println("消费direct模式消息(4)...");
        System.out.println("queueConsume4:"+msg.toString());
        SimpleMessageConverter d=new SimpleMessageConverter();
        Object messObj=d.fromMessage(msg);
        System.out.println("queueConsume4:"+messObj.toString());
    }

}
