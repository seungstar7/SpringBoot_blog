<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<!-- 본문시작 memberMatched.jsp -->
	
	<!-- 파티원 정보 입력 -->
	
	<div class="container-fluid text-center">
	<form name="frm">

		<h1>${ ott_name }</h1>
		
		<h3>${ mem_id }님 매칭이 완료되었습니다!</h3>
		<h3>가입한 파티 정보 : ${ ott_name }</h3>
		<h3>결제된 가격 : ${ party_pcost } </h3>
		<h3>주문번호 : ${ party_ordnumber }</h3>
		<h3>가입된 정보는 my구독관리에서 확인 할 수 있습니다</h3>
		
		<br>
		<input type="button" value="홈화면"  class="w-btn w-btn-red" onclick="location.href='<%=request.getContextPath()%>/home.do'">
	</form>
	</div>




<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>