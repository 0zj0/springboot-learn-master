package com.example.demo.design.d_template_method;

/**
 * 具体子类实现：悍马车模型
 * @author zhangjie
 * @date 2019/4/16 11:21
 */
public class HummerCarModel extends CarModel {

    @Override
    public void start() {
        System.out.println("悍马车发动...");
    }

    @Override
    public void stop() {
        System.out.println("悍马车停车...");
    }

    @Override
    public void alarm() {
        System.out.println("悍马车鸣笛...");
    }

    @Override
    public void engineBoom() {
        System.out.println("悍马车引擎声音...");
    }
}
