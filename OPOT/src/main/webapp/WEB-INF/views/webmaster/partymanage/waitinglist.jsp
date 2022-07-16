2<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<!-- 본문시작 waitinglist.jsp -->
	
	
	<!-- 관리자페이지 : 대기자정보 읽어오기 -->
	
  <div class="container text-center">	

	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 파티 대기자 정보 </strong></span>
		<br><br>
	</div>
		
	<table class="table table-hover">
		<tr>
			<th>대기번호</th>
			<th>아이디</th>
			<th>구독희망OTT</th>
			<th>생성날짜</th>
		</tr>

		<c:forEach var="dto" items="${list}"> 	
			<tr>
				<td>${dto.waiting_no }</td>
				<td>${dto.mem_id }</td>				
				<td>${dto.ott_name }</td>
				<td>${dto.waiting_date }</td>
			</tr>	
		</c:forEach>
		
	</table>

  </div>	
	



<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>