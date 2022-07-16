<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<!-- 본문시작 msgView.jsp -->

<div class="container-fluid text-center">


	<h2>${ msg }</h2>


	<input type="button" value="홈으로 이동" class="btn btn-success" onclick="location.href='/home.do'"> 
	<input type="button" value="파티매칭" class="btn btn-success" onclick="location.href='/party/partyadd.do'"> 

</div>
<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>