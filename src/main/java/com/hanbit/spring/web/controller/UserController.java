package com.hanbit.spring.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hanbit.spring.core.service.UserService;
import com.hanbit.spring.core.vo.UserVO;

@Controller
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	public String userList() {
		
		return "user/list";
	}
	
	@RequestMapping("/api/user/list")
	@ResponseBody
	public List<UserVO> listUsers() {
		
		return userService.listUsers();
	}
	
	@RequestMapping(value="/api/user/add", method=RequestMethod.PUT)
	@ResponseBody
	public UserVO addUser(@RequestBody UserVO userVO) {
		userService.signUpUser(userVO);
		
		userVO.setUserPassword(StringUtils.EMPTY);
		
		return userVO;
	}
	
	@RequestMapping(value="/api/user/add", method=RequestMethod.POST)
	@ResponseBody
	public UserVO addUser(MultipartHttpServletRequest request) throws IOException {
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");
		
		UserVO userVO = new UserVO();
		userVO.setUserEmail(userEmail);
		userVO.setUserPassword(userPassword);
		
		MultipartFile file = request.getFile("userPhoto");
		
		String filePath = "/upload_files/" + file.getOriginalFilename();
		
		FileUtils.writeByteArrayToFile(new File(filePath),
				file.getBytes());
		
		userVO.setUserPhoto(file.getOriginalFilename());
		
		LOGGER.debug(file.getContentType());
		
		userService.signUpUser(userVO);
		
		userVO.setUserPassword(StringUtils.EMPTY);
		
		return userVO;
	}
	
}
