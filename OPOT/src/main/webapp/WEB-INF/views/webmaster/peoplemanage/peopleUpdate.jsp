<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<!-- 본문시작 peopleUpdate.jsp -->
	
	
	
  <div class="container text-center">	
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 배우/감독 상세보기 및 수정 </strong></span>
		<br>
		<input class="btn btn-default" type="button" value="감독" onclick="location.href='/directors.do'">
		<input class="btn btn-default" type="button" value="배우" onclick="location.href='/actors.do'">
		<br><br>
	</div>


			
	<form name="peoplefrm" id="peoplefrm" method="post" action="peopleupdateproc.do?pno=${ dto.pno }" enctype="multipart/form-data">
		<table class="table" style="margin:auto;">
			<tr>
			   <th>이름</th>
			   <td colspan="2"><input type="text" name="pname" id="pname" value="${ dto.pname }" class="form-control" maxlength="30" required></td>
			</tr>
			<tr>
			   <th>영문이름</th>
			   <td colspan="2"><input type="text" name="pname_eng" id="pname_eng" value="${ dto.pname_eng }" class="form-control" maxlength="30"></td>
			</tr>
			<tr>
			   <th>프로필사진</th>
			   <td><input type="file" name="pphotoMF" id="pphotoMF" class="form-control"></td>
			   <td>현재 프로필 사진명 : ${ dto.pphoto } </td>
			</tr>
			<tr>
			   <th colspan="3">
			   </th>
			</tr>
		</table> 
			   
	   	<input type="submit" value="수정" class="btn btn-danger">
		<input type="reset"  value="취소" class="btn btn-danger">

	</form>

  </div>	
	



<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>