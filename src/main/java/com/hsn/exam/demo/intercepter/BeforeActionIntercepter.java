package com.hsn.exam.demo.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class BeforeActionIntercepter implements HandlerInterceptor {//핸들러에서 제공하는 메소드를 반드시 구현해야한다

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//하고싶은거 공통처리
		
		System.out.println();
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
