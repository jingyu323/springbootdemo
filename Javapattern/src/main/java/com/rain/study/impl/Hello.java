package com.rain.study.impl;

import com.rain.study.IHello;

public class Hello implements IHello {
    @Override
    public void sayHello(String str) {
        System.out.println("hello "+str);
    }
}
