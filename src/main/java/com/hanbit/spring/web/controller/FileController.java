package com.hanbit.spring.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileController {

	@RequestMapping("/file/{fileName}")
	public void getFile(@PathVariable("fileName") String fileName,
			HttpServletResponse response) throws IOException {
		
		String filePath = "/upload_files/" + fileName + ".jpg";
		
		byte[] fileData = FileUtils.readFileToByteArray(new File(filePath));
		
		response.setContentType("image/jpeg");
		response.getOutputStream().write(fileData);
		response.flushBuffer();
	}
	
}
