package com.hanbit.spring.core.common.session;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SessionHelper {
	
	private static SessionHelper SESSION_HELPER;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@PostConstruct
	public void initSessionHelper() {
		SESSION_HELPER = this;
	}

	public static Map<String, Object> getSession() {
		return SESSION_HELPER.applicationContext
				.getBean(SpringSession.class).getSession();
	}
	
	
	
}
