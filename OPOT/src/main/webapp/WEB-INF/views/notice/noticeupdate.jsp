<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<script src="https://cdn.ckeditor.com/ckeditor5/34.2.0/classic/ckeditor.js"></script>

<!-- 본문시작 noticeupdate.jsp -->

<div class="container text-center">	
	
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 공지사항 수정 </strong></span>
		<br><br>
	</div>

	<form name="frm" id="frm" method="post" action="noticeupdateproc.do?n_num=${n_num}">
		<input type="hidden" id="n_num" name="n_num" value="${n_num}">
		<table class="table" style="margin:auto;">
			<tr>
			   <th>제목</th>
			   <td colspan="2"><input type="text" name="n_title" id="n_title" class="form-control" value="${ dto.n_title }" required></td>
			</tr>	
			<tr>
			   <th colspan="3"></th>
			</tr>
		</table>
		<textarea name="n_content" id="n_content">${ dto.n_content }</textarea>
		<br>
		<input type="submit" value="수정"  class="btn btn-default">
		<input type="reset"  value="취소"  class="btn btn-default">		
	</form>
	

</div>

<script> 

	ClassicEditor 
	.create( document.querySelector( '#n_content' ),{
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