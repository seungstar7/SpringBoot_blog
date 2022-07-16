<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본문시작 qnaupdate.jsp -->
<!-- 스마트 에디터 넣기 !! -->	
<script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/ckeditor.js"></script>

<div class="container text-center">	
	
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 문의사항 작성 </strong></span>
		<br><br>
	</div>

	<form name="frm" id="frm" method="post" action="qnaupdateProc.do">
		<input type="hidden" id="qna_num" name="qna_num" value="${dto.qna_num}">		
		<table class="table" style="margin:auto;">
			<tr>
			   <th>제목</th>
			   <td colspan="2"><input type="text" name="qna_title" id="qna_title" class="form-control" size="20" value="${dto.qna_title}" placeholder="문의 제목을 적어주세요" required></td>
			</tr>		
			<tr>
			   <th colspan="3"></th>
			</tr>
		</table>
		<textarea name="qna_content" id="qna_content">${dto.qna_content}</textarea>
		<br>
		<input type="submit" value="수정"  class="btn btn-default">
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