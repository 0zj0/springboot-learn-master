package com.example.demo.design.f_proxy;

import java.lang.reflect.Proxy;

/**
 * @author zhangjie
 * @date 2019/5/9 14:33
 */
public class ProxyTest {

    public static void main(String[] args) throws NoSuchMethodException {
        IProxyTestService proxyTestService = new ProxyTestServiceImpl();
        System.out.println("---------------非代理模式----------------");
        proxyTestService.doSomething();
        proxyTestService.somethingElse("hello");

        System.out.println("---------------普通代理模式----------------");
        OrdinaryProxy ordinaryProxy = new OrdinaryProxy(new ProxyTestServiceImpl());
        ordinaryProxy.doSomething();
        ordinaryProxy.somethingElse("hello");

        System.out.println("---------------动态代理模式----------------");
        IProxyTestService proxyTestService1 = new ProxyTestServiceImpl();
        DynamicProxy proxy = new DynamicProxy(proxyTestService1);
        ClassLoader classLoader = proxyTestService1.getClass().getClassLoader();
        IProxyTestService proxyService = (IProxyTestService) Proxy.newProxyInstance(classLoader,
                new Class[]{IProxyTestService.class},
                proxy);
        proxyService.doSomething();
        proxyService.somethingElse("hello");

        IProxyTestService proxyTestService2 = (IProxyTestService) Proxy.newProxyInstance(
                ProxyTestServiceImpl.class.getClassLoader(),
                new Class[]{IProxyTestService.class},
                new DynamicProxy(new ProxyTestServiceImpl())
        );
        proxyTestService2.doSomething();
    }

}
