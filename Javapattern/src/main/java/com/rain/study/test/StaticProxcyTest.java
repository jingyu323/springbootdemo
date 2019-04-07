package com.rain.study.test;

import com.rain.study.IHello;
import com.rain.study.StatProxcy;
import com.rain.study.impl.Hello;

public class StaticProxcyTest {



  public static void main(String[] args) {
    IHello hello = new StatProxcy(new Hello());

    hello.sayHello("Static proxcy test");

  }
}
