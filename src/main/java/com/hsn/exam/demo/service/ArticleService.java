package com.hsn.exam.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hsn.exam.demo.repository.ArticleRepository;
import com.hsn.exam.demo.vo.Article;

@Service
public class ArticleService {
	
	private ArticleRepository articleRepository;
	
	public ArticleService(ArticleRepository articleRepository) {//넘겨받은 다음에 프라이빗articleRepository에 저장후 사용
		this.articleRepository = articleRepository;
		
	}
	
	public Article writeArticle(String title,String body) { //게시물작성로직이 중복되어 함수로 구현함
		
		return articleRepository.writeArticle(title,body);

	}
	
	public void deleteArticle(int id) {
		 
		articleRepository.deleteArticle(id);
		
	}
	
	public void modifyArticle(int id,String title, String body) {
		
		articleRepository.modifyArticle(id,title,body);
	}
	
	public Article getArticle(int id) {
		
		return articleRepository.getArticle(id);
		
	}

	public List<Article> getArticles() {
		
		return articleRepository.getArticles();
		
	}

}
