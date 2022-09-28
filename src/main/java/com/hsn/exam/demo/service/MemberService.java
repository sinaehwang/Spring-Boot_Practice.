package com.hsn.exam.demo.service;

import org.springframework.stereotype.Service;

import com.hsn.exam.demo.repository.MemberRepository;
import com.hsn.exam.demo.vo.Member;


@Service
public class MemberService {
	
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository=memberRepository;
	}

	
	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo,String email) {
		
		Member foundMember = memberRepository.getMemberbyLoginId(loginId);
		
		if(foundMember!=null) {
			return -1;
		}
		
		memberRepository.join(loginId,loginPw,name,nickname,cellphoneNo,email);
		
		return memberRepository.getLastMemberId();
		
	}


	public Member getMemberbyId(int id) {
		
		return memberRepository.getMemberbyId(id);
		
	}

}
