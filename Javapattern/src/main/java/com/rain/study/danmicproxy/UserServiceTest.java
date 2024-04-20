package com.rain.study.danmicproxy;

import com.rain.study.danmicproxy.impl.UserServiceImpl;

import java.lang.reflect.Proxy;

public class UserServiceTest {
    public static void main(String[] args) {
        UserServiceHandler handler =  new UserServiceHandler(new UserServiceImpl());
        UserService pro= (UserService) handler.createProxy();

        pro.createUser();

        UserService operator = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(),
                new Class[]{UserService.class},
                handler);
        operator.createUser();
    }

}
