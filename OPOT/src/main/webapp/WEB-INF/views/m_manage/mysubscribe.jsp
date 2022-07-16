<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<%@ include file="../header.jsp"%>

<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css' rel='stylesheet' />
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>

<!-- 본문시작 mysubscribe.jsp -->
<!-- 구독OTT/파티 관리 -->
<div class="container text-center">

	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 구독OTT/파티정보 </strong></span>
		<br>
	</div>

	<h4><strong>${ s_mem_id }</strong> 님의 구독정보입니다 </h4>
		
    <div id='calendar-container'>    
  	  <div id='calendar'></div>  
    </div>
	<hr>
		<table class="table">
		<tr>
			<th>구독OTT</th>
			<th>이용금액</th>
			<th>이용역할</th>
			<th>구독시작일</th>
			<th>구독종료일</th>
			<th>OTT계정ID</th>
			<th>OTT계정PW</th>
			<th>파티탈퇴하기</th>
			
		</tr>
		
		<c:set var="savedFee" value="${ 0 }"></c:set>
		
		<c:forEach var="dto" items="${list}"> 	
			
			<c:choose>
				<c:when test="${dto.party_role eq '파티장' }">
					<c:set var="serviceFee" value="${ 400 }"></c:set>
				</c:when>
				<c:when test="${dto.party_role eq '파티원' }">
					<c:set var="serviceFee" value="${ 500 }"></c:set>
				</c:when>
			</c:choose>

			<tr>
				<td>${ dto.ott_name }</td>
				<td>${ fn:substringBefore(dto.ott_price/4+serviceFee, '.') }</td>
				<td>${ dto.party_role }</td>
				<td>${ dto.subscribe_start }</td>
				<td>${ dto.subscribe_end }</td>
				<td>${ dto.ott_id }</td>
				<td>${ dto.ott_pw }</td>
				<c:choose>
				<c:when test="${dto.party_role eq '파티장' }">
					<td>
					<form name="frm" id="frm" action="partyexit.do" method="post" onsubmit="return IDlog()">
		            	<input type="submit" value="파티장 탈퇴"  class="btn btn-default">
		            	<input type="hidden" id="party_id" name="party_id" value="${ dto.party_id }">
		            	<input type="hidden" id="mem_id" name="mem_id" value="${ s_mem_id }">
		            	<input type="hidden" id="ott_name" name="ott_name" value="${ dto.ott_name }">
					</form>	
					</td>
				</c:when>
				<c:when test="${dto.party_role eq '파티원' }">
					<td>
					<form name="frm" id="frm" action="partymemexit.do" method="post" onsubmit="return IDlog()">
		            	<input type="submit" value="파티원 탈퇴"  class="btn btn-default">
		            	<input type="hidden" id="party_id" name="party_id" value="${ dto.party_id }">
		            	<input type="hidden" id="mem_id" name="mem_id" value="${ s_mem_id }">
		            </form>	
					</td>
				</c:when>
				</c:choose>
			</tr>	
			
			<tr style="display: none;">
			  <td colspan="8">${ savedFee=savedFee+dto.ott_price/4+serviceFee }</td>
			</tr>
			
		</c:forEach>
		
		<tr>
			<td colspan="3">혼자 사용시 결제했을 금액</td>
			<td colspan="5">${ totalOttFee }</td>
		</tr>
		<tr>
			<td colspan="3">OPOT 파티에서 결제한 금액</td>
			<td colspan="5">${ fn:substringBefore(savedFee, '.') }</td>
		</tr>
		<tr>
			<td colspan="3">OPOT으로 절약한 금액</td>
			<td colspan="5"><strong>${ fn:substringBefore(totalOttFee-savedFee, '.') }</strong></td>
		</tr>
		
	</table>

<script>

document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      	initialView: 'dayGridMonth',
      	events: [
      		<c:forEach var="dto" items="${list}">
				{
					title : "${ dto.ott_name } 구독 종료" ,
					start : "${ fn:substring(dto.subscribe_end, 0, 10)}"
				}, 		
			</c:forEach>
      	]      	
    });
    calendar.render();
    calendar.addEvent(events);
  });


</script>
	



	
</div>
<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>