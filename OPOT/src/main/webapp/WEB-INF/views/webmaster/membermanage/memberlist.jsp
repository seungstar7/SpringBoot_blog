<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<!-- 본문시작 memberlist.jsp -->
	
	
	<!-- 관리자페이지 : 회원정보 읽어오기 -->
	
  <div class="container text-center">	

	<div class="pagetitle">
		<br>
		<span><strong> 회원정보</strong></span>
		<h5>회원아이디를 누르면 회원레벨수정페이지로 이동합니다</h5>
		<br>
	</div>
		
	<table class="table table-hover">
		<tr>
			<th>회원ID</th>
			<th>연락처</th>
			<th>이메일</th>
			<th>가입일</th>
			<th>생년월일</th>
			<th>회원등급</th>
		</tr>

		<c:forEach var="dto" items="${list}"> 	
			<tr>
				<td><a href="memread.do?mem_id=${dto.mem_id }">${dto.mem_id }</a></td>				
				<td>${dto.mem_phone }</td>
				<td>${dto.mem_email }</td>
				<td>${dto.mem_reg }</td>
				<td>${dto.mem_birth }</td>
				<td>${ dto.mem_lv }</td>
				
			</tr>	
		</c:forEach>
		  <tr>
				<td colspan='6' style='text-align:center; height: 50px'>
					<form action="memberlist.do">
						<select name="col">
							<option value="mem_id">아이디
							<option value="mem_phone">연락처
							<option value="mem_email">이메일
							<option value="mem_lv">회원등급
						</select>
						<input type="text" name="word" id="word">
						<input type="submit" value="검색" class="btn btn-danger">
					</form>
				</td>
			</tr>
			<tr>
			<td colspan='6' style='text-align:center; height: 50px;' >
			${paging}

			</td>
		</tr>
		
	</table>

	<form name="frm" method="post" action="<%=request.getContextPath()%>/logout.do">		
		<input class="btn btn-default btn-lg" type="button" value="관리자페이지" onclick="location.href='<%=request.getContextPath()%>/webmaster/webmaster.do'">		
		<input class="btn btn-default btn-lg" type="submit" value="로그아웃">
	</form>

  </div>	
	



<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>