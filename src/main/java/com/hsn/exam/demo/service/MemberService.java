package com.hsn.exam.demo.service;

import java.util.List;

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

		//가입시 아이디 중복체크하는 로직
			int id = -1; //테이블구조상 없는 id인 -1로 가정
		
			Member foundMember =memberRepository.getMemberByLogId(loginId);//아이디 기반으로 쿼리실행후 null값이 담긴다면 중복안되서 좋은거!
			
			if(foundMember!=null) { //null값이 아니라면 아이디가 중복된다는것이기 때문에 id를 -1로 리턴한다 
				return id;
			}
		
			memberRepository.join(loginId,loginPw,name,nickname,cellphoneNum,email);
			
			id = memberRepository.getLastMemberId();
			
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

	public List<Member> getMembers() {
		
		List<Member> members = memberRepository.getMembers();
		
		return members;
	}

}
