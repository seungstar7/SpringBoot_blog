<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<!-- 본문시작 themeList.jsp -->
	
	
<!-- 관리자페이지 : 추천 테마 리스트 -->
	
  <div class="container text-center">	
	
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 추천 테마 리스트 </strong></span>
		<br>
	</div>
	<br><br>
	<c:if test="${ s_mem_lv=='A' }">
	<input type="button" value="테마추가" onclick="location.href='/themeadd.do'" class="btn btn-danger"><hr>	
	</c:if>
	
	<div class="row text-center">
		<c:forEach var="dto" items="${ list }">
	
	      <div class="col-sm-4">
	        <div class="thumbnail">
	          <a href='theme.do?theme=${ dto.t_num }'>
	          <img src="../../storage/${ dto.t_photo }" alt="themes" width="400" height="300">
	          </a>
	          <p><strong>${ dto.t_title }</strong></p>
	          <button class="btn" onclick="location.href='theme.do?theme=${ dto.t_num }'">보러 가기</button>
	        </div>
	      </div>
		</c:forEach>
    </div>
		


  </div>	
	



<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>