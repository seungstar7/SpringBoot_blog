<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 본문시작 noticeread.jsp -->
	
	
	<div class="container-fluid text-center">	
		<div class="container text-center">
		<table class="table" >
		<tr>
			<td colspan="2"><h1><strong>${dto.n_title}</strong></h1></td>
		</tr>
		<tr>
			<td>${dto.n_date}</td>
			<td style="text-align: right;"><strong>조회수</strong>${dto.n_readcnt}</td>			
		</tr>
		<tr height=500>
			<td colspan="2"><h3>${dto.n_content}</h3></td>
		</tr>
		<tr>
			<td style="text-align: center;" colspan="2">
				<input type="button" value="홈으로"  onclick="location.href='/home.do'" class="btn btn-default">
				<input type="button" value="공지목록" onclick="location.href='/notice/notice.do'" class="btn btn-default">				
			</td>
		</tr>
		</table>

		</div>
	</div>

	<c:if test="${ s_mem_lv=='A' }">
		<input type="button" value="공지사항삭제" onclick="location.href='/notice/noticedelelte.do?n_num=${dto.n_num}'" class="btn btn-default">	
		<input type="button" value="공지사항수정" onclick="location.href='/notice/noticeupdate.do?n_num=${dto.n_num}'" class="btn btn-default"><hr>	
	</c:if>



<!-- 본문끝 -->

<%@ include file="../footer.jsp"%>