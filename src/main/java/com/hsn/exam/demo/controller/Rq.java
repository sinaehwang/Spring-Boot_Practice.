package com.hsn.exam.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.Getter;

public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;

	public Rq(HttpServletRequest req) {

		HttpSession httpsession = req.getSession();
		
		boolean isLogined = false;//httpsession에 초기값 저장상태

		int loginedMemberId = 0;

		if (httpsession.getAttribute("loginedMemberId") != null) {// 로그인 상태 유무판별하기 위해서 session사용,널값이 아니면 로그인상태이다.

			isLogined = true;// 로그인상태
			loginedMemberId = (int) httpsession.getAttribute("loginedMemberId");// 로그인된 회원의 번호를 저장해놓는다.
		}
		
		this.isLogined = isLogined; //Rq에 초기값 저장상태
		this.loginedMemberId = loginedMemberId;

	}

}
