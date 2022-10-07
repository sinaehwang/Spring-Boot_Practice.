package com.hsn.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //어노테이션 Data를 하게되면 private으로 만들어진 변수들을 get,set할수있음//getId,getTitle,setId,setTitle이 생김
@AllArgsConstructor//클래스 생성자를 만들지 않아도 생성자 역활을 대신 해줄수 있음
@NoArgsConstructor//인자가 없는 경우의 생성자 역활을 대신해줌
public class Article {
		private int id;
		private String regDate;
		private String updateDate;
		private int memberId;//회원번호 추가
		private String title;
		private String body;
		private String extra_writerName;
	

}
