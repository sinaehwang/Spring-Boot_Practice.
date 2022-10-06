<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--커스텀액션을 사용하겠다는 정의  -->
<c:set var="pageTitle" value = "게시물리스트"/>
 <%@ include file="../common/head.jspf" %>
  <table border="5">
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성날짜</th>
        <th>수정날짜</th>
        <th>작성자</th>
      </tr>
    </thead>
    <tbody><!--$로 model에서 저장한 키값을 불러와서 사용할수 있다.  -->
     <c:forEach var="article" items="${articles }"> <!--c:forEach는 범위내에서 반복문을 수행함,articles를 "article"변수에 담아서 사용한다.  -->
      <tr>
        <td>${article.id}</td>
        <td>
        <a href="../article/detail?id=${article.id}">${article.title}</a> <!--제목클릭시 해당게시글상세페이지에 넘어가도록  -->
        </td>
        <td>${article.regDate.substring(0,10)}</td>
        <td>${article.updateDate.substring(0,10)}</td>
        <td>${article.memberId}</td>
      </tr>
     </c:forEach>
    </tbody>
  </table>
 <%@ include file="../common/foot.jspf" %>