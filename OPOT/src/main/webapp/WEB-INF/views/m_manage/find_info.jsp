<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<%@ include file="../header.jsp"%>

<!-- 아이디/ 비밀번호 찾기 창 -->

	<div class="container" align="center">
		<form name= "infofind" id= "infofind" method="post" >
			<div class="pagetitle">
				<br>
				<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
				<span><strong> 아이디/ 비밀번호 찾기 </strong></span>
				<br>
			</div><hr>
			<div class="btns" >
   				<h3><strong><a href='find_id.do'>[아이디 찾기]</a></strong></h3>
                <h3><strong><a href='find_pw.do'>[비밀번호 찾기]</a></strong></h3>
			</div>
		</form>
	</div>
<%@ include file="../footer.jsp" %>