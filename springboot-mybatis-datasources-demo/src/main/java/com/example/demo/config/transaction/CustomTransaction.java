package com.example.demo.config.transaction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义事务注解
 * 类似spring的@transactional,接受多个不同的事务管理器，然后将注解作用到对应的service方法上
 * @author zhangjie
 * @date 2019/6/17 20:30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.PARAMETER})
public @interface CustomTransaction {

    String[] value() default {};

}
