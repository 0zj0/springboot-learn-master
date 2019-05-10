package com.example.demo.design.e_builder;

/**
 * 套餐B 具体建造者
 * @author zhangjie
 * @date 2019/4/16 15:01
 */
public class BMealBuilder extends MealBuilder {

    private BMealProduct mealProduct = new BMealProduct();

    @Override
    public MealAbstractProduct getMealProduct() {
        return this.mealProduct;
    }
}
