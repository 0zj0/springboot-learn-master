package com.example.demo.design.builder;

/**
 * 套餐-产品类
 * @author zhangjie
 * @date 2019/4/16 14:47
 */
public class BMealProduct extends MealAbstractProduct {

    @Override
    public void setPart() {
        System.out.println("套餐B组装：汉堡1个，可乐1杯，烤翅1个...");
    }

    @Override
    public void price() {
        System.out.println("套餐B价格：￥15...");
    }
}
