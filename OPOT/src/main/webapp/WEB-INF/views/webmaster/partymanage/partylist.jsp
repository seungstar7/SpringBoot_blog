<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<!-- 본문시작 partylist.jsp -->
	
	
	<!-- 관리자페이지 : 파티정보 읽어오기 -->
	
  <div class="container text-center">	
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 전체 파티 리스트 </strong></span>
		<br><br>
	</div>
		
	<table class="table">
		<tr>
			<th>파티아이디</th>
			<th>파티장</th>
			<th>구독OTT</th>
			<th>생성날짜</th>
			<th>매칭인원</th>
		</tr>

		<c:forEach var="dto" items="${list}"> 
			<tr>
				<td>${dto.party_id }</td>
				<td><a href="partyread.do?party_id=${dto.party_id }">${dto.mem_id }</a></td>				
				<td>${dto.ott_name }</td>
				<td>${dto.ott_cdate }</td>
				<td>${dto.matching_no }</td>
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan='5' style='text-align:center; height: 50px'>
				<form action="partylist.do">
					<select name="col">
						<option value="ott_name" id="ott_name" name="ott_name">구독OTT
						<option value="matching_no" name="matching_no" id="matching_no">매칭인원
					</select>
					<select id="word" name="word">

					</select>
					<input type="submit" value="검색" class="btn btn-danger">
				</form>
			</td>
		</tr>
		<tr>
			<td colspan='5' style='text-align:center; height: 50px;' >
			${paging}

			</td>
		</tr>	
		
	</table>

  </div>	

	<script>
	
	function searchParam(key) {
		  return new URLSearchParams(location.search).get(key);
	};
	
	var col=searchParam('col');
	var word=searchParam('word');

    function makeOptions(col){    	  
  	  if(col=="ott_name" || col==null){    		  
  		  $("#word").append("<option id='netflix' name='netflix' value='netflix'>netflix</option>");
  		  $("#word").append("<option id='tving' name='tving' value='tving'>tving</option>");
  		  $("#word").append("<option id='watcha' name='watcha' value='watcha'>watcha</option>");
  		  $("#word").append("<option id='disney' name='disney' value='disney'>disney</option>");
  		  
		  if(word=="netflix"){
			  $("#netflix").attr("selected", "selected");
		  }else if(word=="tving"){
			  $("#tving").attr("selected", "selected");
		  }else if(word=="watcha"){
			  $("#watcha").attr("selected", "selected");
		  }else if(word=="disney"){
			  $("#disney").attr("selected", "selected");
		  }			  
  		  
  	  }else if(col=="matching_no"){
  		  $("#word").append("<option id='m1' name='m1' value='1'>1명</option>");
  		  $("#word").append("<option id='m2' name='m2' value='2'>2명</option>");
  		  $("#word").append("<option id='m3' name='m3' value='3'>3명</option>");
  		  $("#word").append("<option id='m4' name='m4' value='4'>4명(매칭완료)</option>");
  		  		  
		  if(word=="1"){
			  $("#m1").attr("selected", "selected");
		  }else if(word=="2"){
			  $("#m2").attr("selected", "selected");
		  }else if(word=="3"){
			  $("#m3").attr("selected", "selected");
		  }else if(word=="4"){
			  $("#m4").attr("selected", "selected");
		  }
  	  }    	  
    }
	  
	  $(document).ready(function(){		  
		  if(col=="ott_name" || col==null){
			  $("#ott_name").attr("selected", "selected");			  
		  }else if(col=="matching_no"){
			  $("#matching_no").attr("selected", "selected");
		  }
		  makeOptions(col);		  
	  });
	
	  
      $("select[name=col]").change(function(){			
    	  $("#word").empty();
    	  col=$(this).val();
    	  makeOptions(col);
	  });
	
	</script>	



<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>