<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  

	<!-- 메인카테고리 끝 -->

	<!-- 유튜브 연결 -->
	<div id="cont_watch" class="container-fluid text-center">
			
		<iframe width="560" height="315" src="https://www.youtube.com/embed/${ maudio }" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		
	</div>

	<!-- 본문끝 -->

<%@ include file="../footer.jsp"%>