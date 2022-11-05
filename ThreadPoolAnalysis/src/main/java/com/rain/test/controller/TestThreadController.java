package com.rain.test.controller;


import com.rain.test.service.TaskExecutorService;
import com.rain.test.task.JobTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestThreadController {

    private final Logger log = LoggerFactory.getLogger(TestThreadController.class);

    @Autowired
    TaskExecutorService taskExecutorService;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/addTask")
    public String addTask() {
        JobTask task =  new JobTask();
        log.info("start add task...");
        taskExecutorService.addTask(task);

        return "success";
    }


}
