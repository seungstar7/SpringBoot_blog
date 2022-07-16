<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<!-- 본문시작 themeAdd.jsp -->
	
	
<!-- 관리자페이지 : 테마 추가하기 -->
	
  <div class="container text-center">	
	
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 추천 테마 추가 </strong></span>
		<br>
	</div>
	
		<button class="btn btn-danger" onclick="location.href='<%=request.getContextPath()%>/themelist.do'">테마목록</button><br><br>

	<div id="recfrmarea">
	
		<form name="themefrm" id="themefrm" method="post" action="themeins.do" enctype="multipart/form-data">
		<table class="table" style="margin:auto;">
			<tr>
			   <th>제목</th>
			   <td><input type="text" name="t_title" id="t_title" class="form-control" maxlength="30" required></td>
			</tr>
			<tr>
			   <th>대표사진</th>
			   <td><input type="file" name="t_photoMF" id="t_photoMF" class="form-control" required></td>
			</tr>
			<tr>
			   <th></th>
			   <td></td>
			</tr>
		</table> 
		
		
		<br>
		<div id="btnArea">
			<input id="bbsBtn" type="submit" value="쓰기" class="btn btn-default">
		    <input id="bbsBtn" type="reset"  value="취소" class="btn btn-default">
		</div>
		</form>
		
	</div>	
	
	
		


  </div>	
	



<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>