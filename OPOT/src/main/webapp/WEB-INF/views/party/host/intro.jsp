<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<!-- 본문시작 intro.jsp -->
	
	<!-- 파티 생성(파티장) 인트로 페이지 -->
	
	<div class="container-fluid text-center">	
	
		<div>파티 생성</div>
	    <form name="frm" method="post" action="host/payback.do">
			
			<input type="hidden" id="ott_name" name="ott_name" value="${ ott_name }">
			<input type="hidden" id="ott_price" name="ott_price" value="${ ott_price }">
			
			<h1>${ ott_name } 파티생성을 시작합니다!</h1>
			
			혼자 사용할 때 금액 : ${ ott_price }<br><hr>
		    
		    <div>
		      <input type="submit" value="시작하기" class="w-btn-neon2">
		    </div>  
	    </form>
	
	</div>




<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>