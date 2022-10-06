<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="게시글상세페이지"/>
<%@ include file="../common/head.jspf" %>
  
  <h1>상세페이지</h1>
  <table border="1">
  <colgroup>
  <col width="100">
   <col width="300">
  </colgroup>
    <tbody>
      <tr>
        <th>번호</th>
        <td>${article.id}</td>
       </tr>
      <tr> 
        <th>제목</th>
        <td>${article.title}</td>
      </tr>
      <tr>
        <th>내용</th>
        <td>${article.body}</td>
      </tr>
      <tr>
        <th>작성날짜</th>
        <td>${article.regDate.substring(0,10)}</td>
      </tr>
      <tr>
        <th>수정날짜</th>
        <td>${article.updateDate.substring(0,10)}</td>
      </tr>
      <tr>
        <th>작성자</th>
        <td>${article.memberId}</td>
      </tr>
    </tbody>
  </table>
  
  <div class="btns">
      <button type="button" onclick="history.back();">뒤로가기</button>
      <button type="button" onclick="location.href='../home/main' ">HOME</button>
    </div>
<%@ include file="../common/foot.jspf" %>