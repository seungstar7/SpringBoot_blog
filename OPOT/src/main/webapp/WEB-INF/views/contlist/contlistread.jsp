<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!-- 메인카테고리 끝 -->
<script type="text/javascript" src="../js/contentcri_action.js"></script>


<!-- 본문 시작 -->
<div id="cont_list" class="container-fluid text-center">
	<div class="pagetitle">
		<br>
		<span><strong> 🎥 컨텐츠 상세정보 </strong></span>
		<h5>포스터나 OTT아이콘을 누르면 영상보기로 이동합니다</h5>
		<br>
	</div>
	
	<div class="mtitle"><h1><strong>${dto.mtitle}</strong></h1></div>
	<div class="key_code">
		
			<c:forEach var="dto" items="${ keylist }">
				<a href="keysearch.do?key_name=${ dto.key_name }&key_code=${ dto.key_code }"> 
					&#35; ${ dto.key_name }
				</a>
			</c:forEach>
	</div><br>

	<form name="frm" id="frm" method="post" 
		action="<%=request.getContextPath()%>/contlist/contlistwatch.do?mcode=${ dto.mcode }" onsubmit="return IDlog(${mem_lv})">
		<input type="image" src="../../storage/${dto.mthum}" alt="movie" width="300px"><br>
		<c:if test="${dto.netflix eq 'O' }">
			<input type="image" src="../../images/icon_netflix.png" width="50px">	
		</c:if>
		<c:if test="${dto.tving eq 'O'  }">
			<input type="image" src="../../images/icon_tving.png" width="50px">
		</c:if>
		<c:if test="${dto.watcha eq 'O'  }">
			<input type="image" src="../../images/icon_watcha.png" width="50px">
		</c:if>
		<c:if test="${dto.disney eq 'O'  }">
			<input type="image" src="../../images/icon_disney.png" width="50px">
		</c:if>
	</form>

	<div class="mdate"><h4>${dto.mdate}년 작품</h4></div>
	<div class="mrate">컨텐츠 평점 : ${dto.mrate}</div>
	<div class="like">좋아요 수 : ${dto.cri_like}</div>
	<hr> 
	<!-- 사용자 컨텐츠 평가 -->
	<div class="container" align="center" id="cri_panel">
	   		
	   <form name="crifrm" id="crifrm" method="post">	
		<c:choose>
		<c:when test="${ s_mem_id==null || s_mem_pw==null || s_mem_lv==null }">
			<a href="<%=request.getContextPath()%>/login.do">로그인</a>
		</c:when>
		<c:otherwise>
			<div class="btn-group">
				<button class="btn btn-danger btn-lg" type="button" id="likeBtn" onclick="likeCheck()">😍 좋아요</button>
				<button class="btn btn-danger btn-lg" type="button" id="pointBtn" onclick="pointCheck()">♥ 찜하기</button>
		 	</div>
		</c:otherwise>
		</c:choose>
		</form>
	</div>		
	
	<!-- 사용자 컨텐츠 평가 끝 -->
	
	<!-- 감독 배우 목록 -->	
	<div id="people" class="container-fluid text-center">
	  <h4><strong>감독</strong></h4>		
		<c:forEach var="dto" items="${ directorlist }">
			  <span class="directors">
			   <c:choose>
			    <c:when test="${ dto.pphoto==null || dto.pphoto eq '' }">
			      <a href="<%=request.getContextPath()%>/peoplesearch.do?pno=${ dto.pno }&pname=${ dto.pname }">
			      	<img src="../../storage/people_None.jpg" class="img-circle" alt="director" width="50" height="50">
			       </a>			      
			    </c:when>
			    <c:otherwise>
			      <a href="<%=request.getContextPath()%>/peoplesearch.do?pno=${ dto.pno }&pname=${ dto.pname }">
			         <img src="../../storage/${ dto.pphoto }" class="img-circle" alt="director" width="50" height="50">
			      </a>
			    </c:otherwise>
			   </c:choose>
			    
			  </span>
			  <span>${ dto.pname }</span>
		</c:forEach>
		<hr>
		
	    <h4><strong>출연</strong></h4>		
		<c:forEach var="dto" items="${ actorlist }">
			  
			  <span class="actors">
			  <c:choose>
			    <c:when test="${ dto.pphoto==null || dto.pphoto eq '' }">
				  <a href="<%=request.getContextPath()%>/peoplesearch.do?pno=${ dto.pno }&pname=${ dto.pname }">
			      	<img src="../../storage/people_None.jpg" class="img-circle" alt="actor" width="50" height="50">
			      </a>			      
			    </c:when>
			    <c:otherwise>
			      <a href="<%=request.getContextPath()%>/peoplesearch.do?pno=${ dto.pno }&pname=${ dto.pname }">
			          <img src="../../storage/${ dto.pphoto }" class="img-circle" alt="actor" width="50" height="50">
			      </a>
			    </c:otherwise>
			   </c:choose> 
			    </span>			  
			  <span>${ dto.pname }</span>
		</c:forEach>

	</div>
	<!-- 감독 배우 목록 끝 -->

	<!-- 리뷰 관련 -->

	<div id="reviews">		
	   <table id="reviews_tb" class="table" style="width:500px; margin:auto;">
	   <c:forEach var="dto" items="${ reviewlist }">
	   		<tr class="info-wrap" id="info-wrap${ no }">
	   			<td>작성자 : ${ dto.mem_id }</td>
	   			<td style="text-align:right;">${ dto.rev_reg }</td>
	   		</tr>
	   		<tr class="title-wrap" id="title-wrap${ no }">
	   			<th>${ dto.rev_title }</th>
	   			<td style="text-align:right;">⭐${ dto.rev_rate }</td>
	   		</tr>
			<tr class="review-content" id="review-content${ no }">
				<td colspan="2">${ dto.rev_cont }</td>
			</tr>
			<tr class="review-btns" id="review-btns${ no }">
				<td colspan="2" style="text-align:right;" id="review-btn${ no }">
					<button class="btn btn-default btn-sm" onclick="location.href='reviewupdate.do?mcode=${ dto.mcode }&rev_code=${ dto.rev_code }'">수정</button>
					<button class="btn btn-default btn-sm" onclick="location.href='reviewdelete.do?mcode=${ dto.mcode }&rev_code=${ dto.rev_code }'">삭제</button>
		   		</td>
	   		</tr>
	   		<tr><td colspan="2"></td></tr>
	   </c:forEach>
	   </table>
	</div>
   
   <br>
   <c:choose>
		<c:when test="${ s_mem_lv=='B' || s_mem_lv=='A' }">
			<button class="btn btn btn-default btn-lg" onclick="location.href='<%=request.getContextPath()%>/contlist/reviewForm.do?mcode=${ dto.mcode }'">
			    리뷰작성하기
			</button>
		</c:when>
		<c:otherwise>
			<h4>리뷰는 로그인 후 작성가능합니다</h4>
		</c:otherwise>
   </c:choose>
   <c:if test="${ fn:length(reviewlist)!=0 }">
       <button class="btn btn-default btn-lg" onclick="location.href='<%=request.getContextPath()%>/contlist/reviewList.do?mcode=${ dto.mcode }'">
            리뷰 더 보기
       </button>
   </c:if>


</div>

	<script>
	function IDlog(mem_lv) {
		if(mem_lv==null || mem_lv=="F"){
			alert("로그인 후 시청 가능한 컨텐츠 입니다");
			return false;
		}
		return true;
	}//IDlog() end

	</script>

<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>