<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<%@ include file="../header.jsp"%>
<!-- 본문시작 myaccount.jsp -->
	
	<!-- 등록된 카드/계좌 정보 확인 -->
	<div class="container" align="center">
		<div class="pagetitle">
			<br>
			<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
			<span><strong> 결제카드/정산계좌 정보 </strong></span>
			<h4><strong>${ s_mem_id }</strong>님 등록된 카드와 계좌 정보입니다</h4><hr>
		</div>
		
		<div class="account-bg" id="card-wrap" style="background-image:url('/images/img_card.png')">
			<div class="account-info-wrap">
				<div class="account-info-top" id="card_com">				
					<c:choose>
						<c:when test="${ cardmsg1 != null }">${ cardmsg1 }</c:when>
						<c:otherwise>${ cardinfo.card_com }</c:otherwise>
					</c:choose>				
				</div>
				<div class="account-info-below" id="card_no">
					<c:choose>
						<c:when test="${ cardmsg2 != null }">${ cardmsg2 }</c:when>
						<c:otherwise>${ cardinfo.card_no }</c:otherwise>
					</c:choose>				
				</div>
				<div class="account-btn">
					<c:choose>
						<c:when test="${ cardmsg1 != null }">
							<button class="btn" onclick="location.href='member_cardreg.do?mem_id=${ s_mem_id }'">등록</button>
						</c:when>
						<c:otherwise>
							<button class="btn" onclick="location.href='member_card.do?mem_id=${ s_mem_id }'">수정</button>
						</c:otherwise>
					</c:choose>		
				
				</div>				
			</div>			
		</div>

		<div class="account-bg" id="bank-wrap"  style="background-image:url('/images/img_bank.png')">
			<div class="account-info-wrap">
				<div class="account-info-top" id="bank_name">
					<c:choose>
						<c:when test="${ bankmsg1 != null }">${ bankmsg1 }</c:when>
						<c:otherwise>${ bankinfo.bank_name }</c:otherwise>
					</c:choose>					
				</div>
				<div class="account-info-below" id="bank_account">
					<c:choose>
						<c:when test="${ bankmsg2 != null }">${ bankmsg2 }</c:when>
						<c:otherwise>${ bankinfo.bank_account }</c:otherwise>
					</c:choose>		
				</div>
				<div class="account-btn">
					<c:if test="${ bankmsg1 == null }">
						<button class="btn" onclick="location.href='member_bank.do?mem_id=${ s_mem_id }'">수정</button>
					</c:if>	
				</div>	
			</div>			
		</div>
		

	</div>
	

<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>