package com.rain.test;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateFile {

    public static final int IS_DIR_YES = 1;
    public static final int IS_DIR_NO = 0;
    public static void main(String[] args) throws SQLException {
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/123?useUnicode=true&characterEncoding=utf8&mysqlEncoding=utf8&serverTimezone=GMT%2B8","root","root");
//        //定义sql语句
//        String sql = "select * from  htgw_file_info";
//        //获取执行sql的对象Statement
//        Statement stmt = conn.createStatement();
//        //执行sql
//        ResultSet resultSet = stmt.executeQuery(sql);
//        //处理结果
//        while(resultSet.next()) {                                     //让游标移动到下一行,true 表示有数据行,可以到下一行
//            int id = resultSet.getInt(1);                    //获取id
//            String name = resultSet.getString("file_name");             //获取姓名
//            System.out.println("id:" + id + " file_name:" + name);
//        }
//        //释放资源
//        stmt.close();
//        conn.close() ;
        System.out.println("sssss");
        long start = System.currentTimeMillis();
        String filePath ="F:\\";
        HtgwFileInfo fileInfo = getAllFileTree(filePath,null);
        long end = System.currentTimeMillis();


        long differ = end -start;
        System.out.println("time cost:"+differ);

    }

    public  static  void  renameFile(String path){
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if(file2.isDirectory()) renameFile(file2.getPath());
                    String updatePath = file2.getParentFile().getPath()+File.separator+System.currentTimeMillis()+"_"+file2.getName();
                    System.out.println("file2:"+updatePath);
                    File newFile = new File(updatePath);
                    file2.renameTo(newFile);
                }
            }
        }
    }

    public static HtgwFileInfo getAllFileTree(String path, HtgwFileInfo fileInfo) {
        File file = new File(path);
        if (file.exists()) {
            if (fileInfo == null) {
                fileInfo = createFileInfo(file);
            }
            File[] files = file.listFiles();
            if (null != files) {
                List<HtgwFileInfo> childs = new ArrayList<>();
                fileInfo.setChildFiles(childs);
                for (File file2 : files) {
                    HtgwFileInfo chFile = createFileInfo(file2);
                    System.out.println(chFile.getFileName()+":"+chFile.getFilePath());
                    childs.add(chFile);
                    if (file2.isDirectory()) {
                        getAllFileTree(file2.getAbsolutePath(), chFile);
                    }

                }
            } else {
                HtgwFileInfo singlefileInfo = createFileInfo(file);
                System.out.println(singlefileInfo.getFileName());
                if (fileInfo.getChildFiles() == null) {
                    List childs = new ArrayList<>();
                    fileInfo.setChildFiles(childs);
                }
                fileInfo.getChildFiles().add(singlefileInfo);
            }
        }
        return fileInfo;
    }


    public static HtgwFileInfo createFileInfo(File rootFile) {
        HtgwFileInfo fileInfo = new HtgwFileInfo();
        fileInfo.setFilePath(rootFile.getPath());
        fileInfo.setFileName(rootFile.getName());
        fileInfo.setCreateTime(new Date(rootFile.lastModified()));
        if (rootFile.isDirectory()) {
            fileInfo.setIsDir(1);
        } else {
            fileInfo.setIsDir(0);
        }
        String fileSize =  getFileSize(rootFile);
        fileInfo.setFileSize(fileSize);
        return fileInfo;
    }


    public static HtgwFileInfo buildRootFileInfo(String rootPath, HtgwFileInfo child) {
        File rootFile = new File(rootPath);
        HtgwFileInfo fileInfo = createFileInfo(rootFile);
        List childs = new ArrayList<>();
        childs.add(child);
        fileInfo.setChildFiles(childs);
        return fileInfo;
    }

    public static String getFileSize(File file) {
        String size = "";
        long fileS = org.apache.commons.io.FileUtils.sizeOf(file);
        if (fileS < 1024) {
            size = fileS + "B";
        } else if (fileS < 1048576) {
            size = String.format("%.2f", fileS / 1024.0) + "KB";
        } else if (fileS < 1073741824) {
            size = String.format("%.2f", fileS / 1048576.0) + "MB";
        } else {
            size = String.format("%.2f", fileS / 1073741824.0) + "GB";
        }
        return size;
    }

}
