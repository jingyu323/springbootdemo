package com.rain.test.tool.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUploadUtils {

	public static String saveFile(String path, String fileName, MultipartFile file) {
		String savePath = null;
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				File filepath = new File(path);
				if (!filepath.exists()) {
					filepath.mkdirs();
				}
				// 文件保存路径
				savePath = path + (path.endsWith(File.separator) ? "" : File.separator) + fileName;
				File savaFile = new File(savePath);
				if(savaFile.exists()){
					savaFile.delete();
				}
				// 转存文件
				file.transferTo(savaFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return savePath;
	}

	public static ArrayList<String> filesUpload(String path, List<String> fileNames, MultipartFile[] files) {
		ArrayList<String> paths = new ArrayList<String>();
		String saveFile = null;
		// 判断file数组不能为空并且长度大于0
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				// 保存文件
				saveFile = saveFile(path, fileNames.get(i), file);
			}
			paths.add(saveFile);
		}
		return paths;
	}

}
