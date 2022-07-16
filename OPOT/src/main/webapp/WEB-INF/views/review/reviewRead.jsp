<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 메인카테고리 끝 -->


<!-- 본문 시작 -->
<h1>이 작품, 어떠셨나요?</h1>
<br>
<br>

<div id="review_insert" class="container-fluid text-center">
	<form action="contlist/contlistread.do?mcode=${ mcode }" method="post">
		<input type="hidden" id="mcode" name="mcode" value="${ mcode }">
		<section class="section-area" style="padding: 0 16px 16px;">
			<div class="input-wrap">
				<h5 class="section-title">언제 감상하셨나요?</h5>
				<div class="date-wrap">
					<input type="tel" placeholder="입력형태: 20200701" id="rev_reg" value="${rev_reg}"
						class="primary-dark-input">
				</div>
			</div>
		</section>

		<section class="section-area" style="padding: 0 50px 50px;">
			<h5 class="section-title" style="width: 70%; display: inline-block;">간단히
				기록하기</h5>
			<div class="input-wrap">
				<div class="date-wrap">
					<textarea placeholder="작품에 대한 감상을 짧게 남겨주세요. 작성한 글이 제목으로 보여집니다."
						class="primary-dark-input contents" style="width: 70%;" id="rev_cont" name="rev_cont"></textarea>
				</div>
			</div>

			<div class="checkbox-wrap">
				<span class="checkbox-item">
					<span class="rev_spo_check"> 
						<input type="checkbox" id="rev_spo" class="rev_spo" value="${rev_spo}">
					</span> 
					<span class="checkbox-text">
						<label for="check-spoiler">작성한 내용이 스포일러를 포함하고 있습니까?</label>
					</span>
				</span>
			</div>
			
		</section>
		
		<footer class="rating-action-bar">
			
		<button class="primary-btn" 
					  onclick="location.href='<%=request.getContextPath()%>/contlistread.do?mcode=${dto.mcode}'">
		            	완료
		</button>

		</footer>
		
	</form>
</div>

<script>
$(document).ready(function(){
    
    $.ajax({
         url:"keycodes.do",
         type:"get",
        data : {
         mcode : searchParam('mcode'),            
        },      
         success:function(data){


            $.each(data,function(index, value) {
               //alert(value);
                 $('input[value*='+value+']').text(y).attr("checked", "checked");
            
            })
         },
         error:function(error){
         alert("에러: " + error);
      }
    });
 });
		

</script>






<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>