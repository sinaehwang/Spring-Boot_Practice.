package com.hsn.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 어노테이션 Data를 하게되면 private으로 만들어진 변수들을
		// get,set할수있음//getId,getTitle,setId,setTitle이 생김
@AllArgsConstructor // 클래스 생성자를 만들지 않아도 생성자 역활을 대신 해줄수 있음
@NoArgsConstructor // 인자가 없는 경우의 생성자 역활을 대신해줌
public class Article {
	// 테이블에서 가져오는 요소들
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;// 회원번호 추가
	private String title;
	private String body;

	// 부수적 요청으로 가져오는 요소들(db기본요소가 아니라 조인을 통해 가져와야함)
	private String extra_writerName; // 예를들면 article.xml에서 쿼리문실행시에 extra_writerName 이름을 붙여줬음
	private boolean extra__actorCanDelete;
	private boolean extra__actorCanModify;


}
