package com.rain.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class KafkaTestController {


    private final static String TOPIC_NAME = "test";
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public void send() {
        kafkaTemplate.send(TOPIC_NAME, 0, "key", "this is a msg");
    }


    /**
     * 实现文件本地文件读取视频播放
     *
     * @param filename
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/playtest", method = RequestMethod.GET)
    public void playtest(String filename, HttpServletResponse response) throws IOException {


        String basePath = "E:\\work\\tool\\apache-tomcat-9.0.54\\upload";
        basePath = basePath + File.separator + filename;
        File videoFile = new File(basePath);

        response.setContentType("video/mp4");
        response.setHeader("Content-Length", String.valueOf(videoFile.length()));

        System.out.println("basePath = " + basePath);
        FileInputStream fileInputStream = new FileInputStream(videoFile);
        OutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        fileInputStream.close();
        outputStream.close();

    }
}
