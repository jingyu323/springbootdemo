package com.rain.test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;

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

    @Test
    public void testRand() {


        int days = new Random().nextInt(10);

        System.out.println("diffen=" + days);


        File f = new File("/root");

        System.out.println(f.getName());


    }


    @Test
    public void testFileModifyTime() {

        File f = new File("E:\\study\\git\\springbootdemo\\mysql_study\\src\\test\\java\\com\\rain\\test\\hertest_05_客室3摄像头1_20231222_163000.mp4");

        System.out.println(new Date(f.lastModified()));

    }


    @Test
    public void testFileMove() throws IOException {
        File file1 = new File("path1");

        File file2 = new File("path2");

        //移动文件夹,并重新命名
        FileUtils.moveDirectory(new File("/Users/Downloads/file1"),
                new File("/Users/Downloads/file2/file3"));

        //移动文件夹，并给定是否重命名
        FileUtils.moveDirectoryToDirectory(new File("/Users/Downloads/file1"),
                new File("/Users/Downloads/file2/"), false);

        //移动文件到指定文件夹中,并重新命名
        FileUtils.moveFile(file1, new File("/Users/Downloads/海葡萄.jpen"));

        //移动文件到指定文件夹中，并给定是否创建文件夹
        FileUtils.moveFileToDirectory(new File("/Users/Downloads/海葡萄.jpeg"), new File("/Users/Downloads/file2"), false);


    }

    @Test
    public void testFileDelete() throws IOException {
        File file = new File("path1");
        File file2 = new File("path2");
        File directory = new File("path2");

        //递归删除一个目录(包括内容)。
        FileUtils.deleteDirectory(directory);
        //删除一个文件，不会抛出异常。如果文件是一个目录，删除它和所有子目录。
        FileUtils.deleteQuietly(file);
        //清理内容而不删除它。
        FileUtils.cleanDirectory(directory);
        //删除一个文件，会抛出异常 15 //如果file是文件夹，就删除文件夹及文件夹里面所有的内容。如果file是文件，就删除。
        // 16 //如果某个文件/文件夹由于某些原因无法被删除，会抛出异常
        FileUtils.forceDelete(file);
    }

    @Test
    public void testFileCopy() throws IOException, ParseException {
        File file1 = new File("path1");
        File file2 = new File("path2");
        File file3 = new File("path2");
        File file8 = new File("path2");
        File file4 = new File("path2");
        File file13 = new File("path2");
        File directory = new File("path2");
        FileUtils.deleteDirectory(directory);

        File file = new File("path");
        //创建一个文件夹，如果由于某些原因导致不能创建，则抛出异常
        //一次可以创建单级或者多级目录
        FileUtils.forceMkdir(new File("/Users/wuguibin/Downloads/folder"));
        //为指定文件创建文件的父级目录
        FileUtils.forceMkdirParent(file);

        //确定父目录是否包含指定子元素(一个文件或目录)。即directory是否包含file2,在比较之前，文件是标准化的。
        boolean a = FileUtils.directoryContains(directory, file2);
        //比较两个文件的内容，以确定它们是否相同。
        boolean b = FileUtils.contentEquals(file1, file2);
        //获取指定文件或文件夹大小，有可能溢出，变为负值
        long l = FileUtils.sizeOf(file1);
        System.out.println(l + "KB");
        //获取指定文件或文件夹大小，不溢出
        BigInteger bi = FileUtils.sizeOfAsBigInteger(file1);
        System.out.println(bi + "kb");

        //递归地计算一个目录的大小(所有文件的长度的总和)。
        //注意，sizeOfDirectory（）没有检测溢出，如果溢出发生，返回值可能为负。sizeOfDirectoryAsBigInteger()方法则不溢出。
        FileUtils.sizeOfDirectory(file1);
        FileUtils.sizeOfDirectoryAsBigInteger(file1);
        //比较指定文件是否比参考文件创建或修改后时间晚
        boolean b2 = FileUtils.isFileNewer(file1, file2);

        //如果指定的文件比指定的日期更新。
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        String date1 = "2017/06/20";
        boolean c = FileUtils.isFileNewer(file1, date.parse(date1));
        boolean d = FileUtils.isFileNewer(file1, 23243);

        //指定文件创建或修改后的时间是否比参考文件或日期早
        FileUtils.isFileOlder(file1, 232434);
        FileUtils.isFileOlder(file1, System.currentTimeMillis());

        //把集合里面的内容写入文件，以指定字符串结束写入
        //void writeLines(File file,Collection<?> lines,String lineEnding,boolean append)
        ArrayList<String> list = new ArrayList<>();
        String str1 = "Java";
        String str2 = "JSP";
        list.add(str1);
        list.add(str2);
        FileUtils.writeLines(file8, "GBK", list, "java", true);

        //把字符串写入文件
        //参数1：需要写入的文件，如果文件不存在，将自动创建。  参数2：需要写入的内容
        //参数3：编码格式     参数4：是否为追加模式（ ture: 追加模式，把字符串追加到原内容后面）
        String data1 = "认真";
        FileUtils.writeStringToFile(file, data1, "UTF-8", true);

        //把字节数组写入文件
        byte[] buf = {13, 123, 34};
        System.out.println(new String(buf));
        FileUtils.writeByteArrayToFile(file13, buf, 0, buf.length, true);

        FileUtils.copyFileToDirectory(file1, file2);//文件不重命 12 //将文件复制到一个新的地方(重命名文件)并保存文件日期的时间。 13
        FileUtils.copyFile(file1, file3);   //复制文件夹到指定目录下,如果指定目录不存在则创建 16
        FileUtils.copyDirectoryToDirectory(file2, file4);

    }
}
