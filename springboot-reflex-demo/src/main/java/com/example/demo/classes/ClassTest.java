package com.example.demo.classes;

import com.example.demo.entity.Person;
import org.junit.Test;

/**
 * @author zhangjie
 * @date 2019/5/7 11:21
 */
public class ClassTest {


     /**
      * 通过Class类获取对象
      * @return
      * @author 张杰
      * @date 2019/05/07 11:29
      */
    @Test
    public void test1() throws ClassNotFoundException {
        Class clazz = null;

        //1.通过类名获取Class对象  类名.class
        clazz = Person.class;
        System.out.println(clazz.getName());

        //2.通过对象获取Class对象  对象名.getClass()
        Person person = new Person();
        clazz = person.getClass();
        System.out.println(clazz.getName());
        //或者
        Object obj = new Person();
        clazz = obj.getClass();
        System.out.println(clazz.getName());

        //3.通过全类名获取Class对象  Class.forName(全类名)
        String className = "com.example.demo.entity.Person";
        clazz = Class.forName(className);

        System.out.println(clazz.getName());
    }


     /**
      * 获取Class对象的实例
      * @return
      * @author 张杰
      * @date 2019/05/07 14:45
      */
    @Test
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        /*String className = "com.example.demo.entity.Person";
        Class clazz = Class.forName(className);

        //调用缺省构造函数，返回该Class对象的一个实例
        Person person = (Person) clazz.newInstance();
        System.out.println(person.toString());*/
    }
}
