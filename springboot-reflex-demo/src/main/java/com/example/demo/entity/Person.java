package com.example.demo.entity;

import com.example.demo.classes.AgeAnnotation;

import java.io.Serializable;

/**
 * @author zhangjie
 * @date 2019/5/7 11:17
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1258045451015846461L;

    private String name;    //名称

    private int age;        //年龄

    public String addr;     //地址

    protected boolean eatState; //吃饭状态

    public Person() {
    }

    public Person(String name, int age, String addr, boolean eatState) {
        this.name = name;
        this.age = age;
        this.addr = addr;
        this.eatState = eatState;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                ", eatState=" + eatState +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @AgeAnnotation(min = 18,max = 50)
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public boolean isEatState() {
        return eatState;
    }

    public void setEatState(boolean eatState) {
        this.eatState = eatState;
    }

    public void test(){
        System.out.println("测试1");
    }

    private void test1(){
        System.out.println("私有测试2");
    }
}
