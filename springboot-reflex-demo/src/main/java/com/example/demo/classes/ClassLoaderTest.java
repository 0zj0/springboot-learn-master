package com.example.demo.classes;

import org.junit.Test;

/**
 * @author zhangjie
 * @date 2019/5/7 14:56
 */
public class ClassLoaderTest {


     /**
      * 类加载器测试
      * @return
      * @author 张杰
      * @date 2019/05/07 15:12
      */
    @Test
    public void test1() throws ClassNotFoundException {
        //1.获取一个系统的类加载器（可以获取，当前这个类ClassLoaderTest就是它加载的）
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器:"+systemClassLoader);        //sun.misc.Launcher$AppClassLoader@18b4aac2

        //2.获取系统类加载器的父类加载器（扩展类加载器，可以获取）
        ClassLoader extensionClassLoader = systemClassLoader.getParent();
        System.out.println("扩展类加载器:"+extensionClassLoader);     //sun.misc.Launcher$ExtClassLoader@3feba861

        //3.获取扩展类加载器的父类加载器（引导类加载器，不可获取）
        ClassLoader bootstapClassLoader = extensionClassLoader.getParent();
        System.out.println("引导类加载器:"+bootstapClassLoader);      //null

        //4.测试当前类由哪个类加载器进行加载（系统类加载器）
        ClassLoader currentClassLoader = Class.forName("com.example.demo.entity.Person").getClassLoader();
        System.out.println("当前类由哪个类加载器进行加载:"+currentClassLoader);       //sun.misc.Launcher$AppClassLoader@18b4aac2

        //5.测试JDK提供的Object类由哪个类加载负责加载（引导类加载器）
        ClassLoader objectClassLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println("Object类由哪个类加载器进行加载:"+objectClassLoader);      //null
    }
}
