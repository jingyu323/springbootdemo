package com.rain.test;

import java.io.File;
import java.util.List;

public class FileInfo {

    String fileName;

    int fileSize;

    String  filePath;

    List<FileInfo>  childFiles;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<FileInfo> getChildFiles() {
        return childFiles;
    }

    public void setChildFiles(List<FileInfo> childFiles) {
        this.childFiles = childFiles;
    }
}
