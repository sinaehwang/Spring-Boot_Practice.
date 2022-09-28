package com.hsn.exam.demo.service;

import org.springframework.stereotype.Service;

import com.hsn.exam.demo.repository.MemberRepository;

@Service
public class MemberService {
	
	private MemberRepository memberRepository;

	
	public String join(String loginId, String loginPw, String name, String nickname, String cellphoneNo,String email) {
		
		memberRepository.join(loginId,loginPw,name,nickname,cellphoneNo,email);
		
		return "성공";
	}

}
