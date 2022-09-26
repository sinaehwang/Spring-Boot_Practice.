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
	
	//서비스 메소드
	private void makeTestdata() {
		
		for(int i=0; i<10; i++) {
			
			String title = "제목"+i;
			String body = "내용"+i;
			
			writeArticle(title,body);//게시물작성로직실행만 하면됨
		}
		
	}
	
	private Article writeArticle(String title,String body) { //게시물작성로직이 중복되어 함수로 구현함

		int id=lastId+1;
		Article article = new Article(id,title,body);
		
		lastId = id;
		
		articles.add(article); //파라미터로 조립된 article을 리스트에 담는다.
		
		return article;
	}
	
	private void deleteArticle(int id) {
		 Article article = getArticle(id);
		
		articles.remove(article); //리스트에서 id로 찾은 게시글을 삭제한다.
		
	}
	
	
	private Article getArticle(int id) {
		
		for(Article article:articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		
		return null;
	}


	//액션 메소드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title,String body) { //제목과 내용을 파라미터로 받는 응답메소드 구현후,
		
		return writeArticle(title,body); //작성로직실행후 결과를 받기위해 doAdd를 리턴형태로 만듬
		
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() { //리스트에 add된 article 리스트들을 보여주는 응답메소드가 된다.
		
		return articles;

	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article article = getArticle(id); //id를 기반으로 article을 찾는 함수
		
		if(article==null) {
			return id+"번글은 존재하지 않습니다.";
		}

		deleteArticle(id); //찾은 id를 인자로 주고 실제 삭제하는 함수 실행시킴
		
		return id+"번글이 삭제되었습니다.";
	}




	





}



