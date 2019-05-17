package com.example.demo.design.h_mediator;

/**
 * @author zhangjie
 * @date 2019/5/16 16:39
 */
public class Test {

    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        ConcreteColleague1 concreteColleague1 = new ConcreteColleague1(mediator);
        //ConcreteColleague2 concreteColleague2 = new ConcreteColleague2(mediator);
        //mediator.setConcreteColleague1(concreteColleague1);
       // mediator.setConcreteColleague2(concreteColleague2);
        concreteColleague1.depMethod1();
    }

}
