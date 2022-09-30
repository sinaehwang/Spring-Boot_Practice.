package com.hsn.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsn.exam.demo.Util.Ut;
import com.hsn.exam.demo.service.MemberService;
import com.hsn.exam.demo.vo.Member;

import lombok.Getter;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	
	public UsrMemberController(MemberService memberService) {
		this.memberService=memberService;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId,String loginPw,String name,String nickname,String cellphoneNum,String email) {
		
		if(Ut.empty(loginId)) { //아이디값을 매개변수로 주고 아이디에 Null여부를 판단하는 함수로 만듬
			return "loginId값을 입력해주세요";
		}
		
		if(Ut.empty(loginPw)) {
			return "loginPw값을 입력해주세요";
		}
		
		if(Ut.empty(name)) {
			return "name값을 입력해주세요";
		}
		
		if(Ut.empty(nickname)) {
			return "nickname값을 입력해주세요";
		}
		
		if(Ut.empty(cellphoneNum)) {
			return "cellphoneNum값을 입력해주세요";
		}
		
		if(Ut.empty(email)) {
			return "email값을 입력해주세요";
		}
		
		 int id =memberService.join(loginId,loginPw,name,nickname,cellphoneNum,email); //join으로 insert를 한후에
		 //보여줘야하는것들
		 //resultCode
		 //msg
		 //data1
		 if(id==-1) {
			 return Ut.f("이미 사용중인 아이디(%s)입니다.",loginId);//Ut.f에 먼저 넘어가는건 문장자체,그다음에 매개변수가 넘어간다
		 }
		 
		 if(id==-2) {
			 return Ut.f("이미 사용중인 이름(%s)과 메일(%s)입니다.",name,email);//Ut.f에 넘어가는 매개변수가 1개이상이 될수있다.
		 }
		 
		 return memberService.getMemberById(id); //id를 기반으로 회원을 찾는 로직실행
	}
	
	@RequestMapping("/usr/member/getMember")
	@ResponseBody
	public Object getMemberByLogId(String loginId) { //두가지의 리턴타입이 필요하니까 Object타입사용
		
		Member member = memberService.getMemberByLogId(loginId);//가져온값이 null일수도있고 member일수도 있다.
		
		if(member==null) { //널인경우에는 리턴을 문구로 알려주고
			return "해당 회원은 존재하지 않습니다.";
		}
		return member; //값이있다면 멤버를 보여줘야한다.
		 
	}
	
	@RequestMapping("/usr/member/getMembers")
	@ResponseBody
	public List<Member> getMembers() { //가입된 회원정보 모두를 가져올려면 리스트형태가 필요하다.
		
		return memberService.getMembers();
	}

}



