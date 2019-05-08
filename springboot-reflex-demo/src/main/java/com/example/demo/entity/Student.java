package com.example.demo.entity;

/**
 * @author zhangjie
 * @date 2019/5/7 15:28
 */
public class Student extends Person {

    private int grade;  //年级

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Student() {
    }

    public Student(int grade) {
        this.grade = grade;
    }

    public Student(String name, int age, String addr, boolean eatState, int grade) {
        super(name, age, addr, eatState);
        this.grade = grade;
    }

    public void print(){
        System.out.println("This is a Student!");
    }

    private void print1(){
        System.out.println("This is a Student!");
    }

    @Override
    public String toString() {
        String person = super.toString();
        return "Student{" +
                "grade=" + this.getGrade() +
                ", "+person+
                '}';
    }
}
