package com.example.demo.design.template_method;

/**
 * 抽象模板方法
 * @author zhangjie
 * @date 2019/4/16 11:13
 */
public abstract class CarModel {

    //车启动抽象方法
    public abstract void start();

    //车停在抽象方法
    public abstract void stop();

    //车响喇叭的抽象方法
    public abstract void alarm();

    //车引擎响声
    public abstract void engineBoom();

    //车的运行方法（上述方法的组装） 运行模板 假定每种车的运行方法都是一样，仅各种组装方法的详细实现不同
    public void run(){
        //先发动汽车
        this.start();
        //引擎开始轰鸣
        this.engineBoom();
        //然后就开始跑了，跑的过程中遇到一条狗挡路，就按喇叭
        this.alarm();
        //到达目的地就停车
        this.stop();
    }

}
