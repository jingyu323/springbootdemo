package com.rain.test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Date;

public class FileTimeTest {
    private static final long HOUR = 24;
    private static final long MINUTE = 60;
    private static final long SECOND = 60;
    private static final long MMCOND = 1000;

    @Test
    public void testFileTime() {
        File test = new File("E:\\study\\git\\springbootdemo\\mysql_study\\src\\main\\resources\\sql\\mysql_study.sql");
        long currTime = System.currentTimeMillis(); // 当前时间
        long lastTime = test.lastModified(); // 文件被最后一次修改的时间

        System.out.println(lastTime);
        System.out.println(new Date(lastTime));
        long diffen = currTime - lastTime;
        long thDay = 2 * HOUR * MINUTE * SECOND * MMCOND;

        System.out.println("diffen=" + diffen);
        System.out.println("thDay=" + thDay);


    }
}
