package com.example.demo.design.builder;

/**
 * @author zhangjie
 * @date 2019/4/16 15:13
 */
public class Client {

    public static void main(String[] args) {
        Direct direct = new Direct();
        direct.getAMealProduct().print();
        direct.getBMealProduct().print();
        direct.getCMealProduct().print();
    }

}
