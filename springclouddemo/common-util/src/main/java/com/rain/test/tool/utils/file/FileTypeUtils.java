package com.rain.test.tool.utils.file;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * 文件类型工具类
 *
 * @author yzg
 */
public class FileTypeUtils
{
    /**
     * 获取文件类型
     * <p>
     * 例如: yzg.txt, 返回: txt
     * 
     * @param file 文件名
     * @return 后缀（不含".")
     */
    public static String getFileType(File file)
    {
        if (null == file)
        {
            return StringUtils.EMPTY;
        }
        return getFileType(file.getName());
    }

    /**
     * 获取文件类型
     * <p>
     * 例如: yzg.txt, 返回: txt
     *
     * @param fileName 文件名
     * @return 后缀（不含".")
     */
    public static String getFileType(String fileName)
    {
        int separatorIndex = fileName.lastIndexOf(".");
        if (separatorIndex < 0)
        {
            return "";
        }
        return fileName.substring(separatorIndex + 1).toLowerCase();
    }
}