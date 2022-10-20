<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--커스텀액션을 사용하겠다는 정의  -->

<c:set var="pageTitle" value = " ${board.name } 리스트"/> 
<!--변수 pageTitle에 값을 넣는거에 따라 head.jsp에서 pageTitle에 들어가는값이 달라짐(head.jsp보다 변수가 먼저 만들어져야함) -->
 <%@ include file="../common/head.jspf" %> <!--상단headjsp를 불러온다 -->

 <section class="mt-5"  >
    <div class="container mx-auto px-3">
    <div>총 게시글 목록:${articlesCount}개</div>
    <div class = "table-box-type-1">
  <table  class = "table w-full ">
  <colgroup>
    <col width="50">
    <col width="150">
    <col width="100">
    <col width="100">
    <col width="50">
  </colgroup>
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성날짜</th>
        <th>작성자</th>
      </tr>
    </thead>
    <tbody><!--$로 model에서 저장한 키값을 불러와서 사용할수 있다.  -->
     <c:forEach var="article" items="${articles }"> <!--c:forEach는 범위내에서 반복문을 수행함,articles를 "article"변수에 담아서 사용한다.  -->
      <tr class = "hover">
        <td>${article.id}</td>
        <td>
        <a class ="btn-text-link hover:underline" href="../article/detail?id=${article.id}">${article.title}</a> <!--제목클릭시 해당게시글상세페이지에 넘어가도록  -->
        </td>
        <td>${article.regDate}</td>
        <td>${article.extra_writerName }</td>
      </tr>
     </c:forEach>
    </tbody>
  </table>
  </div>
  
    <div class="page-box flex justify-center mt-3">
      <div class="btn-group">
      <c:set var="pageMemuArmLen" value="4" />
        <c:set var="startPage"
          value="${page - pageMemuArmLen >= 1 ? page - pageMemuArmLen : 1}" /><!-- 현재페이지에서 지정길이보다 짧다면 첫페이지를 1로지정 -->
        <c:set var="endPage"
          value="${page + pageMemuArmLen >= pagesCount ? pagesCount : page + pageMemuArmLen}" /><!-- 현재페이지와 지정길이를 합한게 끝페이지를 넘는다면 끝페이지를 end로지정 -->

        <c:if test="${startPage > 1}">
          <a class="btn btn-sm" href="?page=1">1</a>
          <c:if test="${startPage > 2}">
            <a class="btn btn-sm btn-disabled">...</a>
          </c:if>
        </c:if>
        <c:forEach begin="${startPage }" end="${endPage }" var="i">
          <a class="btn btn-sm ${page == i ? 'btn-active' : ''}"
            href="?page=${i}">${i}</a>
        </c:forEach>
        <c:if test="${endPage < pagesCount}">
          <c:if test="${endPage < pagesCount - 1}">
            <a class="btn btn-sm btn-disabled">...</a>
          </c:if>
          <a class="btn btn-sm" href="?page=${pagesCount }">${pagesCount }</a>
        </c:if>
      </div>
    </div>

  </div>
 </section>
 <%@ include file="../common/foot.jspf" %>