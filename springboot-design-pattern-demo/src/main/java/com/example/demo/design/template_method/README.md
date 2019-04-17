设计模式之模板方法
定义：
    Define the skeleton of an algorithm in an operation,deferring some steps to subclasses.
    Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.
    （定义一个操作中的算法的框架，而将一些步骤延迟到子类中。使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。）
    
结构：
    模板方法模式确实非常简单，仅仅使用了Java的继承机制，但它是一个应用非常广泛的模式。其中，AbstractClass叫做抽象模板，它的方法分为两类：
    ● 基本方法
    基本方法也叫做基本操作，是由子类实现的方法，并且在模板方法被调用。
    ● 模板方法
    可以有一个或几个，一般是一个具体方法，也就是一个框架，实现对基本方法的调度，完成固定的逻辑。
    
优点：
    1.封装不变部分，扩展可变部分
    2.提取公共部分代码，便于维护
    3.行为由父类控制，子类实现
    
使用场景：
    1.多个子类有公有的方法，并且逻辑基本相同时。
    2.重要、复杂的算法，可以把核心算法设计为模板方法，周边的相关细节功能则由各个子类实现。
    3.重构时，模板方法模式是一个经常使用的模式，把相同的代码抽取到父类中，然后通 过钩子函数（见“模板方法模式的扩展”）约束其行为。