package com.rain.test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TestMap {
    public static void main(String[] args) {

       String PATH = "D:\\home\\videoLink";
       String FTP_PATH ="D:\\home\\video\\1234";

        String sss  = "D:/home/video/1234";


        String path = sss.replace(FTP_PATH, PATH);

        File file  = new File(path);
        long res= FileUtils.sizeOf(file);

        String sss222 = getFileSize1(file);
        String sss2223 = getFileSize(file);
        System.out.println(sss222);
        System.out.println(sss2223);
        System.out.println(path);
        System.out.println(res);



    }


    public static String getFileSize1(File file) {
        String size = "";
        long fileS = org.apache.commons.io.FileUtils.sizeOf(file);
        if (fileS < 1024) {
            size = fileS + "B";
        } else if (fileS < 1048576) {
            size = fileS / 1024.0 + "KB";
        } else if (fileS < 1073741824) {
            size = fileS / 1048576.0 + "MB";
        } else {
            size = fileS / 1073741824.0 + "GB";
        }
        return size;
    }

    public static String getFileSize(File file) {
        String size = "";
        long fileS = org.apache.commons.io.FileUtils.sizeOf(file);

        FileUtils.sizeOfDirectory(file);
        if (fileS < 1024) {
            size =  fileS + "B";
        } else if (fileS < 1048576) {
            size = String.format("%.2f", fileS / 1024.0) + "KB";
        } else if (fileS < 1073741824) {
            size = String.format("%.2f", fileS / 1048576.0 ) + "MB";
        } else {
            size = String.format("%.2f", fileS / 1073741824.0)  + "GB";
        }
        return size;
    }


}
