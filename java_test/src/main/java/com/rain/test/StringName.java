package com.rain.test;


import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class StringName {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    public static void main(String[] args) {

        String name = "8_10X3005.jpg";
        String[] names = name.split("X");

        System.out.println(Integer.valueOf(name.split("X")[1].substring(1, 4)));
        System.out.println("L" + name.split("X")[1].substring(0, 1));
        System.out.println(Integer.valueOf(name.split("_")[0]));


        Map<String,Double> map = new HashMap<>();

                map.put("主排障器装饰扣-主排障器装饰扣正常", 0.2459955328660397);
        map.put("主排障器装饰扣-主排障器装饰扣丢失",  0.7540044671339604);
        String keyWithMaxValue = map.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("Key with Max Value: " + keyWithMaxValue);

        System.out.println(names.length);
        if (names.length >2&&names[names.length-1].endsWith(".jpg")){
            for (String string : names) {
                System.out.println(string);
            }
        }


        System.out.println("异物".endsWith("异物"));


        List<String> vchanels = Arrays.asList("L5", "L6", "L7", "L8", "L9");
        if(vchanels.contains("L5")){
            System.out.println(66666);

        }

        String saveDirectory = Paths.get("sss", new String[] {"ssssdddd", "message" }).toString();

        System.out.println(saveDirectory);

        String time = format.format(new Date());

        System.out.println(time.substring(8));
        System.out.println(time.substring(0,8));


    }

    private static BufferedImage resizeImage(BufferedImage originalImage, String rotate, int targetWidth, int targetHeight, String isHorz, String isVert) {
        ImageProcessor ip = new ImageProcessor();
        originalImage = ImageProcessor.rotate(originalImage, Integer.parseInt(rotate));
        originalImage = ip.flip(originalImage, Boolean.parseBoolean(isHorz), Boolean.parseBoolean(isVert));
        return originalImage;
    }
}
