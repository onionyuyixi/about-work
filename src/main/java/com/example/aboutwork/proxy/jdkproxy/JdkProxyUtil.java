package com.example.aboutwork.proxy.jdkproxy;

import com.example.aboutwork.proxy.MyTransaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class JdkProxyUtil implements InvocationHandler {

    private Object target;


    public Object bind(Object target) {
        this.target = target;
        // 取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return invoke(method, args);
    }


    Object invoke(Method method, Object[] args) throws Throwable {
        System.err.println("开始invoke");
        MyTransaction annotation = target.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes())
                .getAnnotation(MyTransaction.class);
        if (annotation == null) {
            return method.invoke(target, args);
        } else {
            System.err.println("开始事务");
            Object invoke = method.invoke(target, args);
            System.err.println("结束事务");
            return invoke;
        }
    }
}
