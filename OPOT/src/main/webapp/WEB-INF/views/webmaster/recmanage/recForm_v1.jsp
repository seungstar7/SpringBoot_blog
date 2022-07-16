<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<script type="text/javascript" src="../../js/search_suggest.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/ckeditor.js"></script>

<!-- 본문시작 recForm.jsp -->
	
	
<!-- 관리자페이지 : 추천 리스트 작성페이지 -->
	
<div class="container text-center">	
	
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 추천리스트 작성 </strong></span>
		<br><br>
	</div>
	
	<button class="btn btn-danger" onclick="location.href='<%=request.getContextPath()%>/themelist.do'">테마목록</button><br><br>

	<div id="recfrmarea">
	
		<form name="recfrm" id="recfrm" method="post" action="recins.do" enctype="multipart/form-data" onsubmit="return recCheck()"> <!-- myscript.js에 함수 작성함 -->
		<table class="table" style="margin:auto;">
			<tr>
			   <th>제목</th>
			   <td colspan="2"><input type="text" name="r_title" id="r_title" class="form-control" maxlength="30" required></td>
			</tr>
			<tr>
			   <th rowspan="2">추천콘텐츠</th>
			   <td id="recContents">
			     <input type="text" name="content1" id="content1" class="form-control" maxlength="30" placeholder="추천컨텐츠1: 콘텐츠제목을 입력해주세요" onkeydown="search(this.value)" required>
			   </td>
			   <td>
			    	<img src="../../images/plus_icon.png" id="content_plus" name="director_plus" width="20px">	      	    
			    	<img src="../../images/minus_icon.png" id="content_minus" name="director_minus" width="20px">	      	    
			   </td>
			</tr>
			<tr>
			   <td colspan="2" id="suggest">

			   </td>
			</tr>
			<tr>
			   <th>대표사진</th>
			   <td colspan="2"><input type="file" name="r_photoMF" id="r_photoMF" class="form-control" required></td>
			</tr>			
			<tr>
			   <th colspan="3"></th>
			</tr>
		</table> 
		
		<textarea name="r_content" id="r_content"></textarea>
		<input type="hidden" name="m" id="m" value="1">
		<input type="hidden" name="t_num" id="t_num" value="${ t_num }">
		
		<br>
		<div id="btnArea">
			<input type="submit" value="쓰기" class="btn btn-default">
		    <input type="reset"  value="취소" class="btn btn-default">
		</div>

		</form>
		
	</div>	

<script> 

	ClassicEditor 
	.create( document.querySelector( '#r_content' ),{
		language: 'ko',
		removePlugins: [ 'MediaEmbed', 'ImageUpload', 'EasyImage' ],		
	} )
	.then( newEditor => {
		editor = newEditor;
	} )
	.catch( error => { console.error( error ); } 
	); 
	
	
    var m=1;
    
    $("#content_plus").click(function(){
		
    	m++;        	
    	$("#recContents").append("<input type='text' name='content"+m+"' id='content"+m+"' class='form-control' onkeydown='search(this.value)' placeholder='추천컨텐츠"+m+": 콘텐츠제목을 입력해주세요'>");
    	$("#m").attr('value', m);
       
    });      

    
    $("#content_minus").click(function(){			
    	
    	if(m>1){
        	$("#content"+m).remove();
        	m--; 
        	$("#m").attr('value', m);
    	}else{
        	$("#content"+m).attr('value', '');
    		alert("최소 1개 이상의 추천 콘텐츠를 입력해주세요");
    	}
    });   



</script>


</div>

<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>