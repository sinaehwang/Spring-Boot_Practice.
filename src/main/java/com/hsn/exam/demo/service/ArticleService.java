package com.hsn.exam.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hsn.exam.demo.vo.Article;

@Service
public class ArticleService {
	
	private int lastId;
	private List<Article> articles;
	
	public ArticleService(){
		lastId = 0;
		articles = new ArrayList<>();
		makeTestdata();
	}

	//서비스 메소드
	public void makeTestdata() {
		
		for(int i=0; i<10; i++) {
			
			String title = "제목"+i;
			String body = "내용"+i;
			
			writeArticle(title,body);//게시물작성로직실행만 하면됨
		}
		
	}
	
	public Article writeArticle(String title,String body) { //게시물작성로직이 중복되어 함수로 구현함

		int id=lastId+1;
		Article article = new Article(id,title,body);
		
		lastId = id;
		
		articles.add(article); //파라미터로 조립된 article을 리스트에 담는다.
		
		return article;
	}
	
	public void deleteArticle(int id) {
		 Article article = getArticle(id);
		
		articles.remove(article); //리스트에서 id로 찾은 게시글을 삭제한다.
		
	}
	
	public Article modifyArticle(int id,String title, String body) {
		
		Article article = getArticle(id);
		article.setTitle(title);
		article.setBody(body);
		
		return article;
	}
	
	public Article getArticle(int id) {
		
		for(Article article:articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		
		return null;
	}

	public List<Article> getArticles() {
		
		return articles;
	}

}
