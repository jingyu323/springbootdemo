package com.rain.test;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class TestCollectCPU {
    public static void main(String[] args) {


    }

    public float getPatitionUsage(String path){
        File f = new File(path);
        long total = f.getTotalSpace();
        long free = f.getFreeSpace();
        long used = total - free;
        float usage = (float)used/total;
        return usage;
    }

    public float getHdIOpPercent() {
        System.out.println("开始收集磁盘IO使用率");
        float ioUsage = 0.0f;
        Process pro = null;
        Runtime r = Runtime.getRuntime();
        try {
            String command = "iostat -d -x";
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line = null;
            int count = 0;
            while((line=in.readLine()) != null){
                if(++count >= 4){
// System.out.println(line);
                    String[] temp = line.split("\\s+");
                    if(temp.length > 1){
                        float util = Float.parseFloat(temp[temp.length-1]);
                        ioUsage = (ioUsage>util)?ioUsage:util;
                    }
                }
            }
            if(ioUsage > 0){
                System.out.println("本节点磁盘IO使用率为: " + ioUsage);
                ioUsage /= 100;
            }
            in.close();
            pro.destroy();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            System.out.println("IoUsage发生InstantiationException. " + e.getMessage());
            System.out.println(sw.toString());
        }
        return ioUsage;
    }

}
