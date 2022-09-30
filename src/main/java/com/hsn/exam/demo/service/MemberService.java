package com.hsn.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsn.exam.demo.Util.Ut;
import com.hsn.exam.demo.repository.MemberRepository;
import com.hsn.exam.demo.vo.Member;
import com.hsn.exam.demo.vo.ResultData;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository=memberRepository;
	}

	public ResultData join(String loginId,String loginPw,String name,String nickname,String cellphoneNum,String email) {

		//가입시 아이디 중복체크하는 로직
			//int id = -1; //테이블구조상 없는 id인 -1로 가정
		
			Member foundMember =memberRepository.getMemberByLogId(loginId);//아이디 기반으로 쿼리실행후 null값이 담긴다면 중복안되서 좋은거!
			
			if(foundMember!=null) { //null값이 아니라면 아이디가 중복된다는것이기 때문에 id를 -1로 리턴한다 
				
				return ResultData.from("F-4", Ut.f("%s는 이미 사용중인 아이디입니다.",loginId ));
			}
			
			//가입시 이름+메일 중복체크하는 로직
			
			foundMember = memberRepository.getMemberByNameAndEmail(name,email);
			
			if(foundMember!=null) { //null값이 아니라면 아이디가 중복된다는것이기 때문에 id를 -1로 리턴한다 
				
				//id=-2;
				
				return ResultData.from("F-4", Ut.f("%s과 %s는 이미 사용중인 이름과 이메일입니다.",name,email ));
			}
		
			memberRepository.join(loginId,loginPw,name,nickname,cellphoneNum,email);
			
			 int id = memberRepository.getLastMemberId();
			
			return ResultData.from("S-4",Ut.f("%d번째 회원 가입완료", id),id);
	}

	public ResultData getMemberById(int id) {
		
		Member member = memberRepository.getMemberById(id);
		
		return ResultData.from("S-4",Ut.f("%d번째 회원 가입완료", id), member);
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
