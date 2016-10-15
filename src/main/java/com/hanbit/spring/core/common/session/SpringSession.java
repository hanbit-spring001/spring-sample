package com.hanbit.spring.core.common.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SpringSession {

	private Map<String, Object> session = new HashMap<>();

	public Map<String, Object> getSession() {
		return session;
	}
	
}
