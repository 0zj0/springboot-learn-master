package com.example.demo.design.builder;

/**
 * 导演类
 * @author zhangjie
 * @date 2019/4/16 15:08
 */
public class Direct {

    private AMealBuilder aMealBuilder = new AMealBuilder();
    private BMealBuilder bMealBuilder = new BMealBuilder();
    private CMealBuilder cMealBuilder = new CMealBuilder();

    //调用套餐A建造类 创建套餐A
    public AMealProduct getAMealProduct(){
        return (AMealProduct) aMealBuilder.getMealProduct();
    }

    //调用套餐B建造类 创建套餐B
    public BMealProduct getBMealProduct(){
        return (BMealProduct) bMealBuilder.getMealProduct();
    }

    //调用套餐C建造类 创建套餐C
    public CMealProduct getCMealProduct(){
        return (CMealProduct) cMealBuilder.getMealProduct();
    }

}
