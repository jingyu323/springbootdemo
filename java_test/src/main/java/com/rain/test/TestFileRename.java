package com.rain.test;

import com.rain.test.util.Base64;

import java.io.File;

public class TestFileRename {
    public static void main(String[] args) {
        fileToJson();
    }

    public static void  fileToJson(){

        String path = "F:\\ht\\J85F02F06\\pic\\20250325\\113505_1\\";

        long start = System.currentTimeMillis();
        File file = new File(path);
        File[] files = file.listFiles();

        for (File f : files) {
            System.out.println(Base64.GetImageStr(f.getAbsolutePath()));
        }


        System.out.println(System.currentTimeMillis()-start);


        long start2 = System.currentTimeMillis();

        File file2 = new File(path);
        File[] files2 = file2.listFiles();

        for (File f : files2) {
            System.out.println(Base64.GetImageStr(f.getAbsolutePath()));
        }


        System.out.println(System.currentTimeMillis()-start2);



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
