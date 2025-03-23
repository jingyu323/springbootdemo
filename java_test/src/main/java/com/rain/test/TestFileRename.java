package com.rain.test;

import java.io.File;

public class TestFileRename {
    public static void main(String[] args) {
        filenmecheck();
    }
    public  static  void getFileList(){
        String filePath2 = "E:\\home\\htkj\\dataLabelRootPath\\testLabel001";
        File file = new File(filePath2);
        File[] files = file.listFiles();
        for(File f:files){
            if(f.isDirectory()){
                File[] subfiles = f.listFiles();
                for(File subfile:subfiles){
                    System.out.println(subfile.getAbsoluteFile());
                }
            }
            System.out.println(f.getAbsoluteFile());
        }
    }
    public static void renameFileFolder(){
        String filePath = "d:/sss111";
        String filePath2 = "d:/sss222";

        File file = new File(filePath);
        boolean res = file.renameTo(new File(filePath2));
        System.out.println(res);
    }


    public static void filenmecheck(){
        String filePath = "2_10X3023.jpg";
//        String filePath = "1_10X1002.jpg";


        if (filePath.split("X").length == 2) {
            System.out.println(filePath.split("X")[1].substring(1, 4));
            System.out.println(filePath.split("X")[1]);
            System.out.println("L" + filePath.split("X")[1].substring(0, 1));
            System.out.println(filePath.split("_")[0]);
            System.out.println(filePath.split("_")[0]);
        }else{
            System.out.println("========"+filePath.split("X").length);
        }



    }




}
