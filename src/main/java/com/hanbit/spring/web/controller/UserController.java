package com.hanbit.spring.web.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.spring.core.service.UserService;
import com.hanbit.spring.core.vo.UserVO;

@Controller
public class UserController {
	
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
	
	@RequestMapping(value="/api/user/add", method=RequestMethod.POST)
	@ResponseBody
	public UserVO addUser(@RequestBody UserVO userVO) {
		userService.signUpUser(userVO);
		
		userVO.setUserPassword(StringUtils.EMPTY);
		
		return userVO;
	}
	
}
