package com.example.demo.design.f_proxy;

/**
 * 接口实现类
 * @author zhangjie
 * @date 2019/5/9 14:26
 */
public class ProxyTestServiceImpl implements IProxyTestService {

    @Override
    public void doSomething() {
        System.out.println("doSomething.");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("somethingElse " + arg);
    }
}
