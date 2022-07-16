<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<script type="text/javascript">

 function noBack(){
	 
	 window.history.forward();
	 }
 
</script>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="back()">



<!-- 본문시작 memberMatch.jsp -->
	
	<!-- 파티원 정보 입력 -->
	
	<div class="container-fluid text-center">
	<form name="frm" method="post" action="membermatch.do">
		<input type="hidden" id="ott_name" name="ott_name" value="${ ott_name }">
		<input type="hidden" id="mem_id" name="mem_id" placeholder="회원아이디" value="${ mem_id }"><!-- 세션정보에서 가져오기 -->
		<input type="hidden" id="ott_price" name="ott_price" value="${ ott_price }">
		<input type="hidden" id="party_pcost" name="party_pcost" value="${ party_pcost }">
		<h1>${ ott_name }</h1>
		
	
		<h3>${ ott_name }프리미엄 기존 프리미엄 서비스</h3> <br>
		<h3> ${ott_price}원-> ${party_pcost}원</h3>
		<h3>${payback_amount}원 절약!!</h3>
		<br>
		<h4>${ dto.card_com }카드로 결제 및 매칭진행 하시겠습니까?</h4>
		
		<br>
		파티 매칭을 시작하시겠습니까?
		<br>
		<input type="submit" value="시작하기"  class="w-btn-neon2">
	</form>
	</div>




<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>