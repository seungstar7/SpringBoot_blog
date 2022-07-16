<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<%@ include file="../header.jsp"%>

<script type="text/javascript" src="../js/member_action.js"></script>

<div class="container" align="center">
	<form id="id_form" method="post" onsubmit="return findId();">
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 아이디 찾기 </strong></span>
		<h5>가입할 때 입력하신 전화번호와 이메일을 입력해주세요</h5>
		<h5>정보가 일치하면 이메일로 아이디를 보내드립니다</h5>
	</div>

	<table class="table table-bordered" id="login_table" style="width:500px; margin:auto;">
	 <tr>
	 	<th>전화번호</th>
	 	<td>
	 	   <input type="text" id="mem_phone" name="mem_phone" class="form-control" placeholder="예)010-0000-0000" required>
	 	</td>
	 </tr>
	 <tr>	
	    <th>이메일 </th>
	    <td>
	       <input type="email" id="mem_email" class="form-control" name="mem_email" required>
	    </td>    
	  </tr>
	 </table><br>
	 <input class="btn btn-default" type="submit" id="findIdBtn" value="아이디 찾기">
     <input class="btn btn-default" type="reset" id="cancle" value="취소">
     </form>
    
</div>

<%@ include file="../footer.jsp" %>