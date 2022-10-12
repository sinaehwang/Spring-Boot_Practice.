package com.hsn.exam.demo.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hsn.exam.demo.controller.Rq;

@Component
public class NeedLoginIntercepter implements HandlerInterceptor {//핸들러(인터페이스)에서 제공하는 메소드를 반드시 구현해야한다

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("로그인처리확인?");
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
