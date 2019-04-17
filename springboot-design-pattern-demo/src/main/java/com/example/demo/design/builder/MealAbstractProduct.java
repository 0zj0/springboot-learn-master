package com.example.demo.design.builder;

/**
 * 套餐产品类
 * @author zhangjie
 * @date 2019/4/16 14:43
 */
public abstract class MealAbstractProduct {

    //组装零件
    public abstract void setPart();

    //套餐价格
    public abstract void price();

    public void print(){
        this.setPart();
        this.price();
    }

}
