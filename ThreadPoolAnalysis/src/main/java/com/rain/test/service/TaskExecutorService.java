package com.rain.test.service;

import com.rain.test.controller.TestThreadController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class TaskExecutorService {


    /**
     *  workQueue 工作队列
     * 新任务被提交之后，会先进入到此工作队列中，任务调度时再从队列中取出任务。jdk一共提供了四种工作队列。
     *
     * ArrayBlockingQueue 数组型阻塞队列：数组结构，初始化时传入大小，有界，FIFO（先进先出），使用一个重入锁，默认使用非公平锁，入队和出队共用一个锁，互斥。
     * LinkedBlockingQueue 链表型阻塞队列：链表结构，默认初始化大小为Integer.MAX_VALUE，有界（近似无解），FIFO，使用两个重入锁分别控制元素的入队和出队，用Condition进行线程间的唤醒和等待。
     * SynchronousQueue 同步队列：容量为0，添加任务必须等待取出任务，这个队列相当于通道，不存储元素。
     * PriorityBlockingQueue 优先阻塞队列：无界，默认采用元素自然顺序升序排列。
     * DelayQueue 延时队列：无界，元素有过期时间，过期的元素才能被取出。
     */

    private final Logger log = LoggerFactory.getLogger(TaskExecutorService.class);

    ThreadPoolExecutor pool=new ThreadPoolExecutor(2, 4, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(20));



    public void addTask(Callable task){

     int cpus=   Runtime.getRuntime().availableProcessors();

       Future fu =   pool.submit(task);

        int activeCount = this.pool.getActiveCount();
        int corePoolSize = this.pool.getCorePoolSize();
        int maximumPoolSize = this.pool.getMaximumPoolSize();
        int queueTaskSize = this.pool.getQueue().size();
        long taskCount = this.pool.getTaskCount();
        long complateCount = this.pool.getCompletedTaskCount();
        log.info(" 活跃线程数峰值 = {}, 队列任务数峰值 = {}, " +
                        "核心线程数 = {}, 最大线程数 = {}, 执行的任务总数 = {},完成的任务总数 = {}",
                  activeCount, queueTaskSize, corePoolSize, maximumPoolSize, taskCount,complateCount);







        log.info("task submit sucess,"+ pool.getActiveCount()+",cpu count is:"+cpus);

    }

}
