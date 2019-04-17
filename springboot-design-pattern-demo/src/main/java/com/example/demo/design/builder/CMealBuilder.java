package com.example.demo.design.builder;

/**
 * 套餐C 具体建造者
 * @author zhangjie
 * @date 2019/4/16 15:01
 */
public class CMealBuilder extends MealBuilder {

    private CMealProduct mealProduct = new CMealProduct();

    @Override
    public MealAbstractProduct getMealProduct() {
        return this.mealProduct;
    }
}
