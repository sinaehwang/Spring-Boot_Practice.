package com.hsn.exam.demo.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hsn.exam.demo.controller.Rq;
import com.hsn.exam.demo.service.MemberService;

@Component
public class BeforeActionIntercepter implements HandlerInterceptor {//핸들러(인터페이스)에서 제공하는 메소드를 반드시 구현해야한다

	@Autowired
	private Rq rq;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//Rq rq = new Rq(request,response,memberService); 
		
		//request.setAttribute("rq", rq); //기존에 인터셉터에서 Rq를 저장하던 작업을 Rq가 진행하게됨

		rq.initOnBeforeActionIntercepter(); // head 로그인부분에서 rq객체를 사용하고 있기때문에 첫작업인 비포엔터셉터에서 rq가 실행되는 초기작업이 필요함
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
