package com.hsn.exam.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hsn.exam.demo.Util.Ut;
import com.hsn.exam.demo.repository.ArticleRepository;
import com.hsn.exam.demo.vo.Article;
import com.hsn.exam.demo.vo.ResultData;

@Service
public class ArticleService {
	
	private ArticleRepository articleRepository;
	
	public ArticleService(ArticleRepository articleRepository) {//넘겨받은 다음에 프라이빗articleRepository에 저장후 사용
		this.articleRepository = articleRepository;
		
	}
	
	public ResultData writeArticle(String title,String body) { //게시물작성로직이 중복되어 함수로 구현함
		
		articleRepository.writeArticle(title,body);
		
		 int id = articleRepository.getLastInsertId();//실제 작성쿼리 실행후 가져오는 id를 새 변수 id에 담는다.
		 
		 return ResultData.from("S-2", Ut.f("%d번째 게시글이 작성되었습니다.", id),id);//id정보를 비고사항으로 controller에 넘겨준다.

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
