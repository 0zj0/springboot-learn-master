package com.example.demo.test;

/**
 * @author zhangjie
 * @date 2019/3/19 16:30
 */
public class C extends A  {

    private int age = 10;

    public void read(int age){
        System.out.println("B的read调用:"+this.name+";"+age);
    }

}
