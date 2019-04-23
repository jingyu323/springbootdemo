package com.rain.study.test;

public class TestNonStatic {

    public  String testMethod(){
        return  "test method";
    }

  public static void main(String[] args) {
    //
      TestNonStatic t1 = new TestNonStatic();

        System.out.println(t1.testMethod() + " --->> "+t1.testMethod());
  }
}
