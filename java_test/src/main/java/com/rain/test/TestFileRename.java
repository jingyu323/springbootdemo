package com.rain.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rain.test.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static com.rain.test.util.Base64.GetImageStr;

public class TestFileRename {
    public static void main(String[] args) {
        fileToJson();
    }

    public static void  fileToJson(){

        String path = "F:\\ht\\TF0100181\\pic\\20250322\\010101_1\\";
        String path1 = "F:\\ht\\TF0100181\\pic\\20250322\\010101_1\\1_10X1003.jpg";


        System.out.println(System.currentTimeMillis());
        System.out.println(path1.indexOf("pic"));
        System.out.println(path1.substring(path1.indexOf("pic")+"pic".length()+1));


        File file = new File(path);
        File[] files = file.listFiles();

        String camerNo = "5";

        switch (camerNo) {
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                System.out.println(camerNo);
        }

        int valeu = (int) (0.3*10/1);

        double start = 0;
        double end = 1;
        TreeMap<String, List> intervalDataMap = new TreeMap<>();
        double stepLength = 0.1;
        while (start < end) {
            double tmp =  add(start,stepLength) ;
            String key = start + "_" + tmp;
            List list = new ArrayList();
            list.add(start);

            list.add(tmp);
            list.add(0);
            list.add(0);
            intervalDataMap.put(key, list);
            start = tmp;
        }



     for (int i = 0; i < 10; i++) {
         double di = i *0.1;
         for (Map.Entry entry : intervalDataMap.entrySet()) {

             List list = (List) entry.getValue();
             if(di >= (double)list.get(0) && di < (double)list.get(1)){
                 int suc = (int)list.get(2);
                 list.set(2,suc+1);
                 break;
             }

         }

     }

        for (Map.Entry entry : intervalDataMap.entrySet()) {

            List list = (List) entry.getValue();

            System.out.println(JSONObject.toJSONString(list));
        }

        System.out.println(intervalDataMap.values());


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

//            genJsonFile(    fileMap);
        readJsonFile();
    }

    public static void  readJsonFile(){
        String jsonPath = "E:\\study\\git\\springbootdemo\\java_test\\vehicle_1-camera_1.json";

        File file = new File(jsonPath);
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(jsonPath)));

//            System.out.println(content);

            JSONObject requestBody =new JSONObject().parseObject(content);
            System.out.println(requestBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void genJsonFile(  Map<String, List<File>> fileMap){
        for (Map.Entry<String, List<File>> entry : fileMap.entrySet())  {
            String key = entry.getKey();
            List<File> fileList = entry.getValue();

            System.out.println(key+":");
            JSONObject jb=  buildJsonRequestBody(fileList);

            FileUtils.writeJson(key+".json", jb);
        }
    }

    public static Double add(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.add(b2).doubleValue();
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
