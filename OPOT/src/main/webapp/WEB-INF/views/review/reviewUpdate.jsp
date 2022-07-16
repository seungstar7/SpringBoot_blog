<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본문 시작 -->
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 이 작품, 어떠셨나요? </strong></span>
		<br>
	</div>

<div class="container-fluid text-center" style="display: inline-block;"
	align="left">
	<form name="frm" id="frm" method="post" action="<%=request.getContextPath()%>/reviewupdateproc.do">
		<input type="hidden" id="mcode" class="form-control" name="mcode" value="${ dto.mcode }">
		<input type="hidden" id="rev_code" class="form-control" name="rev_code" value="${ dto.rev_code }">

			<table class="table">
				<tr>
					<td>
						<div>
							<div>
								<strong>평점</strong>
							</div>
							<input type="radio" id="rev_rate1" name="rev_rate" value="1"> 1점 &nbsp;&nbsp;
							<input type="radio" id="rev_rate2" name="rev_rate" value="2"> 2점 &nbsp;&nbsp;
							<input type="radio" id="rev_rate3" name="rev_rate" value="3"> 3점 &nbsp;&nbsp;
							<input type="radio" id="rev_rate4" name="rev_rate" value="4"> 4점 &nbsp;&nbsp;
							<input type="radio" id="rev_rate5" name="rev_rate" value="5"> 5점 &nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<h4 align="left">언제 감상하셨나요?</h4> 
						<input name="rev_reg" value="${ dto.rev_reg }"
						id="rev_reg" type="tel" class="form-control" 
						placeholder="입력형태: 20200701" required>
					</td>
				</tr>
				<tr>
					<td>
						<input name="rev_title" id="rev_title" type="text"
						class="form-control" placeholder="제목을 써주세요" value="${dto.rev_title }"
						size="20" placeholder="후기제목" required>
					</td>
				</tr>
				<tr>
					<td>					
					<textarea class="form-control" id="rev_cont" name="rev_cont" placeholder="작품에 대한 감상을 짧게 남겨주세요. 작성한 글이 제목으로 보여집니다." rows="10" required>${dto.rev_cont }</textarea>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;">
					<input type="submit" value="수정"  class="btn btn-default" >
					<input type="button"  value="이전"  class="btn btn-default" onclick="history.back()">
					</td>
				</tr>
			</table>

	</form>


</div>	



	<script>
	function IDlog(mem_lv) {
		if(mem_id == s_mem_id){
			alert("수정했습니다.")
			return true;
		} (mem_id != s_mem_id)
		alert("다른 사람의 리뷰는 수정 할 수 없습니다.")
		return false;
	}//IDlog() end
	
    $(document).ready(function(){
    	
    	for(i=1; i<=5; i++){
    			
    		var rev_rate=$('#rev_rate'+i).val();
    		
    		if('${ dto.rev_rate }'==rev_rate){
    			$('#rev_rate'+i).attr("checked", "checked");
    		}
    		
    	}

    });
	
	
	
	</script>


<!-- 본문끝 -->

<%@ include file="../footer.jsp"%>