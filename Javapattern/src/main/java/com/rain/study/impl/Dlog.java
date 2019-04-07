package com.rain.study.impl;

import com.rain.study.ILogger;

import java.lang.reflect.Method;
import java.util.Date;

public class Dlog implements ILogger {
    @Override
    public void start(Method method) {
        System.out.println(new Date()+ method.getName() + "   -->> say hello start...");
    }

    @Override
    public void end(Method method) {
        System.out.println(new Date()+ method.getName() + " -->>   say hello end");
    }
}
