package com.hsn.exam.demo.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hsn.exam.demo.controller.Rq;

@Component
public class BeforeActionIntercepter implements HandlerInterceptor {//핸들러(인터페이스)에서 제공하는 메소드를 반드시 구현해야한다

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Rq rq = new Rq(request);
		
		request.setAttribute("rq", rq); //서블릿request를 이용해 컨트롤러에서도 rq(로그인확인로직)를 공유할수 있도록 저장함
		
		System.out.println("실행완료");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
