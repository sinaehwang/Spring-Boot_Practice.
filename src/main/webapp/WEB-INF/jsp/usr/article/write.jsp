<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="게시글 작성페이지"/>
<%@ include file="../common/head.jspf" %>

 <section class="mt-5"  >
    <div class="container mx-auto px-3">
    <form class = "table-box-type-1 " method="POST" action ="../article/doAdd">
    <input type="hidden" name="id" value="${article.id }" />
  <table class = "table w-full">
  <colgroup>
       <col width="100">
  </colgroup>
    <tbody>

      <tr> 
        <th>게시판목록</th>
        <td class="text-align-left text-indent text-strong text-blue" colspan="3">
          <input type="checkbox" name="boardId" value="1" id="ch1"><label for="ch1">공지사항</label>
          <input type="checkbox" name="boardId" value="2" id="ch2"><label for="ch2">자유게시판</label>
        </td>
      </tr>
      
      <tr> 
        <th>제목</th>
        <td><input class="w-96 input input-bordered" name="title" type="text"
                placeholder="제목"/></td>
      </tr>
      <tr>
        <th>내용</th>
            <td>
              <textarea class="w-full input input-bordered"  name="body" rows="10" placeholder="내용"></textarea>
            </td>
      </tr>
      
      <tr>
        <th>작성자</th>
        <td>${rq.loginedMember.nickname}</td>
      </tr>
               
      <tr>
         <th>작성</th>
         <td>
            <button type="submit" class="btn btn-outline btn-primary">작성</button>
            <button type="button" class="btn btn-outline btn-primary" onclick="history.back();">취소</button>
         </td>
     </tr>
     
    </tbody>
  </table>
  </form>
  
  <div class="btns mt-5">
      <button class="btn btn-link" type="button" onclick="history.back();">뒤로가기</button>
      <button class="btn btn-link" type="button" onclick="location.href='../home/main' ">HOME</button>
  </div>
  
  </div>
 </section>
   

<%@ include file="../common/foot.jspf" %>