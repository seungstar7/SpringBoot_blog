<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<script type="text/javascript">

window.history.forward();
function noBack(){
	
	 window.history.forward();
	 }

</script>


<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">


<!-- 본문시작 checkout.jsp -->
	
	<!-- 파티장 신청자가 마지막을 입력한 정보를 확인한 뒤 insert -->
	
	<div class="container-fluid text-center">
	<br><br><br>
	<div><h1>★파티 생성 준비가 완료되었습니다★</h1></div>
	<br>
	<br>
	<h3>파티 생성전에 정보를 확인해 주세요!</h3>
	</div>
	<div class="container">
	<table class="table">
		<tr>
			<th class="success">OTT종류</th>
			<td>${ ott_name }</td>
		</tr>
		<tr>
			<th class="success">OTT 계정 아이디</th>
			<td>${ ott_id }</td>
		</tr>
		
		<tr>
			<th class="success">OTT 계정 비번</th>
			<td>${ ott_pw }</td>
		</tr>
		<tr>
			<th class="success">정산 계좌 번호</th>
			<td>${ bank_name } ${ bank_account }</td>
		</tr>
		
		<tr>
			<th class="success">정산 금액</th>
			<td>${ payback_amount }원</td>
		</tr>

	
	</table>
	</div>
	<a href="#reset" data-toggle="collapse">
	<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button">수정하기</button>
	</a>
	<br><br>
	
	<form name="frm" method="post" action="create.do">
		
		<input type="hidden" id="mem_id" name="mem_id" placeholder="회원아이디" value="${ s_mem_id }"><!-- 세션으로 줄것임 -->
		<input type="hidden" id="ott_name" name="ott_name" placeholder="OTT종류" value="${ ott_name }"><!-- 히든으로 줄것임 -->
		<input type="hidden" id="ott_price" name="ott_price" placeholder="OTT가격" value="${ ott_price }"><!-- 히든으로 줄것임 -->
		<input type="hidden" id="ott_id" name="ott_id" placeholder="OTT 계정 아이디" value="${ ott_id }">
		<input type="hidden" id="ott_pw" name="ott_pw" placeholder="OTT 계정 비번" value="${ ott_pw }">
		<input type="hidden" id="bank_name" name="bank_name" placeholder="정산 계좌 은행명" value="${ bank_name }">
		<input type="hidden" id="bank_account" name="bank_account" placeholder="정산 계좌 번호" value="${ bank_account }">
		<input type="hidden" id="payback_amount" name="payback_amount" placeholder="정산 금액" value="${ payback_amount }">
	    <input type="hidden" id="party_id" name="party_id" placeholder="파티 아이디" value="${ party_id }">
	    <div>
	      <input type="submit" value="바로 파티 생성하기" class="w-btn-neon2"><!-- 파티생성 완료 안내창으로 이동 -->
	    </div>  
    </form>
    
    <br><br><br>
	    

	
	<div id="reset" class="collapse">
	<div><h1>★${ ott_name } 파티 생성전 수정할 내용을 확인해 주세요★</h1></div>
    <form name="frm" method="post" action="create.do">
    	<div class="form-group">
	      <label class="col-sm-4 control-label" style='text-align:right;'>OTT 계정 아이디 :</label>
	      <div class="col-sm-4">
	        <input class="form-control input-lg" type="text" id="ott_id" name="ott_id" placeholder="OTT 계정 아이디" value="${ ott_id }">
	      </div>
	    </div>
	    <br> <br> <br> 
	    <div class="form-group">
	      <label class="col-sm-4 control-label" style='text-align:right;'>OTT 계정 비번 : </label>
	      <div class="col-sm-4">
	        <input class="form-control input-lg" type="text" id="ott_pw" name="ott_pw" placeholder="OTT 계정 비번" value="${ ott_pw }">
	      </div>
	    </div>
	    <br> <br>
	    <div class="form-group">
	      <label class="col-sm-4 control-label" style='text-align:right;'>정산 계좌 은행명 : </label>
	      <div class="col-sm-4">
	      <select class="form-control input-lg" id="bank_name" name="bank_name">
	      				<%String bank_name= request.getParameter("bank_name").trim(); %>
		                <option value="신한" <%if(bank_name.equals("신한")) {out.print("selected");} %>>신한</option>
		                <option value="국민" <%if(bank_name.equals("국민")) {out.print("selected");} %>>국민</option>
		                <option value="하나" <%if(bank_name.equals("하나")) {out.print("selected");} %>>하나</option>
		                <option value="우리" <%if(bank_name.equals("우리")) {out.print("selected");} %>>우리</option>
		                <option value="카카오" <%if(bank_name.equals("카카오")) {out.print("selected");} %>>카카오</option>
		                <option value="우체국" <%if(bank_name.equals("우체국")) {out.print("selected");} %>>우체국</option>
		                <option value="외환" <%if(bank_name.equals("외환")) {out.print("selected");} %>>외환</option>
		                <option value="씨티" <%if(bank_name.equals("씨티")) {out.print("selected");} %>>씨티</option>      
		                <option value="농협" <%if(bank_name.equals("농협")) {out.print("selected");} %>>농협</option>   
		  </select>
	      </div>
	    </div>
	    <br> <br>
	    <div class="form-group">
	      <label class="col-sm-4 control-label" style='text-align:right;'>정산 계좌 번호 : </label>
	      <div class="col-sm-4">
	        <input class="form-control input-lg" type="text" id="bank_account" name="bank_account" placeholder="정산 계좌 번호" value="${ bank_account }"><br>
	      </div>
	    </div>
		<br> <br><br> <br>
		<input type="hidden" id="mem_id" name="mem_id" placeholder="회원아이디" value="${ s_mem_id }"><!-- 세션으로 줄것임 -->
		<input type="hidden" id="ott_name" name="ott_name" placeholder="OTT종류" value="${ ott_name }"><!-- 히든으로 줄것임 -->
		<input type="hidden" id="ott_price" name="ott_price" placeholder="OTT가격" value="${ ott_price }"><!-- 히든으로 줄것임 -->
		<input type="hidden" id="payback_amount" name="payback_amount" placeholder="정산 금액" value="${ payback_amount }">
	    <input type="hidden" id="party_id" name="party_id" placeholder="파티 아이디" value="${ party_id }">
	    
	    <div>
	      <input type="submit" value="수정 후 파티 생성하기" class="w-btn-neon2"><!-- 파티생성 완료 안내창으로 이동 -->
	    </div>  
    </form>
    </div>
	
	




<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>