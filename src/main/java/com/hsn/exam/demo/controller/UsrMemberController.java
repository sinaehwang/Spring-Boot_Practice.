package com.hsn.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsn.exam.demo.service.MemberService;
import com.hsn.exam.demo.vo.Member;


@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	
	public UsrMemberController(MemberService memberService) {
		this.memberService= memberService;
	}

	// 액션 메소드
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,String email) {
		
		int id = memberService.join(loginId,loginPw,name,nickname,cellphoneNo,email);
		
		if(id==-1) {
			return "사용중인 아이디입니다.";
		}
		
		Member member = memberService.getMemberbyId(id);
		
		return member;
	}
	


}
