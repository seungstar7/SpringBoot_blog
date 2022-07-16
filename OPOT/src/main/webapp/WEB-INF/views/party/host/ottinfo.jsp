<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<!-- 본문시작 accountInsert.jsp -->
	
	<!-- 계좌번호 입력 -->
	
	<div class="container-fluid text-center">
		<br><br>	
	
	
		<div><h1>공유할 ${ ott_name } 계정을 등록해주세요</h1></div>
		
		<br><br><br>
	    <form name="frm" method="post" action="checkout.do">
			
			<input type="hidden" id="ott_name" name="ott_name" value="${ ott_name }">
			<input type="hidden" id="ott_price" name="ott_price" value="${ ott_price }">
			<input type="hidden" id="payback_amount" name="payback_amount" value="${ payback_amount }">
			<input type="hidden" id="bank_name" name="bank_name" value="${ bank_name }">
			<input type="hidden" id="bank_account" name="bank_account" value="${ bank_account }">
			
			<div class="container text-center">
			<div class="input-group">
		      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		      <input id="ott_id" name="ott_id" type="text" class="form-control input-lg" placeholder="공유할 ${ ott_name }아이디 입력">
		    </div>
		    <div class="input-group">
		      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		      <input id="ott_pw" type="password" class="form-control input-lg" name="ott_pw" placeholder="공유할 OTT 비밀번호 입력">
		    </div>
		    <br>
			</div>
		    
		    <div>
		      <input type="submit" value="다음" class="w-btn-neon2">
		    </div>  
	    </form>
	
	</div>




<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>