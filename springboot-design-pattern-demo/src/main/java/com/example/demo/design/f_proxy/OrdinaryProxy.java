package com.example.demo.design.f_proxy;

/**
 * @author zhangjie
 * @date 2019/5/9 14:30
 */
public class OrdinaryProxy implements IProxyTestService {

    private IProxyTestService proxyTestService;

    public OrdinaryProxy(IProxyTestService proxyTestService){
        this.proxyTestService = proxyTestService;
    }


    @Override
    public void doSomething() {
        System.out.println("普通代理--doSomething");
        proxyTestService.doSomething();
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("普通代理--somethingElse");
        proxyTestService.somethingElse(arg);
    }
}
