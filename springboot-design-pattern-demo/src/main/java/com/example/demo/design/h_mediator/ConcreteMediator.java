package com.example.demo.design.h_mediator;

/**
 * 中介具体类
 * @author zhangjie
 * @date 2019/5/16 16:32
 */
public class ConcreteMediator extends AbstractMediator {

    public ConcreteMediator() {
        this.concreteColleague1 = new ConcreteColleague1(this);
        this.concreteColleague2 = new ConcreteColleague2(this);
    }

    @Override
    public void dosomething1() {
        //调用同事类方法
        System.out.println("中介者调用同事类一的依赖方法");
        super.concreteColleague1.selfMethod1();
        super.concreteColleague2.selfMethod2();
        this.getConcreteColleague2().selfMethod2();
    }

    @Override
    public void dosomething2() {
        System.out.println("中介者调用同事类二的依赖方法");
        this.getConcreteColleague2().selfMethod2();
        this.getConcreteColleague1().selfMethod1();
    }
}
