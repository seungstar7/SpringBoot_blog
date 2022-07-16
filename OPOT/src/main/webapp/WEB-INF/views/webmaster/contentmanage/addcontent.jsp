<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>        
    
<%@ include file="../../header.jsp"%>

<!-- 본문시작 addcontent.jsp -->

<div class="container text-center">
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 컨텐츠 추가페이지 </strong></span>
		<br><br>
	</div>

<form name="contfrm" id="contfrm" method="post" action="contins.do" enctype="multipart/form-data" onsubmit="">
	<table class="table">
	<tr>
	    <th>컨텐츠제목</th>
	    <td colspan="4"><input type="text" name="mtitle" id="mtitle" class="form-control" size="50" maxlength="50" required autofocus></td>
	</tr>
	<tr>
	    <th>영문컨텐츠제목</th>
	    <td colspan="4"><input type="text" name="mtitle_eng" id="mtitle_eng" class="form-control" size="50" maxlength="50" autofocus></td>
	</tr>
	<tr>
		<th>서비스 중인 OTT</th>
	    <th>
	    	<label><input type="checkbox" name="netflix" id="netflix" value="O"> 넷플릭스 &nbsp;</label>
	    	<label><input type="checkbox" name="watcha" id="watcha" value="O"> 왓챠 &nbsp;</label>	
	    	<label><input type="checkbox" name="tving" id="tving" value="O"> 티빙 &nbsp;</label>
	    	<label><input type="checkbox" name="disney" id="disney" value="O"> 디즈니 &nbsp;</label>
	    </th>
	    
	    <th></th>
	    <th></th>
	    <th></th>
	</tr>
	<tr>
	    <th>개봉년도</th>
	    <td colspan="4">
	      <select name="mdate" id="mdate" class="form-control">
	      </select>
	    </td>
	</tr>
	<tr>
	    <th rowspan="2">키워드코드</th>
	    <td colspan="4">
	    	<c:forEach var="dto" items="${ list }" begin="0" end="${ fn:length(list) }">
	    	<label><input type="checkbox" class="key_word" name="key_word" id="key_word" value="${ dto.key_code }"> ${ dto.key_name } &nbsp;</label>
	    	</c:forEach>
	    </td>
	</tr>
	<tr>
	    <td colspan="4">
	    	<textarea class="form-control" name="key_code" id="key_code"></textarea>
	    </td>
	</tr>
	<tr>
	    <th>감독</th>
	    <td colspan="3" id="directors">
	      <input type="text" name="director1" id="director1" class="form-control" placeholder="감독1">
	    </td>
	    <td>
	    	<img src="../../images/plus_icon.png" id="director_plus" name="director_plus" width="20px">	      	    
	    	<img src="../../images/minus_icon.png" id="director_minus" name="director_minus" width="20px">	      	    
		</td>
	</tr>
	<tr>
	    <th>배우</th>
	    <td colspan="3" id="actors">
	      <input type="text" name="actor1" id="actor1" class="form-control" placeholder="배우1">
	    </td>
	    <td>
	      <img src="../../images/plus_icon.png" id="actor_plus" name="actor_plus" width="20px">      
	      <img src="../../images/minus_icon.png" id="actor_minus" name="actor_minus" width="20px">	      	    	           
	    </td>
	</tr>
	<tr>
	    <th>유튜브주소</th>
	    <td colspan="4">
	      <input type="text" name="maudio" id="maudio" class="form-control">
	    </td>
	</tr>
	<tr>
	    <th>영화포스터</th>
	    <td colspan="4">
	    	<input type="file" name="mthumMF" id="mthumMF" class="form-control">
	    </td>
	</tr>
	<tr>
	    <td colspan="5">
	    </td>
	</tr> 
	</table>
	<input type="hidden" name="i" id="i" value="1">
	<input type="hidden" name="j" id="j" value="1">
	<input type="submit" value="컨텐츠 등록" class="btn btn-danger">
	<input type="reset"  value="취소"       class="btn btn-danger	"><br><br>

</form>
</div>

    <script>
    
    	createYear();
    	
        function createYear(){

            var cYear=moment().year();  //현재년도 2022
            for(y=cYear; y>=cYear-70; y--){
                //3) 현재년도를 미리 selected
                if(cYear==y){
                    $("<option>").text(y).attr("selected", "selected").appendTo("#mdate");
                }else{
                    $("<option>").text(y).appendTo("#mdate");
                }//if end
            }//for end

        }//createYear() end

        
        $(".key_word").change(function(){
        	
        	var key_code=$(this).attr("value")+" || ";
        	
            if($(this).is(":checked")){
                //alert("체크박스 체크");
                //alert(key_code);
                $("#key_code").append(key_code); 
            	
            }else{
                //alert("체크박스 체크해제");
                //alert(key_code);
                $("#key_code").empty();
                $(".key_word").prop("checked", false);
            }
        });
        
        var i=1;
        var j=1;
        
        $("#director_plus").click(function(){
			
        	i++;        	
        	$("#directors").append("<input type='text' name='director"+i+"' id='director"+i+"' class='form-control' placeholder='감독"+i+"'>");
        	$("#i").attr('value', i);
           
        });      

        
        $("#actor_plus").click(function(){
			
        	j++;        	
        	$("#actors").append("<input type='text' name='actor"+j+"' id='actor"+j+"' class='form-control' placeholder='배우"+j+"'>");
        	$("#j").attr('value', j);

        });
        
		
        
        $("#director_minus").click(function(){			
        	
        	if(i>1){
	        	$("#director"+i).remove();
	        	i--; 
	        	$("#i").attr('value', i);
        	}else{
	        	$("#director"+i).attr('value', '');
        		alert("최소 1명 이상의 감독을 입력해주세요");
        	}
        });    
        
        $("#actor_minus").click(function(){			
        	if(j>1){
	        	$("#actor"+j).remove();
	        	j--; 
	        	$("#j").attr('value', j);
        	}else{
	        	$("#actor"+j).attr('value', '');
        		alert("최소 1명 이상의 배우를 입력해주세요");
        	}
        });    
     
    </script>

<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>