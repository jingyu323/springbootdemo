package com.rain.test.thread;

import com.rain.test.task.CreateTaskThread;
import com.rain.test.task.JobTask;

import java.util.Date;
import java.util.concurrent.*;


/***
 * 为什么要用线程池？
 * 
 * 1.创建/销毁线程伴随着系统开销，过于频繁的创建/销毁线程，会很大程度上影响处理效率
 * 
 * 2.线程并发数量过多，抢占系统资源从而导致阻塞
 * 
 * 3.对线程进行一些简单的管理 比如：延时执行、定时循环执行的策略等 运用线程池都能进行很好的实现
 * 
 * 
 * 
 * 
 * public class ThreadPoolExecutor extends AbstractExecutorService
 * 
 * public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long
 * keepAliveTime,TimeUnit unit, BlockingQueue<Runnable> workQueue);
 * 
 * public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long
 * keepAliveTime,TimeUnit unit, BlockingQueue<Runnable> workQueue,ThreadFactory
 * threadFactory);
 * 
 * public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long
 * keepAliveTime,TimeUnit unit, BlockingQueue<Runnable>
 * workQueue,RejectedExecutionHandler handler);
 * 
 * public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long
 * keepAliveTime,TimeUnit unit, BlockingQueue<Runnable> workQueue,ThreadFactory
 * threadFactory,RejectedExecutionHandler handler);
 * 
 * corePoolSize：核心池的大小
 * 
 * 
 * maximumPoolSize：线程池最大线程数，这个参数也是一个非常重要的参数，它表示在线程池中最多能创建多少个线程
 * 
 * keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止。默认情况下，只有当线程池中的线程数大于corePoolSize时，
 * 
 * keepAliveTime 才会起作用，直到线程池中的线程数不大于corePoolSize，即当线程池中的线程数大于corePoolSize时，
 * 如果一个线程空闲的时间达到keepAliveTime
 * ，则会终止，直到线程池中的线程数不超过corePoolSize。但是如果调用了allowCoreThreadTimeOut
 * (boolean)方法，在线程池中的线程数不大于corePoolSize时，keepAliveTime参数也会起作用，直到线程池中的线程数为0；
 * 
 * 
 * unit：参数keepAliveTime的时间单位
 * 
 * workQueue：一个阻塞队列，用来存储等待执行的任务，这个参数的选择也很重要，会对线程池的运行过程产生重大影响 ArrayBlockingQueue;
 * LinkedBlockingQueue; SynchronousQueue;
 * 
 * threadFactory：线程工厂，主要用来创建线程；
 * 
 * 创建线程的方式，这是一个接口，你new他的时候需要实现他的Thread newThread(Runnable r)方法，一般用不上
 * 
 * 
 * handler：表示当拒绝处理任务时的策略
 * 
 * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
 * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
 * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
 * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
 * 
 * 
 * 向ThreadPoolExecutor添加任务
 * 那说了这么多，你可能有疑惑，我知道new一个ThreadPoolExecutor，大概知道各个参数是干嘛的，
 * 可是我new完了，怎么向线程池提交一个要执行的任务啊？
 * 
 * 通过ThreadPoolExecutor.execute(Runnable command)方法即可向线程池内添加一个任务
 * 
 * 
 * 常见四种线程池
 * 
 * CachedThreadPool() 可缓存线程池：
 * 
 * 线程数无限制 有空闲线程则复用空闲线程，若无空闲线程则新建线程 一定程序减少频繁创建/销毁线程，减少系统开销 创建方法：
 * 
 * ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
 * 
 * @author Rain
 *
 */
public class ThreadPoolTest {

	public static void main(String[] args) {

		ExecutorService pool=new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(20)) {	};

		CreateTaskThread tt = new CreateTaskThread(pool);
		Thread st = new Thread(tt);
		st.start();

	}

	// 创建可缓存的线程池
	public void testCreateThreadPool() {
		// 创建一个可缓存的线线城市
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {
			final int index = i;
			try {
				Thread.sleep(index * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cachedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(index+":"+new Date().getSeconds());
				}
			});



		}
	}

	/**
	 * 定长线程池：
	 * 
	 * 1.可控制线程最大并发数（同时执行的线程数） 2. 超出的线程会在队列中等待
	 */
	public void testCreateFixedThreadPool() {
		
		// nThreads => 最大线程数即maximumPoolSize
		// ExecutorService fixedThreadPool = Executors.newFixedThreadPool(int
		// nThreads);

		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

		for(int i = 1; i < 11; i++){
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					//会按顺序打印
					System.out.println("testCreateFixedThreadPool"+index+Thread.currentThread().getName());
				}
			});
		}

	}

	/***
	 * 定长线程池：
	 * 
	 * 支持定时及周期性任务执行。
	 */

	public void tetCreateScheduledThreadPool() {
		// nThreads => 最大线程数即maximumPoolSize
		// ExecutorService scheduledThreadPool =
		// Executors.newScheduledThreadPool(int corePoolSize);
		ExecutorService scheduledThreadPool = Executors
				.newScheduledThreadPool(100);
	}

	/**
	 * 单线程化的线程池：
	 * 
	 * 有且仅有一个工作线程执行任务 所有任务按照指定顺序执行，即遵循队列的入队出队规则
	 */
	public void testCreateSingleThreadExecutor() {
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

	}

}
