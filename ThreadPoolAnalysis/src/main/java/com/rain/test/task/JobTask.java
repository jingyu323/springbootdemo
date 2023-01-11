package com.rain.test.task;

import java.util.concurrent.Callable;

public class JobTask  implements Callable {
   volatile  static  int count =0;
    @Override
    public Object call() throws Exception {

        Thread.sleep(2000);
        System.out.println("count="+count++);
        return true;
    }
}
