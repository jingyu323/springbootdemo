package com.rain.test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TestRename {
    public static void main(String[] args) {

        String path = "E:\\video\\";
//        File file = new File(path);
//        System.out.println(file.getPath());
//        System.out.println(file.getName());
//
//
//        System.out.println(file.getParentFile().getPath());
//        System.out.println(file.getAbsolutePath());
//
//        String updatePath = file.getParentFile().getPath()+File.separator+System.currentTimeMillis()+"_"+file.getName();
//        System.out.println("file:"+updatePath);
//        File newFile = new File(updatePath);
//        file.renameTo(newFile);
//        renameFile(path);



        Collection<String> collection = new ArrayList<>();
        collection.add("张三");
        collection.add("李四");
        collection.add("王五");

        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if(name.equals("李四")){
                iterator.remove();
                continue;
            }
            System.out.println(name);


            // 通过集合的add方法添加元素，会导致ConcurrentModificationException异常
            if ("李四".equals(name)) {
                collection.add("赵六");  // 这里会抛出异常
            }
        }





    }
    public  static  void  renameFile(String path){
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if(file2.isDirectory()){
                        renameFile(file2.getPath());
                    } else {
                        String updatePath = file2.getParentFile().getPath()+File.separator+System.currentTimeMillis()+"_"+file2.getName();
                        System.out.println("file2:"+updatePath);
                        File newFile = new File(updatePath);
                        file2.renameTo(newFile);
                    }

                }
            }else {
                String updatePath = file.getParentFile().getPath()+File.separator+System.currentTimeMillis()+"_"+file.getName();
                System.out.println("file2:"+updatePath);
                File newFile = new File(updatePath);
                file.renameTo(newFile);
            }
        }
    }
}
