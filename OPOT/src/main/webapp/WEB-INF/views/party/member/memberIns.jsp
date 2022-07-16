<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<!-- 본문시작 memberIns.jsp -->
	
	<!-- 파티원 정보 입력 -->
	
	<div class="container-fluid text-center">	
	<h1>${ ott_name }</h1>
		<form name="cardfrm" id="cardfrm" method="post" action="member.do">
		<input type="hidden" id="ott_name" name="ott_name" value="${ ott_name }">
		<input type="hidden" id="ott_price" name="ott_price" value="${ ott_price }">
			<div class="container text-center">
				<input name="card_no" id="card_no" type="number" class="form-control" size="20" maxlength="16" placeholder="카드 번호를 입력 해 주세요" required>
				카드 년 월 입력
				<select name="card_m" id="card_m">
		                <option value="01">01</option>
		                <option value="02">02</option>
		                <option value="03">03</option>
		                <option value="04">04</option>
		                <option value="05">05</option>
		                <option value="06">06</option>
		                <option value="07">07</option>
		                <option value="08">08</option>
		                <option value="09">09</option>
		                <option value="10">10</option>
		                <option value="11">11</option>
		                <option value="12">12</option>            
		        </select>
		        <select name="card_y" id="card_y">
		                <option value="22">22</option>
		                <option value="23">23</option>
		                <option value="24">24</option>
		                <option value="25">25</option>
		                <option value="26">26</option>
		                <option value="27">27</option>
		                <option value="28">28</option>
		                <option value="29">29</option>
		                <option value="30">30</option>
		                <option value="31">31</option>
		                <option value="32">32</option>
		                <option value="33">33</option>            
		        </select>
		        <br>
		        비밀번호 입력<input type="password" name="card_pw" id="card_pw" maxlength="2">  
		        <br>
		        카드사
		        <select name="card_com" id="card_com" >
		                <option value="신한">신한</option>
		                <option value="국민">국민</option>
		                <option value="삼성">삼성</option>
		                <option value="케이뱅크">케이뱅크</option>
		                <option value="카카오">카카오</option>
		                <option value="BC">BC</option>
		                <option value="외환">외환</option>
		                <option value="기업">기업</option>      
		        </select>
		        <br>
				<input type="submit" value="입력"  class="btn btn-danger">
		        <input type="reset"  value="취소"  class="btn btn-danger">
			</div>
		</form>
	
	</div>




<!-- 본문끝 -->

<%@ include file="../../footer.jsp"%>