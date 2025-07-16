package com.rain.springbootstudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class TestController {

	@GetMapping("/hello")
	public String sayHello() {
		return "hello";
	}

	@ApiOperation("获取图片或者视频")
	@RequestMapping(value = "/playvideo", method = RequestMethod.GET)
	public void playtest(Long id, String fileType,HttpServletResponse response) throws IOException {


		File videoFile = new File("");
		if (fileType  == 1) {
			response.setContentType("image/jpeg");
		} else {
			response.setContentType("video/mp4");
		}

		response.setHeader("Content-Length", String.valueOf(videoFile.length()));

		logger.info("basePath = " + file.getFilePath());
		FileInputStream fileInputStream = new FileInputStream(videoFile);
		OutputStream outputStream = response.getOutputStream();

		byte[] buffer = new byte[4096];
		int bytesRead;

		while ((bytesRead = fileInputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}

		fileInputStream.close();
		outputStream.close();

	}


}
