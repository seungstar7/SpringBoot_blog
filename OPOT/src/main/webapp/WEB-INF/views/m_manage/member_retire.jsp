<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<%@ include file="../header.jsp"%>
<!-- 본문시작 회원 탈퇴 member_retire -->
<div class="container text-center"> 
	<div class="pagetitle"><!-- css에  -->
      <br>
      <span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
      <span><strong> 회원 탈퇴 </strong></span>
      <br><br>
   </div>
   
   <form id = "mem_retire" name="mem_retire" action = "member_retire.do" method = post>
	  <input type="password" id="mem_pw" name="mem_pw" placeholder="비밀번호를 입력해주세요" required>
	  <input type="submit" value="탈퇴" id="mem_retire" class="btn btn-default">
   </form>	
</div>
<%@ include file="../footer.jsp"%>