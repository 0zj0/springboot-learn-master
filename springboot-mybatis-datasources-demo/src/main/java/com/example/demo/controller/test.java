package com.example.demo.controller;

import com.example.demo.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjie
 * @date 2019/6/11 10:39
 */
public class test {

   /* public static void main(String[] args) throws CloneNotSupportedException {
        Person person = new Person();
        person.setId(1);
        person.setName("111");
        person.setAge(21);
        List<Person> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for(int i = 0; i < 5;i++){
            Person person1 = new Person();
            BeanUtils.copyProperties(person,person1);
            person1.setId(i);
            list.add(person1);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);  //166
        for(Person person2:list){
            System.out.println(person2.toString());
        }
    }*/


    public static void main(String[] args) throws CloneNotSupportedException {
        Person person = new Person();
        person.setId(1);
        person.setName("111");
        person.setAge(21);
        List<Person> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for(int i = 0; i < 5;i++){
            Person person1 = new Person();
            person1.setId(i);
            list.add(person1);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);  //166
        for(Person person2:list){
            System.out.println(person2.toString());
        }
    }
}
