package com.rain.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestRemoveList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("1");
        list.add("1");
        list.add("2");
        list.add("1");
        list.add("3");


        for(int i=list.size() -1;i>=0;i--){
            if(list.get(i).equals("1")){
                list.remove(i);
            }
        }


        for(String i :list){
            System.out.println(i);

        }



    }


    public static String getFileSize(File file) {
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

}
