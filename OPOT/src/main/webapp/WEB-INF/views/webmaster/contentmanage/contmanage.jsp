<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<!-- 본문시작 contentrlist.jsp -->
	
	
<!-- 관리자페이지 : 콘텐츠 리스트 읽어오기 -->
	
  <div class="container text-center">	
	
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 제목을 누르면 수정페이지로 이동합니다 </strong></span>
		<br>
	</div>
	
	<input type="button" value="컨텐츠추가" onclick="location.href='/addcontent.do'" class="btn btn-danger"><br><br>
	
		
	<table class="table">
		<tr>

			<th>컨텐츠코드</th>
			<th>컨텐츠제목</th>
		</tr>

		<c:forEach var="dto" items="${list}"> 	
			<tr>
				<td>${ dto.mcode }</td>
				<td><a href="contupdate.do?mcode=${ dto.mcode }">${dto.mtitle }</a></td>				
			</tr>	
		</c:forEach>
		
		
		<tr>
			<td colspan='2' style='text-align:center; height: 50px'>
				<form action="contmanage.do">
					<select name="col">
						<option value="mtitle" >컨텐츠제목
						<option value="mcode">컨텐츠코드
					</select>
					<input type="text" name="word" id="word">
					<input type="submit" value="검색" class="btn btn-danger">
				</form>
			</td>
		</tr>
		<tr>
			<td colspan='2' style='text-align:center; height: 50px;' >
			${paging}

			</td>
		</tr>		
	</table>


	<form name="frm" method="post" action="<%=request.getContextPath()%>/logout.do">		
		<input class="btn btn-default btn-lg" type="button" value="관리자페이지" onclick="location.href='<%=request.getContextPath()%>/webmaster/webmaster.do'">		
		<input class="btn btn-default btn-lg" type="submit" value="로그아웃">
	</form>

  </div>	
	



<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>