<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<!-- 본문시작 memberread.jsp -->
	
	
	<!-- 관리자페이지 : 회원정보 읽어오기 -->
	
  <div class="container text-center">
  	<div class="pagetitle">
		<br>
		<span><strong> 회원상세정보</strong></span>
		<h5>A:관리자 B:일반회원 F:탈퇴</h5>
		<br>
	</div>	
	<form method="post" action="memlvupdate.do?mem_id=${ dto.mem_id }" onsubmit="return memlvCheck()">		
		<table class="table">
			<tr>
				<th>회원ID</th>
				<td>${dto.mem_id }</td>	
			</tr>	
			<tr>
				<th>연락처</th>							
				<td>${dto.mem_phone }</td>
			</tr>	
			<tr>
				<th>이메일</th>
				<td>${dto.mem_email }</td>

			</tr>	
			<tr>
				<th>가입일</th>
				<td>${dto.mem_reg }</td>
			</tr>	
			<tr>
				<th>생년월일</th>
				<td>${dto.mem_birth }</td>
			</tr>
			<tr>				
				<th>회원등급</th>
				<td>
				  <select class="form-control" id="mem_lv" name="mem_lv">
	                   <option value="A" id="mem_lv_A">A</option>
	                   <option value="B" id="mem_lv_B">B</option>
	                   <option value="F" id="mem_lv_F">F</option>
	               </select>
				</td>				
			</tr>
			<tr>
				<th colspan="2"></th>
			</tr>				
		</table>
		
		<input type="submit" class="btn btn-default" value="수정">
		<input type="button" class="btn btn-default" value="목록" onclick="location.href='/memberlist.do'">
			
	</form>
  </div>	

	<script>
	
		var oldMemLv="${ dto.mem_lv }";	
		var newMemLv="";
		
	    $(document).ready(function(){
	         if("${ dto.mem_lv }"=="A"){
	             $("#mem_lv_A").attr("selected", "selected");
	         }else if("${ dto.mem_lv }"=="B"){
	             $("#mem_lv_B").attr("selected", "selected");
	         }else{
	             $("#mem_lv_F").attr("selected", "selected");
	         }//if end
	         newMemLv=$('#mem_lv option:selected').val();
	    });
		
	    
	    $("select[name=mem_lv]").change(function(){
	    	  $(this).val();
	    	  $("select[name=mem_lv] option:selected").text();
	    	  newMemLv=$('#mem_lv option:selected').val();  	  
	    	  //alert(newMemLv);    	  
	    }); 
	
	    
		function memlvCheck(){			
			if(oldMemLv==newMemLv){
				alert("기존과 회원레벨이 같습니다");
				return false;
			}else{
				//alert("기존과 회원레벨 다름");
				confirm("회원레벨을 수정하시겠습니까?");
			}
		}
	
	</script>	



<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>