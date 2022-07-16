<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<%@ include file="../header.jsp"%>
<!-- 본문시작 login.jsp -->

<div class="container text-center">

	<div class="pagetitle">
		<br>
		<span><img src="/images/person_icon.png" alt="person" width="50px"></span>
		<span><strong> LOGIN </strong></span>
		<br>
	</div>


	<c:set var="s_mem_id" value="${ s_mem_id }"></c:set>
	<c:set var="s_mem_pw" value="${ s_mem_pw }"></c:set>
	<c:set var="s_mem_lv" value="${ s_mem_lv }"></c:set>

  <c:choose>
	<c:when test="${ s_mem_id==null || s_mem_pw==null || s_mem_lv==null }">
		${ msg }
		<!-- ${ fn:length(msg) } -->
				
		<!-- 게스트일 때, 로그인 폼 출력 -->
		<form name="loginfrm" id="loginfrm" method="post" action="<%=request.getContextPath()%>/login.do" onsubmit="return">
		    
		    <input type="hidden" name="referrer" id="referrer">

			<table class="table table-bordered" id="login_table" style="width:500px; margin:auto;">
			<tr>
			    <td>
				   <input type="text" name="id" id="id" class="form-control" placeholder="아이디" maxlength="10" value="${ c_id }" required>
			    </td>
	
			    <td rowspan="2" width="90px">
			           <!-- type=image의 기본속성이 submit -->
				   <input type="image" src="../images/login_btn_bk.png" width="85px">		   
			    </td>
			</tr>
			<tr>
			   <td>
			      <input type="password" name="passwd" id="passwd" class="form-control" placeholder="비밀번호" maxlength="10" required>
			   </td>
			</tr>
			<tr>
			   <td colspan="2">
			   	  	<c:choose>
			   	  		<c:when test="${ c_id=='' }"><c:set var="check"></c:set></c:when>
			   	  		<c:otherwise><c:set var="check">checked</c:set></c:otherwise>
			   	  	</c:choose>
			   	  
			   	  
			      <label><input type="checkbox" name="c_id" value="SAVE" ${ check } >아이디 저장</label>	<!-- label로 감싸면 글씨 클릭해도 체크박스 체크됨 -->		  
				  &nbsp;&nbsp;&nbsp;
				  <a href="<%=request.getContextPath()%>/agreement.do">회원가입</a>
				  &nbsp;&nbsp;&nbsp;
				  <a href="find_info.do">아이디/비밀번호찾기</a>
			   </td>
			</tr>		  
			</table>
		</form>
		
	</c:when>
	
	<c:otherwise>
		
		<!-- 로그인 상태일 때, 로그아웃 버튼 출력 -->
		<form name="logoutfrm" id="logoutfrm" method="post" action="logout.do">
			<strong>${ s_mem_id }</strong> 님
			<br>
			<input type="submit" value="로그아웃">
			<br><br>
			<a href='member_info.do'>[회원정보수정]</a>
			&nbsp;&nbsp;
			<a href='member_retire.do'>[회원탈퇴]</a>
			<br><br>
		</form>
		
		
	</c:otherwise>
	
  </c:choose>

	<script>
		var referrer= document.referrer;
		$('#referrer').attr('value', referrer);
		
	</script>	


</div>
<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>