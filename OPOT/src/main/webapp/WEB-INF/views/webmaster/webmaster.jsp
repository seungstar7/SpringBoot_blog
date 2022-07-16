<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본문시작 webmaster.jsp -->
	
	
	<!-- 관리자페이지 -->
	
  <div class="container text-center">	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 관리자페이지 </strong></span>
		<br><br>
	</div>
	<form name="frm" method="post" action="<%=request.getContextPath()%>/logout.do">		
		<input class="btn btn-default btn-lg btn-block" type="button" value="컨텐츠관리" onclick="location.href='/contmanage.do'">
		<input class="btn btn-default btn-lg btn-block" type="button" value="배우/감독관리" onclick="location.href='/peoplemanage.do'">
		<input class="btn btn-default btn-lg btn-block" type="button" value="추천글관리" onclick="location.href='/themelist.do'">
		<input class="btn btn-default btn-lg btn-block" type="button" value="회원관리" onclick="location.href='/memberlist.do'">
		<input class="btn btn-default btn-lg btn-block" type="button" value="파티관리" onclick="location.href='/parties.do'">
		<input class="btn btn-default btn-lg btn-block" type="button" value="공지사항관리" onclick="location.href='/notice/notice.do'">
		<hr>
		<input class="btn btn-default btn-lg" type="submit" value="로그아웃">
	</form>
  
  </div>	
	



<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>