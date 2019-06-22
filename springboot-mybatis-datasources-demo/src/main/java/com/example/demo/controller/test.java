package com.example.demo.controller;

import com.example.demo.entity.Person;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

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


    /*public static void main(String[] args) throws CloneNotSupportedException {
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
    }*/

    public static void main(String[] args) {

        List<Person> list = new ArrayList<>();
        for(int i = 0; i < 5;i++){
            Person person = new Person();
            person.setId(i);
            person.setName("111");
            person.setAge(i+20);
            list.add(person);
        }
        IntSummaryStatistics sum = list.stream().map(Person::getAge).collect(Collectors.summarizingInt(value -> value));
        System.out.println(sum.getSum());
        int sum2 = list.stream().map(Person::getAge)
                .reduce((integer, integer2) -> integer + integer2)
                .orElse(0);
        System.out.println(sum2);
        List<Person> list3 = new ArrayList<>();
        int sum3 = list3.stream().map(Person::getAge)
                .reduce((integer, integer2) -> integer + integer2)
                .orElse(0);
        System.out.println(sum3);

        List<Person> list2 = list.stream().filter(person -> person.getId()!=1).collect(Collectors.toList());
        list2.stream().forEach(person -> System.out.println(person.toString()));

        List<Integer> ids = list3.stream().map(Person::getId).collect(Collectors.toList());
        System.out.println(ids.contains(1));
    }
}
