<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>
<!-- 본문시작 msgView.jsp -->

  <div class="container-fluid text-center">
  	<h1>${ ott_name }</h1>
  	
  	

	
	<h3>${ ott_name }프리미엄 기존 프리미엄 서비스</h3> <br>
	<h3> ${ott_price}원-> ${party_pcost}원</h3>
	<h3>${payback_amount}원 절약!!</h3>
	<br>
	<h4>${ dto.card_com }카드로 결제 및 매칭진행 하시겠습니까?</h4>

	
	<form method="post" action="membermatch.do">	
		<input type="submit" value="바로매칭하기" class="w-btn-neon2">
		<input type="hidden" id="mem_id" name="mem_id" placeholder="회원아이디" value="${ mem_id }">
		<input type="hidden" id="ott_name" name="ott_name" value="${ ott_name }"> 
		<input type="hidden" id="ott_price" name="ott_price" value="${ ott_price }">
		<input type="hidden" id="party_pcost" name="party_pcost" value="${ party_pcost }">
	</form>
	
	<br><br>
  	<h3>${ dto.card_com }카드</h3>
  	<form method="post" action="cardupdate.do">
		<input type="submit" value="결제카드변경하기" class="w-btn-neon2">
	</form>

    
    

    
  </div>  

<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>