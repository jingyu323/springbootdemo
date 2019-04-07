package com.rain.study.test;

import com.rain.study.DynaProxyHelloSepLog;
import com.rain.study.IHello;
import com.rain.study.impl.Dlog;
import com.rain.study.impl.Hello;

public class DynaProxyHelloSepLogTest {

  public static void main(String[] args) {

      IHello hell = (IHello) new DynaProxyHelloSepLog().bind(new Dlog(),new Hello());

      hell.sayHello("log sep test");




  }
}
