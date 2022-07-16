<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<!-- 본문시작 recList.jsp -->
	
	
<!-- 관리자페이지 : 테마별 추천글 리스트 -->
	
  <div class="container text-center">	
	
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 추천글 리스트 </strong></span>
		<h4>${ dto.t_title }</h4>
	</div>

	<br>
	<c:if test="${ s_mem_lv=='A' }">	
		<input type="button" value="테마추가" onclick="location.href='/themeadd.do'" class="btn btn-danger">
		<input type="button" value="추천글추가" onclick="location.href='/recadd.do?t_num=${ dto.t_num }'" class="btn btn-danger"><br><br>
	</c:if>
	<div class="row text-center">
		<c:forEach var="dto" items="${ list }">


			    <div class="col-sm-3">
			      
			      <a href="/themeread.do?r_num=${ dto.r_num }">
			      	<img src="../../storage/${ dto.r_photo }" alt="theme" class="img-responsive margin" style="width:100%">
			      </a>
			      <p>${ dto.r_title }</p>

			    </div>

		</c:forEach>
    </div>

	<button class="btn btn-danger" onclick="location.href='themelist.do'">테마목록</button>


  </div>	


<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>