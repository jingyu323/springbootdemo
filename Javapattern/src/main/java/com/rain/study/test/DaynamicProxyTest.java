package com.rain.study.test;

import com.rain.study.DynaProxyHello;
import com.rain.study.IHello;
import com.rain.study.impl.Hello;

public class DaynamicProxyTest {

  public static void main(String[] args) {


      IHello hell =(IHello) new DynaProxyHello().bind(new Hello());

      hell.sayHello("this is  dynamic proxcy");


  }
}
