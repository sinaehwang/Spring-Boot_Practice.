package com.hsn.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsn.exam.demo.Util.Ut;
import com.hsn.exam.demo.repository.ArticleRepository;
import com.hsn.exam.demo.vo.Article;
import com.hsn.exam.demo.vo.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {// 넘겨받은 다음에 프라이빗articleRepository에 저장후 사용
		this.articleRepository = articleRepository;

	}

	public ResultData<Integer> writeArticle(int boardId, String title, String body, int memberId) { // 게시물작성로직이 중복되어 함수로 구현함

		articleRepository.writeArticle(boardId,title, body, memberId);

		int id = articleRepository.getLastInsertId();// 실제 작성쿼리 실행후 가져오는 id를 새 변수 id에 담는다.

		return ResultData.from("S-2", Ut.f("%d번째 게시글이 작성되었습니다.", id), id,"Article");// id정보를 비고사항으로 controller에 넘겨준다.

	}

	public void deleteArticle(int id) {

		articleRepository.deleteArticle(id);

	}

	public ResultData<Article> modifyArticle(int id, String title, String body) {

		articleRepository.modifyArticle(id, title, body);

		Article article = getForPrintArticle(0, id);// id를 기반으로 수정한 게시글을 가져온다.

		return ResultData.from("S-4", Ut.f("%d번 게시글이 수정되었습니다.", id), article,"article");
	}
	
	public ResultData actionCanModify(Article article, int loginedMemberId) {

		if (article.getMemberId()!=loginedMemberId) {
			return ResultData.from("F-5", "해당게시글에 대한 권한이 없습니다.");
		}

		return ResultData.from("S-1", "해당 게시글 수정완료했습니다.");

	}

	public Article getForPrintArticle(int id, int loginedMemberId) {

		Article article = articleRepository.getForPrintArticle(id);
		
		upDateArticle(article, loginedMemberId);
		
		return article;

	}

	public List<Article> getForPrintArticles(int loginedMemberId, int boardId, int itemsInAPage, int page, String searchKeywordType, String searchKeyword) {//boardId정보를 추가적으로 받는다.

		int limitStart = (page-1)*itemsInAPage;//(현재페이지-1)*10개 시작점

		int limitTake = itemsInAPage;
		
		List<Article> articles = articleRepository.getArticles(boardId,limitStart,limitTake,searchKeywordType,searchKeyword);// 쿼리로 가져온 리스트들을 먼저 가져오고
		
		for(Article article : articles) {
			
			upDateArticle(article,loginedMemberId); //리턴전에 로그인 멤버와 게시글의 회원번호가 동일한지 확인후 article의 권한체크 CanDoDelete값을 세팅함
		}

		return articles;

	}

	private void upDateArticle(Article article , int loginedMemberId) {
		
		if(article==null) {
			
			return;
		}
		
		ResultData actorCanDeletedRd = actorCanDelete(article,loginedMemberId);
		
		article.setExtra__actorCanDelete(actorCanDeletedRd.isSuccess());
		
		ResultData actorCanModifyRd = actionCanModify(article,loginedMemberId);
		
		article.setExtra__actorCanModify(actorCanModifyRd.isSuccess());
		
	}

	public ResultData actorCanDelete(Article article, int loginedMemberId) {
		
		if(article == null) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다.");
		}
		if(article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-2", "해당게시물 권한이 없습니다.");
		}
		return ResultData.from("S-1", "삭제가능");
	}

	public int getTotalPageCount(int boardId, String searchKeywordType, String searchKeyword) {
		
		return articleRepository.getTotalPageCount(boardId,searchKeywordType,searchKeyword);
	}



}
