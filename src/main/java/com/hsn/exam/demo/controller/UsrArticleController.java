package com.hsn.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsn.exam.demo.Util.Ut;
import com.hsn.exam.demo.service.ArticleService;
import com.hsn.exam.demo.service.BoardService;
import com.hsn.exam.demo.vo.Article;
import com.hsn.exam.demo.vo.Board;
import com.hsn.exam.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	@Autowired
	// ArticleService와 연결해줌,객체생성을 안해도 가능해짐
	private ArticleService articleService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private Rq rq;

	//상단부메뉴 글쓰기를 선택하면 연결되는 부분,write.jsp로 연결되서 글쓰기작성폼 보여줌
	@RequestMapping("/usr/article/write")
	public String write(HttpServletRequest req,Model model) {
		
		
		return "usr/article/write";
	}
	
	// 액션 메소드(write.jsp에서 작성후 전송시 제목,내용,게시판Id파라미터값을받음)
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd(int boardId,String title, String body) {
		
		if (Ut.empty(title)) {// 값이 비어있거나 null인경우를 판단하는 함수로직실행
			//return ResultData.from("F-2", "제목을 입력해주세요");// 실패코드랑 메세지만 넘겨준다.
			return Ut.jsHistoryBack("제목을 입력해주세요");
		}

		if (Ut.empty(body)) {
			//return ResultData.from("F-2", "내용을 입력해주세요");
			return Ut.jsHistoryBack("내용을 입력해주세요");
		}

		ResultData<Integer> writeData = articleService.writeArticle(boardId,title, body, rq.getLoginedMemberId());//rq클래스를 통해서  로그인 멤버Id 정보를 가져와야함

		int id = (int) writeData.getData1();// int로 형변환이 필요함

		
		return Ut.jsLocationReplace(Ut.f("%d번 글이 생성되었습니다.", id), Ut.f("../article/detail?id=%d", id));

	}

	@RequestMapping("/usr/article/list")
	public String getArticles(Model model,
			@RequestParam(defaultValue = "1") int boardId,
			@RequestParam(defaultValue = "1") int page) {//boardId와 page인자값이 안넘왔을때 기본값으로 세팅하기위해
		
		Board board = boardService.getBoardId(boardId);//게시판 클래스생성

		
		if(board==null) {
			
			return rq.historyBackOnView(Ut.f("%d번 게시판은 존재하지 않습니다.", boardId));
		}

		int articlesCount = boardService.getTotalPageCount(boardId);//게시판Id별 총페이지갯수
		
		int itemsInAPage = 10; //10page씩 가져온다
		
		int pagesCount = (int)Math.ceil((double)articlesCount/itemsInAPage);//double형으로 형변환해서 소수점발생후 반올림을시켜야 누락페이지가 발생안함
		
		List<Article> articles = articleService.getForPrintArticles(rq.getLoginedMemberId(),boardId,itemsInAPage,page);//현재페이지랑 itemsInAPage를 넘겨줌
		
		model.addAttribute("articlesCount",articlesCount);//TotalPageCount를 list.jsp에서 불러올수있음
		model.addAttribute("articles",articles);//list.jsp에서 불러올수있음
		model.addAttribute("board",board);//board.repository에서 가져온 데이터를 list.jsp에서 불러올수있음
		model.addAttribute("pagesCount",pagesCount);
		model.addAttribute("boardId",boardId);
		model.addAttribute("page",page);
		
		return "usr/article/list";  //jsp로 리스트를 구현한다.

	}
	
	@RequestMapping("/usr/article/detail")
	public String ArticleDetail(Model model,int id) {

		Article article = articleService.getForPrintArticle(id,rq.getLoginedMemberId());
		
		model.addAttribute("article",article);
		
		return "usr/article/detail";//jsp로 구현

	}
	

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = articleService.getForPrintArticle(id,rq.getLoginedMemberId()); // id를 기반으로 article을 찾는 함수

		
		if(article==null) {
			
			return Ut.jsHistoryBack("해당 게시글은 존재하지 않습니다.");
			
			}

		if (rq.getLoginedMemberId() != article.getMemberId()) {
			
			return Ut.jsHistoryBack("해당게시글에 대한 권한이 없습니다.");
			
		}

		articleService.deleteArticle(id); // 찾은 id를 인자로 주고 실제 삭제하는 함수 실행시킴
		
		return Ut.jsLocationReplace(Ut.f("%d번 게시글이 삭제되었습니다.", id), "/usr/article/list?boardId=1");//모든 컨트롤 작업마다 메세지가 다르기 때문에 함수화를 시켜주는게 좋음
		
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {// 리턴타입을 String 과 Article 둘다 사용해야되기 때문에 Object타입을 사용함

		Article article = articleService.getForPrintArticle(id,rq.getLoginedMemberId());
		
		if (article == null) {

			//return ResultData.from("F-4", Ut.f("%d번 게시글은 존재하지 않습니다.", id));
			return rq.jsHistoryBack(Ut.f("%d번 게시글은 존재하지 않습니다.", id));
		}
		
		ResultData actionCanModifyRd =articleService.actionCanModify(article, rq.getLoginedMemberId());//Service에서 게시글 수정권한을 체크하기위해서

		if(actionCanModifyRd.isFail()) {//게시글권한이 없으면 F로시작되는 코드가 리턴될것이기 때문에 ResultData 실패코드를 그대로 리턴해준다.
			
			//return actionCanModifyRd;
			return rq.jsHistoryBack(actionCanModifyRd.getMsg());
		}
		
		//return articleService.modifyArticle(id, title, body,rq.getLoginedMemberId());//권한체크가 성공한다면 실제 수정기능 로직실행이됨
		
		articleService.modifyArticle(id, title, body);
		
		return rq.jsReplace(Ut.f("%d번 글이 수정되었습니다.", id), Ut.f("../article/detail?id=%d", id));
	}
	
	@RequestMapping("/usr/article/modify")
	public String showmodify(Model model,int id) {

		Article article = articleService.getForPrintArticle(id,rq.getLoginedMemberId());
		
		if (article == null) {

			return rq.historyBackOnView(Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		ResultData actionCanModifyRd =articleService.actionCanModify(article,rq.getLoginedMemberId());//Service에서 게시글 수정권한을 체크하기위해서

		if(actionCanModifyRd.isFail()) {//게시글권한이 없으면 F로시작되는 코드가 리턴될것이기 때문에 ResultData 실패코드를 그대로 리턴해준다.
			
			return rq.historyBackOnView(actionCanModifyRd.getMsg());
		}

		model.addAttribute("article", article);
		
		return "usr/article/modify";
	}
	

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {// ResultData클래스타입으로 바꾸고 from()리턴타입으로 맞춰줘야함
		

		Article article = articleService.getForPrintArticle(id,rq.getLoginedMemberId());

		if (article == null) {

			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));// 2개인자로 받는 from()함수를 실행시키고 리턴으로 data1를
																			// null값으로 주는 3개인자from()실행
		}

		return ResultData.from("S-1", Ut.f("%d번 게시물 입니다.", id), article,"Article");// 게시글이 있을때는 해당게시글까지 포함해서 3개인자를 넘겨준다.

	}

}
