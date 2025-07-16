package com.rain.test.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test001 {
    public static void main(String[] args) {
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

    }
}
