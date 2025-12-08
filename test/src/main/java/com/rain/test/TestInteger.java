package com.rain.test;

import java.text.DecimalFormat;

public class TestInteger {
    public static void main(String[] args) {

        Integer ss = new Integer(1123);

        int ssss  = 1123;

        if(ssss == ss){
            System.out.println("ss");
        }

        String s = "258144";
        DecimalFormat df = new DecimalFormat("#.00");
        String  line = df.format(Double.parseDouble(s) / 1024/1024);
        System.out.println(line);


        System.out.println("#2".split("#").length);

        String playPa  = "/home/videoPlay";
//        htFile.setFilePlayUrl("http://"+serverIp+"/video"+fileInfo.getFilePath().substring(FileUtils.PLAY_PATH.length()));

        String filePath = "/home/videoPlay/1234/1234/13.mp4";

        System.out.println( filePath.substring(playPa.length()));

    }
    
    
    
}
