package com.hsn.exam.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsn.exam.demo.Util.Ut;
import com.hsn.exam.demo.vo.Member;

import lombok.Getter;

public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	
	private HttpServletRequest req;
	private HttpServletResponse res;
	private HttpSession httpsession;

	public Rq(HttpServletRequest req, HttpServletResponse res) {//printHistoryBackjs을 위해 res 가져옴

		this.req = req;
		this.res = res;
		this.httpsession = req.getSession();
		
		boolean isLogined = false;//httpsession에 초기값 저장상태

		int loginedMemberId = 0;

		if (httpsession.getAttribute("loginedMemberId") != null) {// 로그인 상태 유무판별하기 위해서 session사용,널값이 아니면 로그인상태이다.

			isLogined = true;// 로그인상태
			loginedMemberId = (int) httpsession.getAttribute("loginedMemberId");// 로그인된 회원의 번호를 저장해놓는다.
		}
		
		this.isLogined = isLogined; //Rq에 초기값 저장상태
		this.loginedMemberId = loginedMemberId;

	}

	public void printHistoryBackjs(String msg) {
		
		//자바스크립트로 로그인필요 경고창 띄우기
		//getWriter 를 사용하기 위해서 try문으로 감싸줘야함, print 메소드로 한번더 추상화시킴
		
		res.setContentType("text/html; charset=utf-8"); //자바스크립트 한글사용하기 위해서 필요
		print(Ut.jsHistoryBack(msg));
		
	}
	
	public void print(String msg) {
		
		try {
			res.getWriter().append(msg);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

	public void login(Member member) {
		
		httpsession.setAttribute("loginedMemberId", member.getId());
		
	}

	public void logout() {
		
		
		httpsession.removeAttribute("loginedMemberId");
		
	}
	

	public String historyBackOnView(String msg) {
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", true);
		

		return "common/js";
	}
	

}
