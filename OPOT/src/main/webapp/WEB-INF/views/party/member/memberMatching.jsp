<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<!-- 본문시작 memberMatching.jsp -->
	
	<!-- 파티원 정보 입력 -->
	
	<div class="container-fluid text-center">
	<form name="frm" method="post" action="party/membermatch.do">
		<input type="hidden" id="ott_name" name="ott_name" value="${ ott_name }">
		<input type="hidden" id="mem_id" name="mem_id" placeholder="회원아이디" value="${ mem_id }"><!-- 세션정보에서 가져오기 -->
		<input type="hidden" id="ott_price" name="ott_price" value="${ ott_price }">
		<h1>${ ott_name }</h1>
		
		<br>
		<br>
		<h3>${ mem_id }님! 현재 가입 가능한 ${ ott_name } 파티가 없습니다</h3>
		<h3>결제는 매칭이 완료되면 진행됩니다~</h3>
		<h3>매칭이 완료되면 결제 후 마이페이지에서</h3>
		<h3>가입한 파티의 정보(ID,PW)를 확인 할 수 있습니다</h3>
		<br>
		<input type="button" value="홈화면"  class="w-btn w-btn-red" onclick="location.href='<%=request.getContextPath()%>/home.do'">
	</form>
	</div>




<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>