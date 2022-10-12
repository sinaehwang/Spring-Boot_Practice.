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

		Rq rq =(Rq)request.getAttribute("rq"); //공유중인 rq객체 불러오기
		
		if(rq.isLogined()==false) {//로그인이 안됬다면 자바스크립트로 경고창을 띄워주고 false를 리턴해준다.
			
			rq.printHistoryBackjs();
			
			return false;
			
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
