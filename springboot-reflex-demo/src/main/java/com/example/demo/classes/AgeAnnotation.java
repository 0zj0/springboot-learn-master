package com.example.demo.classes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个注解
 * @author zhangjie
 * @date 2019/5/7 18:45
 */
@Retention(RetentionPolicy.RUNTIME)     //注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
@Target(value = {ElementType.METHOD})   //方法上注解，用于描述方法
public @interface AgeAnnotation {

    public int min();
    public int max();

}
