package com.example.demo.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Description: rabbitmq 配置类
 * @Auther: zhangjie
 * @Date: 2018/12/14 17:55
 */
@Configuration
public class RabbitConfig {

    //注册缓存连接工厂
    @Bean("firstConnectionFactory")
    @Primary
    public ConnectionFactory firstConnectionFactory(@Value("${spring.rabbitmq.first.host}") String host,
                                                    @Value("${spring.rabbitmq.first.port}") int port,
                                                    @Value("${spring.rabbitmq.first.username}") String username,
                                                    @Value("${spring.rabbitmq.first.password}") String password,
                                                    @Value("${spring.rabbitmq.first.virtual-host}") String virtual){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtual);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean("secondConnectionFactory")
    public ConnectionFactory secondConnectionFactory(@Value("${spring.rabbitmq.second.host}") String host,
                                                    @Value("${spring.rabbitmq.second.port}") int port,
                                                    @Value("${spring.rabbitmq.second.username}") String username,
                                                    @Value("${spring.rabbitmq.second.password}") String password,
                                                    @Value("${spring.rabbitmq.second.virtual-host}") String virtual){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtual);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }


    //Qualifier 限定描述赋
    //注册消息模板
    @Bean("firstRabbitTemplate")
    @Primary
    public RabbitTemplate firstRabbitTemplate(@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory){
        RabbitTemplate firstRabbitTemplate = new RabbitTemplate(connectionFactory); //使用外部事物
        return firstRabbitTemplate;
    }

    @Bean("secondRabbitTemplate")
    public RabbitTemplate secondRabbitTemplate(@Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory){
        RabbitTemplate secondRabbitTemplate = new RabbitTemplate(connectionFactory); //使用外部事物
        return secondRabbitTemplate;
    }


    //注册监听容器工厂
    @Bean(name="firstContainerFactory")
    public SimpleRabbitListenerContainerFactory firstContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                                      @Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean(name="secondContainerFactory")
    public SimpleRabbitListenerContainerFactory secondContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                                @Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }


}
