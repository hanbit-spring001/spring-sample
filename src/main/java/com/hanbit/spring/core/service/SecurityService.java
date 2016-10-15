package com.hanbit.spring.core.service;

import javax.annotation.PostConstruct;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
	
	private PasswordEncoder encoder;
	
	@PostConstruct
	public void initEncoder() {
		encoder = new StandardPasswordEncoder();
	}
	
	public String encryptPassword(String rawPassword) {
		
		String encryptPassword = encoder.encode(rawPassword);
		
		return encryptPassword;
	}
	
	public boolean matchPassword(String rawPassword, 
			String encryptPassword) {
		
		return encoder.matches(rawPassword, encryptPassword);
	}
	
}
