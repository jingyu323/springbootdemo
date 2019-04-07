package com.rain.study;

import java.util.Date;

public class Logger {
    public static void start(){
        System.out.println(new Date()+ " say hello start...");
    }

    public static void start(String str ){
        System.out.println(new Date()+ " say hello start..."+str);
    }

    public static void end(){
        System.out.println(new Date()+ " say hello end");
    }

    public static void end(String str){
        System.out.println(new Date()+ " say hello end"+str);
    }
}
