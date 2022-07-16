<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  

<!-- 본문시작 qnalist.jsp -->
  	<div class="container text-center">
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 문의사항 </strong></span>
		<br>
	</div>	


		<div class="container-fluid text-center">
		<table class="table">
		<tr>
			<th>글제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>등록일</th>
			
		</tr>
		<c:forEach var="dto" items="${list}">
			<tr>
				<td>
					<c:forEach begin="1" end="${ dto.qna_indent }">
						<c:set var="i" value="1"></c:set>
						<c:if test="${ i==dto.qna_indent }"><img src='../../images/reply.gif'></c:if>
						<span class="hide">${ i=i+1 }</span>						
					</c:forEach>
					<a href="../qna/qnaread.do?qna_num=${dto.qna_num}">${dto.qna_title}</a>
				</td>
				<td>${dto.mem_id}</td>
				<td>${dto.qna_readcnt}</td>
				<td>${dto.qna_date}</td>
			</tr>
		</c:forEach>
			<tr>
				<td colspan='8' style='text-align:center; height: 50px'>
					<form action="qna.do">
						<select name="col">
							<option value="qna_title_qna_content">제목+내용
							<option value="qna_title">제목
							<option value="qna_content">내용
							<option value="mem_id">작성자
						</select>
						<input type="text" name="word" id="word">
						<input type="submit" value="검색" class="btn btn-danger">
					</form>
				</td>
			</tr>
			<tr>
				<td colspan='8' style='text-align:center; height: 50px;' >
				${paging}
	
				</td>
			</tr>
		
		</table>
		</div>
		<form name="frm" id="frm" method="post" action="qnaForm.do" onsubmit="return IDlog(${mem_lv})">
            <input type="submit" value="문의사항등록"  class="btn btn-default">
        </form>
	</div>
		
	<script>
	function IDlog(mem_lv) {
		if(mem_lv==null || mem_lv=="F"){
			alert("로그인 후 작성가능합니다")
			return false;
		}
		return true;
	}//IDlog() end

	</script>

<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>