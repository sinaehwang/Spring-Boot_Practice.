package com.hsn.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsn.exam.demo.service.ArticleService;
import com.hsn.exam.demo.vo.Article;

@Controller
public class UsrArticleController {
	@Autowired
	//ArticleService와 연결해줌,객체생성을 안해도 가능해짐
	private ArticleService articleService;

	//액션 메소드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title,String body) { //제목과 내용을 파라미터로 받는 응답메소드 구현후,
		
		Article article = articleService.writeArticle(title,body); //작성로직실행후 결과를 받기위해 doAdd를 리턴형태로 만듬 
		
		return article;
		
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() { //리스트에 add된 article 리스트들을 보여주는 응답메소드가 된다.
		
		List<Article> articles = articleService.getArticles();
		
		return articles;

	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article article = articleService.getArticle(id); //id를 기반으로 article을 찾는 함수
		
		if(article==null) {
			return id+"번글은 존재하지 않습니다.";
		}

		articleService.deleteArticle(id); //찾은 id를 인자로 주고 실제 삭제하는 함수 실행시킴
		
		return id+"번글이 삭제되었습니다.";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(int id,String title, String body) {//리턴타입을 String 과 Article 둘다 사용해야되기 때문에 Object타입을 사용함
		
		Article article = articleService.getArticle(id);
		
		if(article==null) {
			return id+"번글은 존재하지 않습니다.";
		}

		articleService.modifyArticle(id,title,body);
		
		return article;
		
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticleAction(int id) {
		
		Article article = articleService.getArticle(id);
		
		if(article==null) {
			return id+"번글은 존재하지 않습니다.";
		}

		return article;
		
	}


}



