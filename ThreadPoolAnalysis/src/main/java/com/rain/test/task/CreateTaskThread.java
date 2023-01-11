package com.rain.test.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class CreateTaskThread implements Runnable {
    ExecutorService pool;

    public CreateTaskThread(ExecutorService pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            JobTask task =  new JobTask();
            Future fu = pool.submit(task);

            System.out.println("task add success");
            if(fu.isDone()){
                System.out.println("fu:"+fu.toString());
            }


        }

    }
}
