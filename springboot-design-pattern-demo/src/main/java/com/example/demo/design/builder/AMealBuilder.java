package com.example.demo.design.builder;

/**
 * 套餐A 具体建造者
 * @author zhangjie
 * @date 2019/4/16 15:01
 */
public class AMealBuilder extends MealBuilder {

    private AMealProduct mealProduct = new AMealProduct();

    @Override
    public MealAbstractProduct getMealProduct() {
        return this.mealProduct;
    }
}
