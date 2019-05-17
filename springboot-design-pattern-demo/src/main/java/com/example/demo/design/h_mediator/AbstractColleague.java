package com.example.demo.design.h_mediator;

/**
 * 抽象同事类
 * @author zhangjie
 * @date 2019/5/16 16:27
 */
public abstract class AbstractColleague {

    protected ConcreteMediator mediator;

    public AbstractColleague(ConcreteMediator mediator) {
        this.mediator = mediator;
    }
}
