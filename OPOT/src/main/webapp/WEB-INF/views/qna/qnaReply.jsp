<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/ckeditor.js"></script>

<!-- 본문시작 qnaform.jsp -->

<div class="container text-center">	
	
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 문의사항 답변 작성 </strong></span>
		<br><br>
	</div>
		<table class="table" style="margin:auto;">
			<tr>
			   <th>원글 제목</th>
			   <td>${ dto.qna_title }</td>
			</tr>
			<tr>
			   <th>원글 내용</th>
			   <td>${ dto.qna_content }</td>
			</tr>
		</table>	
	
	<form name="frm" id="frm" method="post" action="replyProc.do">
		<input type="hidden" id="qna_num" name="qna_num" value="${ qna_num }">		
		<input type="hidden" id="qna_grpno" name="qna_grpno" value="${ qna_grpno }">		
		<input type="hidden" id="qna_indent" name="qna_indent" value="${ qna_indent }">		
		<input type="hidden" id="qna_ansnum" name="qna_ansnum" value="${ qna_ansnum }">			
		<table class="table" style="margin:auto;">
			<tr>
			   <th>제목</th>
			   <td colspan="2">
			     <input type="text" name="qna_title" id="qna_title" class="form-control" size="20" value="re: ${ dto.qna_title }" readonly>
			   </td>
			</tr>
			<tr>
			   <th>비밀번호</th>
			   <td colspan="2"><input type="password" name="qna_pw" id="qna_pw" class="form-control" size="20" placeholder="비밀번호는 답변 수정 삭제 필요합니다" required></td>
			</tr>	
			<tr>
			   <th colspan="3"></th>
			</tr>
		</table>
		<textarea name="qna_content" id="qna_content"></textarea>
		<br>
		<input type="submit" value="답변등록"  class="btn btn-default">
		<input type="reset"  value="취소"  class="btn btn-default">		
	</form>
	

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