package com.hsn.exam.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsn.exam.demo.vo.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UsrArticleController {
	private int lastId;
	private List<Article> articles;
	
	public UsrArticleController() {
		lastId = 0;
		articles = new ArrayList<>();
		makeTestdata();
	}
	
	
	private void makeTestdata() {
		
		for(int i=0; i<10; i++) {
			int id=lastId+1;
			String title = "제목"+i;
			String body = "내용"+i;
			Article article = new Article(id,title,body);
			
			lastId = id;
			
			articles.add(article); //파라미터로 조립된 article을 리스트에 담는다.
		}
		
	}


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



