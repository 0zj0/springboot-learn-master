package com.example.demo.test;

/**
 * @author zhangjie
 * @date 2019/3/19 16:30
 */
public class B extends A {

    private String addr = "宝安";

    public void read(){
        System.out.println("B的read调用:"+this.name+";"+addr);
    }

}
