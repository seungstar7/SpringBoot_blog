<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@page import="net.utility.Paging"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  

<!-- 본문시작 noticelist.jsp -->
  	<div class="container text-center">
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 공지사항 </strong></span>
		<br>
	</div>	


		<div class="container-fluid text-center">
		<table class="table">
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="dto" items="${list}">
		<tr>
			<td>${dto.n_num}</td>	
			<td><a href="../notice/noticeread.do?n_num=${dto.n_num}">${dto.n_title}</a></td>
			<td>${dto.n_date}</td>
			<td>${dto.n_readcnt}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan='4' style='text-align:center; height: 50px'>
				<form action="notice.do">
					<select name="col">
						<option value="n_title_n_content">제목+내용
						<option value="n_title">제목
						<option value="n_content">내용
					</select>
					<input type="text" name="word" id="word">
					<input type="submit" value="검색" class="btn btn-danger">
				</form>
			</td>
		</tr>
		<tr>
			<td colspan='4' style='text-align:center; height: 50px;' >
			${paging}

			</td>
		</tr>
		</table>
		</div>
	</div>
	
	<c:if test="${ s_mem_lv=='A' }">
		<input type="button" value="공지사항등록" onclick="location.href='/notice/noticeForm.do'" class="btn btn-default"><hr>	
	</c:if>

<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>