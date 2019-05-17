设计模式之中介者模式
    efine an object that encapsulates how a set of objects interact.Mediator promotes loose coupling 
by keeping objects from referring to each other explicitly,and it lets you vary their interaction independently.
    （用一个中介对象封装一系列的对象 交互，中介者使各对象不需要显示地相互作用，从而使其耦合松散，而且可以独立地改变它
们之间的交互。）

    
 组成：
    1.mediator 抽象中介者角色
    抽象中介者角色定义统一的接口，用于各同事角色之间的通信
    2.concrete mediator 具体中介者角色
    具体中介者角色通过协调各同事角色实现协作行为，因此它必须依赖于各个同事角色。
    3.colleague 同事角色
    每一个同事角色都知道中介者角色，而且与其他的同事角色通信的时候，一定要通过中
    介者角色协作。每个同事类的行为分为两种：一种是同事本身的行为，比如改变对象本身的
    状态，处理自己的行为等，这种行为叫做自发行为（Self-Method），与其他的同事类或中介
    者没有任何的依赖；第二种是必须依赖中介者才能完成的行为，叫做依赖方法（Dep-Method）
    