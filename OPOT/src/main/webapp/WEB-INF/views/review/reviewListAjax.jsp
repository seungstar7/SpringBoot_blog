<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 메인카테고리 끝 -->

<div class="container">
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 리뷰 더보기 </strong></span>
		<br><br>
	</div>

	<div id="reviews">
	   <c:set var="no" value="1"></c:set>
		
	   <table id="reviews_tb" class="table" style="width:500px; margin:auto;">
	   <c:forEach var="dto" items="${ list }">
	   		<tr class="info-wrap" id="info-wrap${ no }">
	   			<td>작성자 : ${ dto.mem_id }</td>
	   			<td style="text-align:right;">${ dto.rev_reg }</td>
	   		</tr>
	   		<tr class="title-wrap" id="title-wrap${ no }">
	   			<th>${ dto.rev_title }</th>
	   			<td style="text-align:right;">⭐${dto.rev_rate}</td>
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
	   		<span class="hide">${ no=no+1 }</span>	   			
	   </c:forEach>
	   </table>
	</div>
	<input type="hidden" id="no" value="4">
	
</div>

<script>

var loading = false;    //중복실행여부 확인 변수
var page = 1;   //불러올 페이지

$(window).scroll(function(){  
    if($(document).height() <= $(window).scrollTop() + $(window).height()){    
       
       page++;               
       loadNext();  
    }
 });


/*nextpageload function*/
function loadNext(){
	var num=parseInt($("#no").val());		

        $.ajax({
                type:"post",
                url:"morereviews.do",
                data : {page : page, 
                         mcode :searchParam('mcode')
                        },
                //dataType : "text",
                success: function(data){
                    
                   $.each(data,function(index, value) {
                        $('#reviews_tb').append('<tr class="info-wrap" id="info-wrap'+num+'">');
                        $('#reviews_tb').append('<tr class="title-wrap" id="title-wrap'+num+'">');
                        $('#reviews_tb').append('<tr class="review-content" id="review-content'+num+'">');
                        $('#reviews_tb').append('<tr class="review-btns" id="review-btns'+num+'">');
                        $('#reviews_tb').append('<tr><td colspan="2"></td></tr>');

                	   
                        $('#info-wrap'+num).append("<tr><td>작성자 : "+value.mem_id+"</td>");
                        $('#info-wrap'+num).append("<td style='text-align:right;'>"+value.rev_reg+"</td>");
                        
                        $('#title-wrap'+num).append("<th>"+value.rev_title+"</th>");
                        $('#title-wrap'+num).append("<td style='text-align:right;'>⭐"+value.rev_rate+"</td>");

                        $('#review-content'+num).append("<td colspan='2'>"+value.rev_cont+"</td>");

                        $('#review-btns'+num).append("<td colspan='2' style='text-align:right;' id='review-btn"+num+"'>");                        
                        $('#review-btn'+num).append("<button class='btn btn-default btn-sm' onclick='href=\"reviewupdate.do?mcode="+value.mcode+"&rev_code="+value.rev_code+"\"'>수정</button>");                        
                        $('#review-btn'+num).append("<button class='btn btn-default btn-sm' onclick='href=\"reviewdelete.do?mcode="+value.mcode+"&rev_code="+value.rev_code+"\"'>삭제</button>");                        
						
                        num++;
                   })
                   $('#no').attr('value', num);	
                }
                ,error: function(error) 
                {
                    alert(error);
                }
            });
}


function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
};
   

   
   </script>
   
   
   
   






<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>