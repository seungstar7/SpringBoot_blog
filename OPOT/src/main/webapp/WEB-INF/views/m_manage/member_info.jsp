<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<%@ include file="../header.jsp"%>

<script type="text/javascript" src="../js/member_action.js"></script>
<!-- 본문시작 member_info.jsp -->

<!-- 회원 정보 수정 시작 -->

<div class="container text-center">
	
	<div class="pagetitle"><!-- css에  -->
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 회원 정보 수정 </strong></span>
		<br><br>
	</div>
	
	<form name="mem_info" id="mem_info" method="post" action="/member_info.do" onsubmit="return updateFormCheck();">
	
		<table class="table memTable" id="memTable">
			
		<tr>
		    <th>아이디</th>
		    <td>
		      <input type="text" class="form-control" name="mem_id" id="mem_id" value="${ dto.mem_id }" size="10" readonly>
		    </td>
		</tr>
		<tr>
		    <th>생년월일</th>
		    <td>
		      <input type="text" class="form-control" name="mem_birth" id="mem_birth" value="${ dto.mem_birth }" readonly>
		    </td>
		    <td>  

		    </td>
		</tr>
		<tr>
		    <th>현재비밀번호*</th>
		    <td>
		      <input type="password" class="form-control" name="mem_pw" id="mem_pw" size="20" required>
		      <font id="checkPw" size="2"></font>
		    </td>
		</tr>
		<tr>
		    <th>신규비밀번호</th>
		    <td>
		      <input type="password" class="form-control" name="new_pw" id="new_pw" size="20">
		    </td>
		</tr>
		<tr>
		    <th>비밀번호 확인</th>
		    <td>
		      <input type="password" class="form-control" name="newre_pw" id="newre_pw" size="20" onblur="newpwCheck()">
		      <font id="checknPw" size="2"></font>
		    </td>
		</tr>
		<tr>
		    <th>연락처*</th>
		    <td>
		      <input type="text" class="form-control" name="mem_phone" id="mem_phone" value="${ dto.mem_phone }" required onblur="phoneCheck()">
		    </td>
		    <td></td>
		</tr>
		<tr>
		    <th>이메일*</th>
		    <td>
      		  <input type="text" class="form-control" name="mem_email" id="mem_email"  value="${ dto.mem_email }" required>
		    </td>
		</tr>
		
	
		<tr>
		  	<th>가입날짜</th>
		  	<td colspan="2">
		  	 	${ dto.mem_reg }
		  	</td>
		</tr>
		<tr>
		  	<td colspan="3">
		  	</td>
		</tr>		
	
		</table>
				
		<input type="submit" value="수정"  id="memBtn" class="btn btn-default"/>
        <input type="reset"  value="취소"  id="cancle" class="btn btn-default"/>
        <input type="button" value="탈퇴" id="retire" class="btn btn-default" onclick="location.href='member_retire.do'"/>
		<br><br><br>
		
	</form>

</div>


<!-- 회원정보 수정 끝 -->
	

<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>