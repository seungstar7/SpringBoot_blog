<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<!-- 본문시작 payback.jsp -->
	
	<!-- 정산 금액 및 일자 체크 -->
	
	<div class="container-fluid text-center">	
	
		<div>매월 정산받는 금액과 일자를 확인해주세요</div>
	    <form name="frm" method="post" action="account.do">
			
			<input type="hidden" id="ott_name" name="ott_name" value="${ ott_name }">
			<input type="hidden" id="ott_price" name="ott_price" value="${ ott_price }">
			<input type="hidden" id="payback_amount" name="payback_amount" value="${ payback_amount }">
			
			
			<h1>${ ott_name }</h1>
			
			혼자 사용할 때 금액 : ${ ott_price }<br>
			
			OTT 페이백 : ${ ott_price/4 }원 X 파티원 3명 <br>
			OPOT 수수료 : ${ service_fee }원<br>
			
			<h3>매월 정산받는 금액 : ${ payback_amount }</h3><br>

			<hr>
			<h3>정산일자 : 매 월 ${ payback_day }일</h3><br>
			<h5>첫 정산일자 : ${ first_payback_month } 월 ${ payback_day }일</h5>
			<h6>한달간 계정이 안전하게 공유된 후 정산해드려요</h6>
			
			<hr>

		    <div>
		      <input type="submit" value="다음" class="w-btn-neon2">
		    </div>  
	    </form>
	
	</div>




<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>