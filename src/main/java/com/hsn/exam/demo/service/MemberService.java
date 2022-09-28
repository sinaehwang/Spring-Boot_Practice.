package com.hsn.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsn.exam.demo.repository.MemberRepository;
import com.hsn.exam.demo.vo.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository=memberRepository;
	}

	public int join(String loginId,String loginPw,String name,String nickname,String cellphoneNum,String email) {

		memberRepository.join(loginId,loginPw,name,nickname,cellphoneNum,email);
		
		int id = memberRepository.getLastMemberId();
		
		return id;
	}

	public Member getMemberById(int id) {
		
		Member member = memberRepository.getMemberById(id);
		
		return member;
		
	}

	public Member getMemberByLogId(String loginId) {
		
		Member member = memberRepository.getMemberByLogId(loginId);
		
		return member;
	}

}
