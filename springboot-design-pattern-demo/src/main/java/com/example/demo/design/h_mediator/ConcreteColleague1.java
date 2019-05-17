package com.example.demo.design.h_mediator;

/**
 * 具体同事类
 * @author zhangjie
 * @date 2019/5/16 16:29
 */
public class ConcreteColleague1 extends AbstractColleague  {

    public ConcreteColleague1(ConcreteMediator mediator) {
        super(mediator);
    }

    public void selfMethod1(){
        System.out.println("ConcreteColleague1 自有方法，处理自己的业务");
    }

    public void depMethod1(){
        //处理自己的业务逻辑
        //自己不能处理的业务逻辑，委托给中介者处理
        super.mediator.dosomething1();
    }
}
