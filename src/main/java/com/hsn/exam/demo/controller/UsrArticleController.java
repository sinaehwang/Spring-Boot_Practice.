package com.hsn.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsn.exam.demo.Util.Ut;
import com.hsn.exam.demo.service.ArticleService;
import com.hsn.exam.demo.vo.Article;
import com.hsn.exam.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	@Autowired
	// ArticleService와 연결해줌,객체생성을 안해도 가능해짐
	private ArticleService articleService;

	// 액션 메소드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(HttpServletRequest req, String title, String body) {
		
		Rq rq = new Rq(req);

		if (rq.isLogined() == false) {//rq클래스를 통해서  로그인 상태 정보를 가져와야함
			return ResultData.from("F-3", "로그인후 이용해주시기 바랍니다.");
		}

		if (Ut.empty(title)) {// 값이 비어있거나 null인경우를 판단하는 함수로직실행
			return ResultData.from("F-2", "제목을 입력해주세요");// 실패코드랑 메세지만 넘겨준다.
		}

		if (Ut.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}

		ResultData<Integer> writeData = articleService.writeArticle(title, body, rq.getLoginedMemberId());//rq클래스를 통해서  로그인 멤버Id 정보를 가져와야함

		int id = (int) writeData.getData1();// int로 형변환이 필요함

		Article article = articleService.getForPrintArticle(id,rq.getLoginedMemberId());

		return ResultData.newData(writeData, article); // 성공했을때 해당게시글까지 넘겨줘서 보여지도록

	}

	@RequestMapping("/usr/article/list")
	public String getArticles(HttpSession httpsession,Model model) {//Model클래스도입
		
		boolean isLogined = false;// 로그인이 아닌상태로 가정

		int loginedMemberId = 0;

		if (httpsession.getAttribute("loginedMemberId") != null) {// 로그인 상태 유무판별하기 위해서 session사용,널값이 아니면 로그인상태이다.
			isLogined = true;// 로그인상태

			loginedMemberId = (int) httpsession.getAttribute("loginedMemberId");// 로그인된 회원의 번호를 저장해놓는다.
		}

		List<Article> articles = articleService.getForPrintArticles(loginedMemberId);
		
		model.addAttribute("articles",articles);//list.jsp에서 불러올수있음
		
		return "usr/article/list";  //jsp로 구현한다.

	}
	
	@RequestMapping("/usr/article/detail")
	public String ArticleDetail(HttpSession httpsession,Model model,int id) {
		
		boolean isLogined = false;// 로그인이 아닌상태로 가정

		int loginedMemberId = 0;

		if (httpsession.getAttribute("loginedMemberId") != null) {// 로그인 상태 유무판별하기 위해서 session사용,널값이 아니면 로그인상태이다.
			isLogined = true;// 로그인상태

			loginedMemberId = (int) httpsession.getAttribute("loginedMemberId");// 로그인된 회원의 번호를 저장해놓는다.
			
		}

		Article article = articleService.getForPrintArticle(id,loginedMemberId);
		
		model.addAttribute("article",article);
		
		return "usr/article/detail";//jsp로 구현

		//if (article == null) {

			//return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));// 2개인자로 받는 from()함수를 실행시키고 리턴으로 data1를
																			// null값으로 주는 3개인자from()실행
		//}

		//return ResultData.from("S-1", Ut.f("%d번 게시물 입니다.", id), article,"Article");// 게시글이 있을때는 해당게시글까지 포함해서 3개인자를 넘겨준다.

	}
	

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpSession httpsession, int id) {

		boolean isLogined = false;// 로그인이 아닌상태로 가정

		int loginedMemberId = 0;

		if (httpsession.getAttribute("loginedMemberId") != null) {// 로그인 상태 유무판별하기 위해서 session사용,널값이 아니면 로그인상태이다.
			isLogined = true;// 로그인상태

			loginedMemberId = (int) httpsession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {// "로그인상태가 아니다"가 참이라면,
			
			return Ut.jsHistoryBack("로그인후 이용해주시기 바랍니다.");
			
		}


		Article article = articleService.getForPrintArticle(id,loginedMemberId); // id를 기반으로 article을 찾는 함수

		
		if(article==null) {
			
			return Ut.jsHistoryBack("해당 게시글은 존재하지 않습니다.");
			
			}

		if (loginedMemberId != article.getMemberId()) {
			
			return Ut.jsHistoryBack("해당게시글에 대한 권한이 없습니다.");
			
		}

		articleService.deleteArticle(id); // 찾은 id를 인자로 주고 실제 삭제하는 함수 실행시킴
		
		return Ut.jsLocationReplace(Ut.f("%d번 게시글이 삭제되었습니다.", id), "/usr/article/list");//모든 컨트롤 작업마다 메세지가 다르기 때문에 함수화를 시켜주는게 좋음
		
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(HttpSession httpsession,int id, String title, String body) {// 리턴타입을 String 과 Article 둘다 사용해야되기 때문에 Object타입을 사용함

		boolean isLogined = false;// 로그인이 아닌상태로 가정

		int loginedMemberId = 0;

		if (httpsession.getAttribute("loginedMemberId") != null) {// 로그인 상태 유무판별하기 위해서 session사용,널값이 아니면 로그인상태이다.
			isLogined = true;// 로그인상태

			loginedMemberId = (int) httpsession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {// "로그인상태가 아니다"가 참이라면,
			return ResultData.from("F-3", "로그인후 이용해주시기 바랍니다.");
		}

		Article article = articleService.getForPrintArticle(id,loginedMemberId);
		
		if (article == null) {

			return ResultData.from("F-4", Ut.f("%d번 게시글은 존재하지 않습니다.", id));
		}
		
		ResultData actionCanModifyRd =articleService.actionCanModify(loginedMemberId,article);//Service에서 게시글 수정권한을 체크하기위해서

		if(actionCanModifyRd.isFail()) {//게시글권한이 없으면 F로시작되는 코드가 리턴될것이기 때문에 ResultData 실패코드를 그대로 리턴해준다.
			
			return actionCanModifyRd;
		}
		
		return articleService.modifyArticle(id, title, body,loginedMemberId);//권한체크가 성공한다면 실제 수정기능 로직실행이됨
		
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(HttpSession httpsession,int id) {// ResultData클래스타입으로 바꾸고 from()리턴타입으로 맞춰줘야함
		
		boolean isLogined = false;// 로그인이 아닌상태로 가정

		int loginedMemberId = 0;

		if (httpsession.getAttribute("loginedMemberId") != null) {// 로그인 상태 유무판별하기 위해서 session사용,널값이 아니면 로그인상태이다.
			isLogined = true;// 로그인상태

			loginedMemberId = (int) httpsession.getAttribute("loginedMemberId");
		}

		Article article = articleService.getForPrintArticle(id,loginedMemberId);

		if (article == null) {

			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));// 2개인자로 받는 from()함수를 실행시키고 리턴으로 data1를
																			// null값으로 주는 3개인자from()실행
		}

		return ResultData.from("S-1", Ut.f("%d번 게시물 입니다.", id), article,"Article");// 게시글이 있을때는 해당게시글까지 포함해서 3개인자를 넘겨준다.

	}

}
