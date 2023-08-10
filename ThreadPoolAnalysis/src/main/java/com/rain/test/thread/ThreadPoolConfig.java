package com.rain.test.thread;

import java.util.concurrent.*;

public class ThreadPoolConfig {

    /**
     * 核心线程数
     */
    private static final int corePoolSize = 5;
    /**
     * 最大线程数
     */
    private static final int maxPoolSize = 100;
    /**
     * 多余线程最大空闲时间
     */
    private static final int keepAlive = 30;

    /**
     * 线程池缓冲队列
     *  如果不手动指定容量，默认为Integer.MAX_VALUE，也就是无界队列。
     */
    private static final BlockingQueue poolQueue = new LinkedBlockingQueue(3000);


    private ThreadPoolConfig(){}

    private static volatile ThreadPoolExecutor poolExecutor;

    public static ThreadPoolExecutor getInstance(){
        if (poolExecutor == null){
            synchronized (ThreadPoolConfig.class){
                if (poolExecutor == null){
                    poolExecutor = new ThreadPoolExecutor(corePoolSize,
                            maxPoolSize,
                            keepAlive,
                            TimeUnit.SECONDS,
                            poolQueue,
                            new ThreadPoolExecutor.DiscardOldestPolicy());
                }
            }
        }
        poolExecutor.allowCoreThreadTimeOut(true);
        return poolExecutor;
    }


    public static String submit(ThreadPoolExecutor poolExecutor ,Callable callable) throws ExecutionException, InterruptedException {
        Future<String> future = poolExecutor.submit(callable);
        String result = future.get();
        return result;
    }
    public static Future submitNoRsult(ThreadPoolExecutor poolExecutor ,Callable callable) throws ExecutionException, InterruptedException {
        Future future = poolExecutor.submit(callable);
        return future;
    }

    public static void execute(Runnable runnable){
        poolExecutor.execute(runnable);
    }

    public static void shutdown(){
        poolExecutor.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        getInstance();
        String res = ThreadPoolConfig.submit(poolExecutor,() -> {
            System.out.println("do something...");

            Thread.sleep(3000);
            return "success";
        });


        System.out.println(res);
        poolExecutor.shutdown();

    }


}
