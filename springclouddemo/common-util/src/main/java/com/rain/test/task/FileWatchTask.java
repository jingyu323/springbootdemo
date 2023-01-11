package com.rain.test.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;

public class FileWatchTask  implements Runnable{

    public String fileDirectory;
    public static final Logger logger = LoggerFactory.getLogger(FileWatchTask.class);

    public FileWatchTask(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }


    public void run() {
        logger.info("watch is start....");
        WatchService service = null;
        try {
            // 获取当前文件系统的监控对象
            service = FileSystems.getDefault().newWatchService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // 获取文件目录下的Path对象注册到 watchService中。
            // 监听的事件类型，有创建，删除，以及修改
            Paths.get(fileDirectory).register(service, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            logger.info("watch22222 is start....");
            WatchKey key = null;
            try {
                // 获取可用key.没有可用的就wait
                key = service.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (WatchEvent<?> event : key.pollEvents()) {
                // todo
                logger.info(event.context() + "文件:" + event.kind() + "次数: " + event.count());
            }
            // 重置，这一步很重要，否则当前的key就不再会获取将来发生的事件
            boolean valid = key.reset();
            // 失效状态，退出监听
            if (!valid) {
                break;


            }
            logger.info("dddddddddddddd....................");
        }

    }

}
