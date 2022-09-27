package com.hsn.exam.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsn.exam.demo.repository.ArticleRepository;
import com.hsn.exam.demo.vo.Article;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public Article writeArticle(String title,String body) { //게시물작성로직이 중복되어 함수로 구현함
		
		Article article = articleRepository.writeArticle(title,body);
		
		return article;
	}
	
	public void deleteArticle(int id) {
		 
		articleRepository.deleteArticle(id);
		
	}
	
	public Article modifyArticle(int id,String title, String body) {
		
		Article article = articleRepository.modifyArticle(id,title,body);
		
		return article;
	}
	
	public Article getArticle(int id) {
		
		Article article = articleRepository.getArticle(id);
		
		return article;
	}

	public List<Article> getArticles() {
		
		List<Article> articles = articleRepository.getArticles();
		
		return articles;
	}

}
