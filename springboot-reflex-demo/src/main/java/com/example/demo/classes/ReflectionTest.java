package com.example.demo.classes;

import com.example.demo.entity.Person;
import com.example.demo.utils.ClassUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author zhangjie
 * @date 2019/5/7 15:24
 */
public class ReflectionTest {

    @Test
    public void test1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //获取class对象
        Class personClazz = Class.forName("com.example.demo.entity.Person");
        Class studentClazz = Class.forName("com.example.demo.entity.Student");


        /*******************1.获取方法***********************/
        //1.1获取class对应类中的所有方法-方法数组，不能获取private方法，且获取父类继承来的所有方法
        Method[] methods = studentClazz.getMethods();
        Arrays.asList(methods).stream().forEach(method -> System.out.print(method.getName()+"\t"));
        //print	getGrade	setGrade	toString	getName	setName	setAddr	getAddr	setAge	isEatState	getAge	setEatState	wait	wait	wait	equals	hashCode	getClass	notify	notifyAll
        System.out.println();

        //1.2获取所有方法，包括私有方法，方法数组，所有声明的方法，都可以获取到，且只获取当前类的方法(继承类方法获取不到)
        Method[] methods1 = studentClazz.getDeclaredMethods();
        Arrays.asList(methods1).stream().forEach(method -> System.out.print(method.getName()+"\t"));
        //print	getGrade	setGrade	print1
        System.out.println();

        //1.3获取指定的方法，需要参数名称和参数列表，午餐则不需要写
        //Person public String getName()
        Method method = personClazz.getMethod("getName");
        System.out.println(method.getName());       //getName
        //Person public void setAge(int age)  注意：int对应int.class;integer 对应integer.class,不能混乱
        Method method1 = personClazz.getMethod("setAge",Integer.class);
        System.out.println(method1.getName());      //setAge

        /*******************2.执行方法***********************/
        //invoke 第一个参数表示执行哪个对象的方法，剩下的参数是执行方法时需要传入的参数
        Object obj = personClazz.newInstance();
        System.out.println(obj.toString());
        method1.invoke(obj,20);     //执行Person的setAge方法
        System.out.println(obj.toString());

        //私有方法的执行，必须在调用invoke之前加上一句method.setAccessible（true）;
    }

    @Test
    public void test2() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object obj = Class.forName("com.example.demo.entity.Person").newInstance();
        //Object obj = new Person();
        System.out.println(obj.toString());
        ClassUtil.invoke(obj,"setAge",30);
        System.out.println(obj.toString());
    }

     /**
      * 获取父类的私有方法
      * @return
      * @author 张杰
      * @date 2019/05/07 17:13
      */
    @Test
    public void test3() throws Exception {
        Object obj = Class.forName("com.example.demo.entity.Student").newInstance();
        System.out.println(obj.toString());
        ClassUtil.invoke(obj,"setAge",30);
        System.out.println(obj.toString());
    }

    @Test
    public void testField() throws Exception {
        //获取class对象
        Class personClazz = Class.forName("com.example.demo.entity.Person");
        Class studentClazz = Class.forName("com.example.demo.entity.Student");

        /****************1. 获取字段*****************/
        //1.1 获取所有字段   可以获取公用和私有的所有字段，但不能获取父类字段
        Field[] personFields = personClazz.getDeclaredFields();
        Arrays.asList(personFields).stream().forEach(field -> System.out.print(field.getName()+"\t"));
        //name	age	addr	eatState
        System.out.println();

        Field[] studentFields = studentClazz.getDeclaredFields();
        Arrays.asList(studentFields).stream().forEach(field -> System.out.print(field.getName()+"\t"));
        //grade
        System.out.println();

        //1.2获取指定字段
        Field field = personClazz.getDeclaredField("name");
        System.out.println(field.getName());

        /****************2. 使用字段*****************/
        //2.1 获取指定对象的指定字段的值
        Person person = new Person("zhangsan",20,"eee",true);
        Field field1 = personClazz.getDeclaredField("addr");
        Object value = field1.get(person);
        System.out.println("value:"+value);     //name 为私有熟悉，获取失败

        //2.2 设置指定对象的指定对象Field值
        field1.set(person,"rrr");
        System.out.println(person.getAddr());

        //2.3 如果字段私有的，读写都必须先调用setAccessible(true)方法
        field.setAccessible(true);
        System.out.println("name-value:"+field.get(person));

    }

}
