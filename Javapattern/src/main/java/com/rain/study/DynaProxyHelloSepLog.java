package com.rain.study;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynaProxyHelloSepLog implements InvocationHandler {

    private Object  target;
    private Object  proxy;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;
        Class clazz = this.proxy.getClass();

        Method start = clazz.getDeclaredMethod("start",new Class[]{Method.class});

        //执行日志中的 start方法
        start.invoke(this.proxy, new Object[]{method});

        //执行要处理对象的原本方法
        result =  method.invoke(this.target, args);

        Method end = clazz.getDeclaredMethod("end", new Class[]{Method.class});
        //反射执行end方法
        end.invoke(this.proxy, new Object[]{method});

        return null;
    }

    public  Object bind(Object proxy,Object target){
        this.proxy = proxy;
        this.target = target;

        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),this.target.getClass().getInterfaces(),this);
    }
}
