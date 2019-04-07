package com.rain.study;

import java.lang.reflect.Method;

public interface  ILogger {
    void start(Method method);
   void end(Method method);
}
