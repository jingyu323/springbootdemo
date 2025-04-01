package com.rain.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rain.test.util.Base64;
import com.rain.test.util.FileUtils;

import java.io.File;
import java.security.KeyStore;
import java.util.*;

import static com.rain.test.util.Base64.GetImageStr;

public class TestFileRename {
    public static void main(String[] args) {
        fileToJson();
    }

    public static void  fileToJson(){

        String path = "F:\\ht\\J85F02F06\\pic\\20250325\\113505_1\\";

        long start = System.currentTimeMillis();
        File file = new File(path);
        File[] files = file.listFiles();


        Map<String, List<File>> fileMap = new HashMap<>();

        for (File f : files) {

            String fileName = f.getName().substring(0,f.getName().lastIndexOf("X")+2);

//            vehicle_2-camera_2
            String jsonName = "vehicle_"+fileName.split("_")[0]+"-camera_"+fileName.split("X")[1]; ;



            List<File> fileList = fileMap.get(jsonName);
            if (fileList == null) {
                fileList = new ArrayList<>();
            }
            fileList.add(f);
            fileMap.put(jsonName, fileList);


        }


        for (Map.Entry<String, List<File>> entry : fileMap.entrySet())  {
            String key = entry.getKey();
            List<File> fileList = entry.getValue();

            System.out.println(key+":");
            JSONObject jb=  buildJsonRequestBody(fileList);

              FileUtils.writeJson(key+".json", jb);
        }


    }

    private static JSONObject buildJsonRequestBody(List<File> carList) {
        JSONObject requestBody = new JSONObject();
        JSONArray imgArr = new JSONArray();
        carList.forEach(item -> {
            JSONObject imgInfo = new JSONObject();
            String path = null;


            imgInfo.put("path", item.getAbsolutePath());
            imgInfo.put("image", GetImageStr(item.getAbsolutePath()));
            imgArr.add(imgInfo);
        });
        requestBody.put("input_image_info", imgArr);
        //apt tr
        requestBody.put("station_code","F999999999");
           requestBody.put("car_code", "ZYS374401");

        return requestBody;
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
