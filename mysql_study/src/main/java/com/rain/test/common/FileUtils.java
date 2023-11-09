package com.rain.test.common;

import java.io.*;

public class FileUtils {

    /**
     * 将数据保存到指定文件路径
     */
    public static void saveDataToFile(String filePath, byte[] data) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) file.createNewFile();
        FileOutputStream outStream = new FileOutputStream(file);
        outStream.write(data);
        outStream.flush();
        outStream.close();
    }

    /**
     * 读取文件数据
     */
    public static byte[] getFileBytes(File file) throws IOException {
        if (!file.exists() || (file.exists() && !file.isFile())) return null;
        InputStream inStream = new FileInputStream(file);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 读取文本文件字
     */
    public static String getFileText(String path) {
        try {
            byte[] data = getFileBytes(new File(path));
            return new String(data);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 清空文件夹下所有文件
     */
    public static void cleanDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) file.delete();
                if (file.isDirectory()) cleanDir(file);
            }
        }
    }
}
