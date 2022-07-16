<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

	<!-- 메인카테고리 끝 -->

	<!-- 본문 시작 -->
<div id="cont_list" class="container-fluid text-center">

	<div class="pagetitle">
		<br>
		<span><strong> 👀 컨텐츠들을 구경해보세요! 👀 </strong></span>
		<h5>${ msg }</h5>
		<br>
	</div>
	
	
		<div class="ott_search">
			
			<button class="ott_search_btn" id="netflix" name="netflix"><img src="../../images/icon_netflix_search.png"></button>
			<button class="ott_search_btn" id="tving" name="tving"><img src="../../images/icon_tving_search.png"></button>
			<button class="ott_search_btn" id="watcha" name="watcha"><img src="../../images/icon_watcha_search.png"></button>
			<button class="ott_search_btn" id="disney" name="disney"><img src="../../images/icon_disney_search.png"></button>
			<p id="panel"></p>
		</div>
		
		<div class="contents">
		
					<c:forEach var="dto" items="${list}">
				<div class="col-sm-3">
					<div class="thumbnall">
					  
						<a href="<%=request.getContextPath()%>/contlist/contlistread.do?mcode=${ dto.mcode }">
							<img src="../../storage/${dto.mthum}" alt="movie"
							width="300px">
						</a>
						<div class="mtitle">${dto.mtitle}</div>
						<div class="mrate">평점 : ${dto.mrate}</div>
						
					
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
			</c:forEach>
		
		</div>	
		
    
</div>

	<script>
	
	var list = [];
	<c:forEach var="dto" items="${ list }">
		list.push("${ dto }");
	</c:forEach>
	
	var size=list.length;
	
	$(document).ready(function(){
		
		   $(".contents").append(list);
		   $(".contents").append(size);

	});
	
	
	
	$("#netflix").click(function(){
			
		//$(this).val("O");				
		
	    $.ajax({
            url:"ottsearch.do",  //요청명령어 
            type:"get",        //get방식
            datatype:"text", 
		
            success:function(result){//success callback함수
               alert("성공: " + result); //result : 서버가 응답해준 메시지               
               $(".contents").empty();
			   $(".contents").append(result);
               
            }
   		});//ajax() end
			
	});	
		
	</script>

	<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>