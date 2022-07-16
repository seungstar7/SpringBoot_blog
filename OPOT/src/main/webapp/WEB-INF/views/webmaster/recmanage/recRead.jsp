<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<!-- 본문시작 recRead.jsp -->
<!-- 테마별 추천글 상세보기 -->
	
  <div class="container text-center">	
	
	
	<div class="pagetitle">
		<br>
		<span><strong> ${ dto.r_title } </strong></span>
	</div>
	
	<div class="row text-center">
		
		${ dto.r_content }<hr>		
		
    </div>

	<!-- 테마 추천 콘텐츠들 -->
	<div class="row text-center" id="contents">

	<c:set var="no" value="1"></c:set>
		<c:forEach var="dto" items="${list}">
			<div class="col-md-4 col-sm-6">
				<div class="thumb" id="content${ no }">			  
				<a href="<%=request.getContextPath()%>/contlist/contlistread.do?mcode=${ dto.mcode }">
					<img src="../../storage/${dto.mthum}" alt="movie"
					width="300px">
				</a>
				<div class="mtitle"><strong>${dto.mtitle}</strong></div>
				<div class='stars'>
		        	<c:forEach begin="1" end="${ dto.mrate }">★</c:forEach><c:forEach begin="${ dto.mrate+1 }" end="5">☆</c:forEach>
		        	${ dto.mrate }
		        </div>
				<c:if test="${dto.netflix eq 'O' }" >
					<img src="../../images/icon_netflix.png" width="50px">
				</c:if>
				<c:if test="${dto.tving eq 'O'  }" >
					<img src="../../images/icon_tving.png" width="50px">
				</c:if>
				<c:if test="${dto.watcha eq 'O'  }" >
					<img src="../../images/icon_watcha.png" width="50px">
				</c:if>
				<c:if test="${dto.disney eq 'O'  }" >
					<img src="../../images/icon_disney.png" width="50px">
				</c:if>
			</div>
		</div>
		<span class="hide">${ no=no+1 }</span>
		</c:forEach>		
    </div>
	<br>
	<button class="btn btn-danger" onclick="location.href='themelist.do'">테마목록</button>
    <button class="btn btn-danger" onclick="location.href='theme.do?theme=${ dto.t_num }'">추천글목록</button>


	<!-- 수정 삭제 버튼 -->	
	<c:if test="${ s_mem_lv=='A' }">
		<hr>	
		<input type="button" value="삭제" class="btn btn-default"  onclick="deleteCheck()">
		<input type="button" value="수정" onclick="location.href='/recupdate.do?t_num=${ dto.t_num }&r_num=${ dto.r_num }'" class="btn btn-default"><br><br>
	</c:if>


  </div>
  
  <script>
  
  	function deleteCheck(){  		
  		var result = confirm("관련 파일도 모두 삭제 됩니다.\n정말로 삭제하시겠습니까?"); 		
  		if(result){
  			location.replace('/recdelete.do?t_num=${ dto.t_num }&r_num=${ dto.r_num }');
  		}		
  	}//deleteCheck() end
  	
  	
  	
  	
  	
  
  </script>
  	


<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>