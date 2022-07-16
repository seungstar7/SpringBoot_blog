<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
			     <input name="content" id="content" class="form-control" maxlength="30" placeholder="추천컨텐츠 검색: 콘텐츠제목을 입력해주세요">
			   </td>
			   <td>
					<input type="button" id="addcontent" class="btn btn-default" value="추가">   	    
			   </td>
			</tr>
			<tr>
			   <td id="suggest">
				  <input type="text" name="content1" id="content1" class="form-control" maxlength="30" placeholder="추천컨텐츠1" readonly required>				 
			   </td>
			   <td>
			    	<img src="../../images/plus_icon.png" id="content_plus" name="content_plus" width="20px">	      	    
			    	<img src="../../images/minus_icon.png" id="content_minus" name="content_minus" width="20px">	      	    
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
    
    
    $("#addcontent").click(function(){
		
		var cont = $('#content').val();
		//alert(cont);
        $("#content"+m).attr('value', cont);
        $('#content').val('');
		
    });  
    
    
    $("#content_plus").click(function(){
		
    	m++;        	
    	$("#suggest").append("<input type='text' name='content"+m+"' id='content"+m+"' class='form-control' readonly placeholder='추천컨텐츠"+m+"'>");
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

    
    
    $('#content').autocomplete({
        source : function(request, response) {
            $.ajax({
                  url : "moviesuggest.do"
                , type : "POST"
                , data : { keyword : $('#content').val() } // 검색 키워드
                , dataType : "JSON"
                , success : function(data){ // 성공
                    response(
						
                        $.map(data, function(item) {
                            return {
                                  label : item    //목록에 표시되는 값
                                , value : item    //선택 시 input창에 표시되는 값
                            };
                        })
                    );//response
                }
                ,
                error : function(){ //실패	
                    alert("통신 실패");
                }
            });
        }
        , minLength : 1    
        , autoFocus : false    
        , select : function(event, ui) {
            console.log(ui);//사용자가 오토컴플릿이 만들어준 목록에서 선택을 하면 반환되는 객체
            console.log(ui.item.label);
            console.log(ui.item.value);
        }
        , focus : function(event, ui) {
            return false;
        }
        , close : function(event) {
            console.log(event);
        }
    });


</script>


</div>

<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>