<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본문시작 qnaread.jsp -->
	
	
	<div class="container-fluid text-center">	
		<div class="container text-center">	
		<table class="table">
		<tr>
			<td colspan="3"><h1>${dto.qna_title}</h1></td>
		</tr>
		<tr>
			<th>${dto.mem_id}</th>
			<td style="text-align: right;">${dto.qna_date}&nbsp;&nbsp;&nbsp;<strong>조회수</strong>${dto.qna_readcnt}</td>
			
		</tr>
		<tr height=500>
			<td colspan="2"><h3>${dto.qna_content}</h3></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;">
				<input type="button" value="홈으로"  onclick="location.href='/home.do'" class="btn btn-default">
				<input type="button" value="Q&A목록" onclick="location.href='/qna/qna.do'" class="btn btn-default">			
			</td>
		</tr>
		</table>

		</div>

       <c:choose>
	       <c:when test="${ s_mem_lv=='A' || s_mem_id==dto.mem_id }">
	           <input type="button" value="삭제"  class="btn btn-danger" onclick="deleteLog()">
	           <input type="button" value="수정"  class="btn btn-danger" onclick="updateLog()">
	           <input type="button" value="답글"  class="btn btn-danger" onclick="replyLog()">
	       </c:when>    
	   </c:choose>

	</div>
		
	<script>
	function deleteLog() {

		var message="해당 글을 삭제합니다.\n삭제된 내용은 복구되지 않습니다.\n계속 진행할까요?";	    		
		
		if("${ s_mem_lv }"=='A' || "${ s_mem_id }"=="${ dto.mem_id }"){
			if(confirm(message)){				
				location.href="qnadelete.do?qna_num=${dto.qna_num}";				
		    }
		}else{
			alert("작성자만 삭제 가능합니다");
		}
		
	}//deleteLog() end
	
	
	function updateLog() {

		var message="해당 글을 수정합니다.\n계속 진행할까요?";	    		
		
		if("${ s_mem_lv }"=='A' || "${ s_mem_id }"=="${ dto.mem_id }"){
			if(confirm(message)){				
				location.href="qnaupdate.do?qna_num=${dto.qna_num}";				
		    }
		}else{
			alert("작성자만 수정 가능합니다");
		}
		
	}//updateLog() end

	
	
	function replyLog() {

		var message="답글을 작성합니다.\n계속 진행할까요?";	    		
		
		if("${ s_mem_lv }"=='A' || "${ s_mem_id }"=="${ dto.mem_id }"){
			if(confirm(message)){				
				location.href="qnareply.do?qna_num=${dto.qna_num}&qna_grpno=${dto.qna_grpno}&qna_indent=${dto.qna_indent}&qna_ansnum=${dto.qna_ansnum}";				
		    }
		}else{
			alert("작성자와 관리자만 답글 작성이 가능합니다");
		}
		
	}//replyLog() end
	

	</script>
		
	




<!-- 본문끝 -->

<%@ include file="../footer.jsp"%>