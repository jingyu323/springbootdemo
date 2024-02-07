package com.rain.test.thread;


import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadUtils {

    public static void main(String[] args) {
        //首先通过MoreExecutors类的静态方法listeningDecorator方法初始化一个ListeningExecutorService的方法，
        // 然后使用此实例的submit方法即可初始化ListenableFuture对象。
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        final ListenableFuture<Integer> listenableFuture = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("callable execute...");
                TimeUnit.SECONDS.sleep(1);
                return 1;
            }
        });
        //ListenableFuture要做的工作，在Callable接口的实现类中定义，
        // 这里只是休眠了1秒钟然后返回一个数字1，有了ListenableFuture实例，
        // 可以执行此Future并执行Future完成之后的回调函数。
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                //成功执行...
                System.out.println("Get listenable future's result with callback " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                //异常情况处理...
                t.printStackTrace();
            }
        }, Executors.newSingleThreadExecutor());
    }


}
