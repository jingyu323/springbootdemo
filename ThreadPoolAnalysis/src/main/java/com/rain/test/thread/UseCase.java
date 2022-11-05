package com.rain.test.thread;

public class UseCase {

    public  @interface UseCases{
        public String id();

        public String description() default "no description";
    }
}
