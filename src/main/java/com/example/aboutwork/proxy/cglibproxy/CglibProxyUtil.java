package com.example.aboutwork.proxy.cglibproxy;

import com.example.aboutwork.proxy.MyTransaction;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyUtil implements MethodInterceptor {

    private Object target;


    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //案 经过测试可知 obj表示是enhancer增强的子类
        System.err.println("开始cglibProxy invoke");
        MyTransaction annotation = obj.getClass().getSuperclass()
                .getDeclaredMethod(method.getName(), method.getParameterTypes())
                .getAnnotation(MyTransaction.class);
        if (annotation == null) {
            return methodProxy.invoke(target, args);
        } else {
            System.err.println("事务开始");
            Object invoke = methodProxy.invokeSuper(obj, args);
            System.err.println("事务结束");
            return invoke;
        }
    }

}
