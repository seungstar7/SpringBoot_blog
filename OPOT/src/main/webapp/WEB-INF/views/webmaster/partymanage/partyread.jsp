<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<!-- 본문시작 partyread.jsp -->
	
	
	<!-- 관리자페이지 : 파티정보 상세보기 -->
	
  <div class="container text-center">	

	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 파티 상세 페이지 </strong></span>
		<br><br>
	</div>
	
	<h2>파티아이디 : ${ partyDTO.party_id }</h2>
	<h3>구독OTT : ${ partyDTO.ott_name }</h3>
	<h4>OTT계정아이디 : ${ partyDTO.ott_id } / OTT계정비번 : ${ partyDTO.ott_pw }</h4>
	<hr>
	<h5>현재 매칭인원 : ${ partyDTO.matching_no }</h5>
	<h5>현재 매칭상태 : 
		<c:choose>
		  <c:when test="${ partyDTO.matching_no==4 }">매칭완료</c:when>
		  <c:when test="${ partyDTO.matching_no!=4 }">매칭진행중</c:when>		  
		</c:choose>
	</h5>
	
	
	<hr>
		
	파티장 상세보기
	
	<table class="table table-hover">
		<tr>
			<th>회원아이디</th>
			<th>파티생성날짜</th>
			<th>정산금액</th>
			<th>정산계좌</th>
			<th>정산결과</th>			
		</tr>

			<tr>
				<td>${partyDTO.mem_id }</td>
				<td>${partyDTO.ott_cdate }</td>				
				<td>${partyDTO.payback_amount }</td>
				<td>${partyDTO.bank_name } ${ partyDTO.bank_account }</td>
				<td>${partyDTO.payback_result }</td>
				
			</tr>
		
	</table>
	
	
	
	
	<hr>
	
	파티원 상세보기 
	
	<table class="table table-hover">
		<tr>
			<th>회원아이디</th>
			<th>결제금액</th>
			<th>결제날짜</th>
			<th>주문번호</th>
		</tr>

		<c:forEach var="dto" items="${list}"> 	
			<tr>
				<td>${dto.mem_id }</td>
				<td>${dto.party_pcost }</td>				
				<td>${dto.party_pdate }</td>
				<td>${dto.party_ordnumber }</td>
			</tr>
		</c:forEach>
		
	</table>
	
	
	
	

  </div>	
	



<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>