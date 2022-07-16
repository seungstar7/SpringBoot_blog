<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>


<div class="container text-center">	
	
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 비밀번호 확인 </strong></span>
		<h5>${ msg } 위해 비밀번호를 확인합니다</h5>
		<br><br>
	</div>
	
	<c:if test="${ msg eq '답글 삭제를' }">
	<form name="frm" id="frm" method="post" action="qnadelete.do?qna_num=${ qna_num }">
		<input type="hidden" id="qna_num" name="qna_num" value="${ qna_num }">
		<table class="table" style="margin:auto; width:500px;">
			<tr>
			   <th>비밀번호</th>
			   <td colspan="2"><input type="password" name="qna_pw" id="qna_pw" class="form-control" size="20" placeholder="글 작성시 입력한 비밀번호를 적어주세요" required></td>
			</tr>	
		</table>
		<br>
		<input type="submit" value="확인"  class="btn btn-default">
		<input type="button" value="이전"  class="btn btn-default" onclick="history.back();">		
	</form>
	</c:if>
	
	<c:if test="${ msg eq '답글 수정을' }">
	<form name="frm" id="frm" method="post" action="qnaupdate.do?qna_num=${ qna_num }">
		<input type="hidden" id="qna_num" name="qna_num" value="${ qna_num }">		
		<table class="table" style="margin:auto; width:500px;">
			<tr>
			   <th>비밀번호</th>
			   <td colspan="2"><input type="password" name="qna_pw" id="qna_pw" class="form-control" size="20" placeholder="글 작성시 입력한 비밀번호를 적어주세요" required></td>
			</tr>	
		</table>
		<br>
		<input type="submit" value="수정"  class="btn btn-default">
		<input type="button" value="이전"  class="btn btn-default" onclick="history.back();">		
	</form>
	</c:if>

</div>

<script> 

	ClassicEditor 
	.create( document.querySelector( '#qna_content' ),{
		language: 'ko',
		removePlugins: [ 'MediaEmbed', 'ImageUpload', 'EasyImage' ],		
	} )
	.then( newEditor => {
		editor = newEditor;
	} )
	.catch( error => { console.error( error ); } 
	); 
	
</script>


<!-- 본문끝 -->

<%@ include file="../footer.jsp"%>