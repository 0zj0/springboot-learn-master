package com.example.demo.design.f_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhangjie
 * @date 2019/5/9 14:36
 */
public class DynamicProxy implements InvocationHandler {

    private Object proxyed;

    public DynamicProxy(Object proxyed) {
        this.proxyed = proxyed;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理");
        return method.invoke(proxyed,args);
    }
}
