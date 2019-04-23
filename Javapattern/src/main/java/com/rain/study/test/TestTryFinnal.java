package com.rain.study.test;

public class TestTryFinnal {

  public static void main(String[] args) {
    //

      TestTryFinnal t1 =  new TestTryFinnal();

      int b  = t1.get();

    System.out.println(b);
  }

  public int get(){
      try{
          return  1;
      }finally{
          return 2;
      }
  }
}
