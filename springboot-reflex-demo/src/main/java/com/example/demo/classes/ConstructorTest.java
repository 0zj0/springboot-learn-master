package com.example.demo.classes;

import com.example.demo.entity.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @author zhangjie
 * @date 2019/5/7 18:36
 */
public class ConstructorTest {

    @Test
    public void test() throws Exception {
        //获取class对象
        Class<Person> personClazz = (Class<Person>) Class.forName("com.example.demo.entity.Person");

        /*******************1. 获取Constructor 对象*******************/
        //1.1 获取全部构造器
        Constructor<Person>[] constructors = (Constructor<Person>[]) personClazz.getConstructors();
        for(Constructor<Person> constructor:constructors){
            System.out.println(constructor);
        }

        //1.2 获取指定构造器
        Constructor<Person> constructor1 = personClazz.getConstructor(java.lang.String.class,
                int.class,
                java.lang.String.class,
                boolean.class);
        System.out.println(constructor1);

        /********************2. 调用构造器*******************/
        Person person = constructor1.newInstance("李四",22,"ddd",false);
        System.out.println(person.toString());
    }

}
