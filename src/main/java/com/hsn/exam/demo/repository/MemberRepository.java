package com.hsn.exam.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hsn.exam.demo.vo.Member;
@Mapper
public interface MemberRepository {

	@Insert("""
			INSERT INTO `member`
			SET regDate = NOW(),
			updateDate = NOW(),
			loginId = #{loginId},
			loginPw = #{loginPw},
			`name` = #{name},
			`nickname` = #{nickname},
			cellphoneNo = #{cellphoneNo},
			email = #{email}
			
			""")
	public void join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email);

	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	public int getLastMemberId();

	@Select("""
			SELECT * FROM `member` 
			WHERE id = #{id}
			""")
	public Member getMemberbyId(int id);

}
