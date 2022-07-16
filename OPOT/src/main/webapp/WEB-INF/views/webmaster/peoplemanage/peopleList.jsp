<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<!-- 본문시작 peopleList.jsp -->	
	
	
  <div class="container text-center">	
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 배우/감독 리스트 </strong></span>
		<br><br>
	</div>
		
	<table class="table">
		<tr>
			<th>이름</th>
		</tr>

		<c:forEach var="dto" items="${list}"> 
			<tr>
				<td>
				  <c:choose>
				    <c:when test="${ dto.pphoto==null || dto.pphoto eq '' }">
				      <a href="peopleupdate.do?pno=${ dto.pno }">
				      	<img src="../../../storage/people_None.jpg" class="img-circle" alt="img" width="50" height="50">
				       </a>			      
				    </c:when>
				    <c:otherwise>
				      <a href="peopleupdate.do?pno=${ dto.pno }">
				         <img src="../../../storage/${ dto.pphoto }" class="img-circle" alt="img" width="50" height="50">
				      </a>
				    </c:otherwise>
				   </c:choose>
				   <a href="peopleupdate.do?pno=${ dto.pno }">${dto.pname }</a>
				 </td>
			</tr>
		</c:forEach>
		<tr>
			<td style='text-align:center; height: 50px;'> ${ paging } </td>
		</tr>			
	</table>

		<input class="btn btn-default btn-lg" type="button" value="관리자페이지" onclick="location.href='<%=request.getContextPath()%>/webmaster/webmaster.do'">		
		<input class="btn btn-default btn-lg" type="button" value="배우/감독" onclick="location.href='<%=request.getContextPath()%>/peoplemanage.do'">		


  </div>	
	



<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>