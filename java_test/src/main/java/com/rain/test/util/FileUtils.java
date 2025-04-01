package com.rain.test.util;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 文件处理工具类
 *
 * @author ruoyi
 */
@Slf4j
public class FileUtils {
    /**
     * The constant FILENAME_PATTERN.
     */
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";


    /**
     * Copy file.
     *
     * @param oldPath the old path
     * @param newPath the new path
     * @throws IOException the io exception
     */
    public static void copyFile(String oldPath, String newPath) {

        File oldFile = new File(oldPath);
        File file = new File(newPath);
        FileInputStream in = null;
        FileOutputStream out = null;
        try{
            in = new FileInputStream(oldFile);
            out = new FileOutputStream(file);;
            byte[] buffer=new byte[2097152];
            int readByte = 0;
            while((readByte = in.read(buffer)) != -1){
                out.write(buffer, 0, readByte);
            }
        }catch (IOException e){
            log.error("操作异常", e.getMessage());
        }finally {
            try {
                if (in != null){
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                if (out != null){
                    out.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * Copy dir.
     *
     * @param sourcePath the source path
     * @param newPath    the new path
     * @throws IOException the io exception
     */
    public static void copyDir(String sourcePath, String newPath) throws IOException {
        File file = new File(sourcePath);
        String[] filePath = file.list();

        if (!(new File(newPath)).exists()) {
            (new File(newPath)).mkdir();
        }

        for (int i = 0; i < filePath.length; i++) {
            if ((new File(sourcePath + File.separator + filePath[i])).isDirectory()) {
                copyDir(sourcePath  + File.separator  + filePath[i], newPath  + File.separator + filePath[i]);
            }

            if (new File(sourcePath  + File.separator + filePath[i]).isFile()) {
                copyFile(sourcePath + File.separator + filePath[i], newPath + File.separator + filePath[i]);
            }

        }
    }





    /**
     * 删除文件
     *
     * @param filePath 文件
     * @return boolean
     */
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            flag = file.delete();
        }
        return flag;
    }

    /**
     * 递归删除
     * 删除某个目录及目录下的所有子目录和文件
     *
     * @param file 文件或目录
     * @return 删除结果 boolean
     */
    public static boolean delFiles(File file){
        boolean result = false;
        //目录
        if(file.isDirectory()){
            File[] childrenFiles = file.listFiles();
            for (File childFile:childrenFiles){
                result = delFiles(childFile);
                if(!result){
                    return result;
                }
            }
        }
        //删除 文件、空目录
        result = file.delete();
        return result;
    }

    /**
     * 文件名称验证
     *
     * @param filename 文件名称
     * @return true 正常 false 非法
     */
    public static boolean isValidFilename(String filename) {
        return filename.matches(FILENAME_PATTERN);
    }




    /**
     * 百分号编码工具方法
     *
     * @param s 需要百分号编码的字符串
     * @return 百分号编码后的字符串 string
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public static String percentEncode(String s) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
        return encode.replaceAll("\\+", "%20");
    }

    /**
     * 获取图像后缀
     *
     * @param photoByte 图像数据
     * @return 后缀名 file extend name
     */
    public static String getFileExtendName(byte[] photoByte) {
        String strFileExtendName = "jpg";
        if ((photoByte[0] == 71) && (photoByte[1] == 73) && (photoByte[2] == 70) && (photoByte[3] == 56)
                && ((photoByte[4] == 55) || (photoByte[4] == 57)) && (photoByte[5] == 97)) {
            strFileExtendName = "gif";
        } else if ((photoByte[6] == 74) && (photoByte[7] == 70) && (photoByte[8] == 73) && (photoByte[9] == 70)) {
            strFileExtendName = "jpg";
        } else if ((photoByte[0] == 66) && (photoByte[1] == 77)) {
            strFileExtendName = "bmp";
        } else if ((photoByte[1] == 80) && (photoByte[2] == 78) && (photoByte[3] == 71)) {
            strFileExtendName = "png";
        }
        return strFileExtendName;
    }

    /**
     * 重命名文件
     *
     * @param filePath    the file path
     * @param oldFileName the old file name
     * @param newFileName the new file name
     * @return
     */
    public static void renameFile(String filePath, String oldFileName, String newFileName) {
        File oldFile = new File(filePath + File.separator + oldFileName);
        File newFile = new File(filePath + File.separator + newFileName);
        if (oldFile.exists() && oldFile.isFile()) {
            boolean re = oldFile.renameTo(newFile);
            if (re){
                return;
            }
        }
    }

    /**
     * 写json文件
     *
     * @param Path     the path
     * @param dataJson the data json
     */
    public static void writeJson(String Path, JSONObject dataJson) {
        //读取json文件
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(Path));
            String ws = dataJson.toString();
            bw.write(ws);
            bw.flush();
        } catch (Exception e) {
            log.error("操作异常", e.getMessage());
        }finally {
            if (bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    log.error("操作异常", e.getMessage());
                }
            }
        }
    }
}
