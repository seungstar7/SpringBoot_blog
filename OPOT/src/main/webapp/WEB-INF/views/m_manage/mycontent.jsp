<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<%@ include file="../header.jsp"%>
<!-- 본문시작 mysubscribe.jsp -->
<!-- 구독OTT/파티 관리 -->
<div class="container text-center">

	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 나의 컨텐츠 </strong></span>
		<br>
	</div>
	
	<h4><strong>${ s_mem_id }</strong> 컨텐츠 목록 </h4><hr>
	<div class="btn-group">
		<button class="btn btn-danger btn-lg" onclick="location.href='/watchlist.do?mem_id=${ s_mem_id }'">시청 목록</button>
		<button class="btn btn-danger btn-lg" onclick="location.href='/likecontent.do?mem_id=${ s_mem_id }'">좋아요한 컨텐츠</button>
		<button class="btn btn-danger btn-lg" onclick="location.href='/pointcontent.do?mem_id=${ s_mem_id }'">찜한 컨텐츠</button>
	</div>
	<br><br>
	
	
	


	
</div>
<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>