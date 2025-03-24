package com.rain.study.danmicproxy;

import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserServiceHandler  implements InvocationHandler {

    // 委托类对象
    private Object target;

    public UserServiceHandler(){}

    public UserServiceHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 使用反射执行委托类对象具体方法
        Object result = method.invoke(target, args);
        System.out.println(method.getName() + " cost time is:" + (System.currentTimeMillis() - startTime));
        return result;
    }
    public Object createProxy(){
        Class[] interfaces = target.getClass().getInterfaces();
        if(interfaces.length==0){
            System.out.println("该类无接口，不能使用jdk都动态代理");
            throw new RuntimeException("该类无接口，不能使用jdk都动态代理");
        }
        return Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), interfaces, this);
    }
}
