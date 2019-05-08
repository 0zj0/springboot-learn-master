package com.example.demo.classes;

import com.example.demo.entity.Person;
import com.example.demo.utils.ClassUtil;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author zhangjie
 * @date 2019/5/7 18:51
 */
public class AnnotationTest {

    @Test
    public void test(){
        Person person = new Person();
        person.setAge(10);
    }

    @Test
    public void test1() throws Exception {
        //获取class对象
        Class clazz = Class.forName("com.example.demo.entity.Person");
        //获取method方法
        Method method = ClassUtil.getMethod(clazz,"setAge",Integer.class);

        int val = 25;
        Annotation annotation = method.getAnnotation(AgeAnnotation.class);
        if(annotation != null){
            if(annotation instanceof AgeAnnotation){
                AgeAnnotation ageAnnotation = (AgeAnnotation) annotation;
                if(val < ageAnnotation.min() || val > ageAnnotation.max()){
                    throw new RuntimeException("年龄非法");
                }
            }
        }
        Object obj = clazz.newInstance();
        method.invoke(obj,val);
        System.out.println(obj.toString());
    }

}
