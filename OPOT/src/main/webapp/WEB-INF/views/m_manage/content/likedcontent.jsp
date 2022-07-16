<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>
<!-- 본문시작 likedcontent.jsp -->

<!-- 내가 좋아요/찜한 목록 -->
  <div class="container text-center">	

	<div class="pagetitle"><!-- css에  -->
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> ${ title }한 작품들 </strong></span>
		<br><br>
	</div>
 	 
	<c:forEach var="dto" items="${ list }">
		<table class="table table-bordered" id="like_table" style="width:600px; margin:auto;">
		  <tr>
		  	<td rowspan="2" style="width:90px; margin:auto;">
		  		<a href="contlist/contlistread.do?mcode=${ dto.mcode }" >
					<img src="../../../storage/${dto.mthum}" alt="movie" width="80px">
				</a>
		  	</td>
		  	<td>
		  		<label></label>
		  		<a href="contlist/contlistread.do?mcode=${ dto.mcode }"><strong>${ dto.mtitle }</strong></a>
		  		&nbsp;⭐${ dto.mrate }
		  	</td>
		  </tr>
		  <tr>
		  	<td>
		  		바로 보러가기
		  		<c:if test="${ dto.netflix eq 'O' }" >
		  			<a href="contlist/contlistwatch.do?mcode=${ dto.mcode }" >
						<img src="../../images/icon_netflix.png" width="30px">
					</a>
				</c:if>
				
				<c:if test="${ dto.watcha eq 'O' }" >
		  			<a href="contlist/contlistwatch.do?mcode=${ dto.mcode }" >
						<img src="../../images/icon_watcha.png" width="30px">
					</a>
				</c:if>
				<c:if test="${ dto.tving eq 'O' }" >
		  			<a href="contlist/contlistwatch.do?mcode=${ dto.mcode }" >
						<img src="../../images/icon_tving.png" width="30px">
					</a>
				</c:if>
				<c:if test="${ dto.disney eq 'O' }" >
		  			<a href="contlist/contlistwatch.do?mcode=${ dto.mcode }" >
						<img src="../../images/icon_disney.png" width="30px">
					</a>
				</c:if>
		  	</td>		  	
		  </tr>
	    </table>
	</c:forEach>

  </div>
<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>