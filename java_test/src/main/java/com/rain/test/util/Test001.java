package com.rain.test.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test001 {

    private static final String SEP = File.separator;
    public static void main(String[] args) throws IOException {
        List<Integer> list =  new ArrayList<>();
        list.add(Integer.valueOf("1"));
        list.add(Integer.valueOf("3"));
        list.add(Integer.valueOf("6"));
        list.add(Integer.valueOf("5"));
        list.add(Integer.valueOf("4"));
        list.add(Integer.valueOf("2"));

        list.add(Integer.valueOf("7"));
        Collections.sort(list);
        System.out.println(list);

        String path = "E:\\home\\htkj\\dataLabelRootPath\\0906tianxianposun\\BTM天线-BTM天线正常\\00_592_CRH380AL-2919_L5_0608_BTM天线盒划痕_1320240412_P图.jpg".replaceAll("\\\\", "/");

        System.out.println(path.substring(0,path.lastIndexOf("/")+1));

        Long aaas = 80L;
        Long bb = 100L;

        System.out.println(aaas*100/bb);


        File imge = new File("F:/ht/0604-2/0604-34/构造-广州局实车故障构造-1217_CRH380A_0_5282-华兴致远/1_10X1001.jpg");

        System.out.println(imge);
        long l1 = System.currentTimeMillis();

        BufferedImage image = ImageIO.read(imge);

        String saveDirectory = Paths.get("F:/ht", new String[] { "pic" }).toString();
        File directory = new File(saveDirectory);
        if (!directory.exists())
            directory.mkdirs();
        File outputFile = new File(saveDirectory + SEP + "imageName.jpg");
        try(FileOutputStream fos = new FileOutputStream(outputFile); BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            ImageIO.write(image, "jpeg", bos);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("耗时："+ (System.currentTimeMillis() - l1));
        String  train = "01026_15_161_18_002_24_447_27_288";
        System.out.println(train.substring(1,2));

        String filePath =  "F:/xialongmen/state/ZT_sdfdf.data";
        String[] splitArray = filePath.replace("//","/").split("/");
        System.out.println(splitArray.length);
        for(int i=0;i<splitArray.length;i++){
            System.out.println(  splitArray[i]);
        }

        String data = "45";

        double  fileSize = Double.valueOf(data) / 1000;
        System.out.println(fileSize);

        System.out.println(String.format("%02d", 2));




    }
}
