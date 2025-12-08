package com.rain.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TetSys {
    public static void main(String[] args)  {

        String path = "D:/tool";
        File root = new File(path);
        if(root.exists()){
            FileInfo fileInfo= folderMethod2(path,null);
            if(fileInfo != null){
                printFileInfo(  fileInfo);
            }
        }

    }

    public static  void  printFileInfo(FileInfo fileInfo){
        if(fileInfo != null){
            System.out.println(fileInfo.filePath);
            if(fileInfo.childFiles != null){
                for(FileInfo finfo: fileInfo.getChildFiles()){
                    printFileInfo(finfo);
                }
            }
        }
    }

    public static FileInfo folderMethod2(String path,FileInfo fileInfo) {
        File file = new File(path);
        if (file.exists()) {
            if(fileInfo == null){
                fileInfo = createFileInfo(  file);
            }
            File[] files = file.listFiles();
            if (null != files) {
                List<FileInfo> childs =   new ArrayList<>();
                fileInfo.setChildFiles(childs);
                for (File file2 : files) {
                    FileInfo  chFile = createFileInfo(  file2);
                    childs.add(chFile);
                    if (file2.isDirectory()) {
                        folderMethod2(file2.getAbsolutePath(),chFile);
                    }
                }
            }else {
                FileInfo  singlefileInfo = createFileInfo(  file);
                if(fileInfo.childFiles == null){
                   List childs =   new ArrayList<>();
                    fileInfo.childFiles = childs;
                }
                fileInfo.childFiles.add(singlefileInfo);
            }
        }
    return  fileInfo;
    }


    private  static FileInfo  createFileInfo(File file){
        FileInfo  fileInfo = new FileInfo();
        fileInfo.setFileName(file.getName());
        fileInfo.setFilePath(file.getPath());
        return  fileInfo;
    }
    private  static List<FileInfo>  createFileInfoList(File[] files){
        List<FileInfo> childs  = new ArrayList<>();
        for (File file : files) {
            FileInfo  fileInfo = createFileInfo(  file);
            childs.add(fileInfo);
        }
        return  childs;
    }


}
