package com.rain.study.test;

public class TestInnerOuter {

    private  class  InterClass{
        public  InterClass(){
             System.out.println("inner class  creater ");
        }
    }


    public  TestInnerOuter(){
        InterClass ic = new InterClass();

        System.out.println("outer class  creater ");
    }

  public static void main(String[] args) {
    //
      TestInnerOuter to = new TestInnerOuter();

  }
}
