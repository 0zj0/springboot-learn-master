package com.example.demo.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * rabbitmq 配置类
 * @author zhangjie
 * @date 2019/4/17 10:39
 */
@Configuration
public class RabbitConfig {

    //创建mq连接
    @Bean("firstConnectionFactory")
    @Primary //设置默认
    public ConnectionFactory firstConnectionFactory(@Value("${spring.rabbitmq.first.host}") String host,
                                                    @Value("${spring.rabbitmq.first.port}") int port,
                                                    @Value("${spring.rabbitmq.first.username}") String username,
                                                    @Value("${spring.rabbitmq.first.password}") String password,
                                                    @Value("${spring.rabbitmq.first.virtual-host}") String virtual){
        CachingConnectionFactory connectionFactory = getConnectionFactory(host,port,username,password);
        connectionFactory.setVirtualHost(virtual);
        connectionFactory.setPublisherConfirms(true);
        System.out.println("firstConnectionFactory的创建");
        return connectionFactory;
    }

    @Bean("secondConnectionFactory")
    public ConnectionFactory secondConnectionFactory(@Value("${spring.rabbitmq.second.host}") String host,
                                                     @Value("${spring.rabbitmq.second.port}") int port,
                                                     @Value("${spring.rabbitmq.second.username}") String username,
                                                     @Value("${spring.rabbitmq.second.password}") String password,
                                                     @Value("${spring.rabbitmq.second.virtual-host}") String virtual){
        CachingConnectionFactory connectionFactory = getConnectionFactory(host,port,username,password);
        connectionFactory.setVirtualHost(virtual);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    //Qualifier 限定描述赋
    //注册消息模板
    @Bean("firstRabbitTemplate")
    @Primary //设置默认
    public RabbitTemplate firstRabbitTemplate(@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory){
        RabbitTemplate firstRabbitTemplate = new RabbitTemplate(connectionFactory); //使用外部事物
        return firstRabbitTemplate;
    }

    @Bean("secondRabbitTemplate")
    public RabbitTemplate secondRabbitTemplate(@Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory){
        RabbitTemplate secondRabbitTemplate = new RabbitTemplate(connectionFactory); //使用外部事物
        return secondRabbitTemplate;
    }


    private static CachingConnectionFactory getConnectionFactory(String host, int port, String username, String password){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

}
