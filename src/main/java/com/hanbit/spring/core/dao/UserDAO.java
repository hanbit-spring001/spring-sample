package com.hanbit.spring.core.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanbit.spring.core.vo.UserVO;

@Repository
public class UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertUser(UserVO userVO) {
		return sqlSession.insert("user.insertUser", userVO);
	}

	public int insertUserDetail(UserVO userVO) {
		return sqlSession.insert("user.insertUserDetail", userVO);
	}

	public int countEmail(String email) {
		return sqlSession.selectOne("user.countEmail", email);
	}

	public int updateUser(UserVO userVO) {
		return sqlSession.update("user.updateUser", userVO);
	}

	public String selectPassword(String userId) {
		return sqlSession.selectOne("user.selectPassword", userId);
	}

	public int deleteUser(String userId) {
		return sqlSession.delete("user.deleteUser", userId);
	}

	public int deleteUserDetail(String userId) {
		return sqlSession.delete("user.deleteUserDetail", userId);
	}

	public List<UserVO> selectUsers() {
		return sqlSession.selectList("user.selectUsers");
	}

	public UserVO selectUserDetail(String userId) {
		return sqlSession.selectOne("user.selectUserDetail", userId);
	}

	public UserVO selectUserDetailByEmail(String userEmail) {
		return sqlSession.selectOne("user.selectUserDetailByEmail", userEmail);
	}
	
}
