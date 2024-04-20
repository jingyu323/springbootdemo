package com.rain.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureManager {

    public String execute() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<String> future = executor.submit(() -> {
            System.out.println(" --- task start --- ");
            Thread.sleep(3000);
            System.out.println("333333333333333333333");
            System.out.println(" --- task finish ---");
            return "this is future execute final result!!!";
        });

        //这里需要返回值时会阻塞主线程
        return future.get();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("1111111111111111111111");
        FutureManager manager = new FutureManager();
        manager.execute();
        System.out.println("222222222222222222222");
    }
}
