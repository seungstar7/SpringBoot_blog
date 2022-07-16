<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<!-- 본문시작 partymanage.jsp -->
	
	
	<!-- 파티관리페이지 -->
	
  <div class="container text-center">	

	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 파티관리 페이지 </strong></span>
		<br><br>
	</div>

	<form name="frm" method="post" action="<%=request.getContextPath()%>/logout.do">		
		<input class="btn btn-default btn-lg btn-block" type="button" value="파티목록관리" onclick="location.href='/partylist.do'">
		<input class="btn btn-default btn-lg btn-block" type="button" value="파티매칭대기목록" onclick="location.href='/waitinglist.do'">
		<hr>
		<input class="btn btn-default btn-lg" type="button" value="이전목록" onclick="location.href='<%=request.getContextPath()%>/webmaster/webmaster.do'">		
		<input class="btn btn-default btn-lg" type="submit" value="로그아웃">
	</form>
  
  </div>	
	



<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>