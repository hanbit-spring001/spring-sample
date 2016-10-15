package com.hanbit.spring.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanbit.spring.core.dao.UserDAO;
import com.hanbit.spring.core.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SecurityService securityService;

	@Transactional
	public String signUpUser(UserVO userVO) {
		
		String email = userVO.getUserEmail();
		
		if (userDAO.countEmail(email) > 0) {
			throw new RuntimeException("이미 가입된 이메일입니다.");
		}
		
		String password = userVO.getUserPassword();
		String encryptedPassword = securityService.encryptPassword(password);
		userVO.setUserPassword(encryptedPassword);
		
		userVO.setUserId(String.valueOf(System.currentTimeMillis()) + Thread.currentThread().getId());
		
		int countInsertedUser = userDAO.insertUser(userVO);
		int countInsertedDetail = userDAO.insertUserDetail(userVO);
		
		if (countInsertedUser < 1 || countInsertedDetail < 1) {
			throw new RuntimeException("서버에 오류가 있어 잠시후 시도해주세요.");
		}
		
		return email;
	}
	
	public boolean modifyUser(UserVO userVO) {
		
		String userId = userVO.getUserId();
		String currentPassword = userVO.getUserPassword();
		
		String userPassword = userDAO.selectPassword(userId);
		
		if (!securityService.matchPassword(currentPassword, userPassword)) {
			throw new RuntimeException("패스워드가 다릅니다.");
		}
		
		String newPassword = userVO.getNewPassword();
		String encryptedPassword = securityService.encryptPassword(newPassword);
		
		userVO.setUserPassword(encryptedPassword);
		
		int countUpdated = userDAO.updateUser(userVO);
		
		return countUpdated > 0;
	}
	
	@Transactional
	public boolean removeUser(String userId) {
		
		int countDeletedUser = userDAO.deleteUser(userId);
		int countDeletedDetail = userDAO.deleteUserDetail(userId);
		
		return (countDeletedUser > 0 && countDeletedDetail > 0);
	}
	
	public List<UserVO> listUsers() {
		
		return userDAO.selectUsers();
	}
	
	public UserVO getUserDetail(String userId) {
	
		return userDAO.selectUserDetail(userId);
	}
	
	public UserVO getUserDetailByEmail(String userEmail) {
		
		return userDAO.selectUserDetailByEmail(userEmail);
	}
	
}
