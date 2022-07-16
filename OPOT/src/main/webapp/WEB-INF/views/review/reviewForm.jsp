<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 메인카테고리 끝 -->


<!-- 본문 시작 -->
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 이 작품, 어떠셨나요? </strong></span>
		<br>
	</div>


<div class="container-fluid text-center" style="display: inline-block;" align="left">
	<form name="frm" id="frm" method="post" action="contlistins.do?mcode=${ mcode }">
		<input type="hidden" id="mcode" class="form-control" name="mcode" value="${ mcode }">

			<table class="table">
				<tr>
					<td>
						<div>
							<div>
								<strong>평점</strong>
							</div>
							<input type="radio" id="rev_rate" name="rev_rate" value="1"> 1점 &nbsp;&nbsp;
							<input type="radio" id="rev_rate" name="rev_rate" value="2"> 2점 &nbsp;&nbsp;
							<input type="radio" id="rev_rate" name="rev_rate" value="3"> 3점 &nbsp;&nbsp;
							<input type="radio" id="rev_rate" name="rev_rate" value="4"> 4점 &nbsp;&nbsp;
							<input type="radio" id="rev_rate" name="rev_rate" value="5" checked> 5점 &nbsp;&nbsp;
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<h4 align="left">언제 감상하셨나요?</h4> 
						<input name="rev_reg" id="rev_reg" type="date" class="form-control" required>
					</td>
				</tr>
				<tr>
					<td>
					<input name="rev_title" id="rev_title" type="text" class="form-control" placeholder="제목을 써주세요" size="20"></td>
				</tr>
				<tr>
					<td><textarea class="form-control" id="rev_cont" name="rev_cont" placeholder="작품에 대한 감상을 짧게 남겨주세요. 작성한 글이 제목으로 보여집니다." rows="10" required></textarea></td>
				</tr>
				<tr>
					<td style="text-align: center;">
						<input type="submit" value="입력" class="btn btn-default">
						<input type="reset" value="취소" class="btn btn-default">
					</td>
				</tr>
			</table>

	</form>


</div>


<script>
	function IDlog(mem_lv) {

		if (mem_lv == null || mem_lv == "F") {
			alert("로그인 후 작성가능합니다")
			return false;
		}
		return true;
	}//IDlog() end
</script>





<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>