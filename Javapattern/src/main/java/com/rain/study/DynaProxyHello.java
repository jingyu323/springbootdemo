package com.rain.study;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynaProxyHello implements InvocationHandler {
    private  Object target;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;

        Logger.start("DynaProxyHello");

        result = method.invoke(this.target, args);
        Logger.end("DynaProxyHello");

        return result;
    }


    /**
     * 刚才没有添加target 所以它代理的是当前的类，也就是
     * @param obj
     * @return
     */
    public  Object bind(Object obj){
        this.target = obj;
        return  Proxy.newProxyInstance(this.target.getClass().getClassLoader(),this.target.getClass().getInterfaces(),this);

    }
}
