package com.rain.test.service;

import com.rain.test.controller.TestThreadController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class TaskExecutorService {


    private final Logger log = LoggerFactory.getLogger(TaskExecutorService.class);

    ExecutorService pool=new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(20)) {	};


    public void addTask(Callable task){

        pool.submit(task);

        log.info("task submit sucess");

    }

}
