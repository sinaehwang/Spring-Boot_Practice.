package com.hsn.exam.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UsrArticleController {
	int lastId=0;
	List<Article> articles = new ArrayList<>();
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title,String body) { //제목과 내용을 파라미터로 받는 응답메소드 구현후,
		
		
		int id=lastId+1;
		Article article = new Article(id,title,body);
		lastId = id;
		
		articles.add(article); //파라미터로 조립된 article을 리스트에 담는다.
		
		return article;
		
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() { //리스트에 add된 article 리스트들을 보여주는 응답메소드가 된다.
		
		return articles;

	}
}


@Data //어노테이션 Data를 하게되면 private으로 만들어진 변수들을 get,set할수있음//getId,getTitle,setId,setTitle이 생김
@AllArgsConstructor//클래스 생성자를 만들지 않아도 생성자 역활을 대신 해줄수 있음
@NoArgsConstructor//인자가 없는 경우의 생성자 역활을 대신해줌
class Article{
	private int id;
	private String title;
	private String body;
}
