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
	
	public ResultData<Integer> writeArticle(String title,String body,int memberId) { //게시물작성로직이 중복되어 함수로 구현함
		
		articleRepository.writeArticle(title,body,memberId);
		
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

	public ResultData getArticles() {
		
		List<Article>articles=articleRepository.getArticles();//쿼리로 가져온 리스트들을 새 리스트 articles변수에 담는다.
		
		return ResultData.from("S-3","게시글목록",articles);//ResultData폼 형식으로 변환한다.
		
	}

}
