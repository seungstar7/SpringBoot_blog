<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본문시작 agreement.jsp-->
<div class="container text-center">
	
	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 회원 가입 약관 </strong></span>
		<br><br>
	</div>
	
	<form action="memberjoin.do" method="post" onsubmit="return send()">
	

	            <textarea cols="55" rows="14" readonly>	
  OPOT 서비스약관


  제1조(목적 등)
 
① OPOT (opot.cafe24.com) 서비스 약관(이하 "본 약관"이라 합니다)은 이용자가 ㈜ www_opot(이하 "OPOT"이라 합니다)에서 제공하는 인터넷 관련 서비스(이하 "서비스"라 합니다)를 이용함에 있 어 이용자와 "OPOT"의 권리·의무 및 책임사항을 규정함을 목적으로 합니다.
 
② 이용자가 되고자 하는 자가 "OPOT"이 정한 소정의 절차를 거쳐서 "등록하기" 단추를 누르면 본 약관에 동의하는 것 으로 간주합니다. 본 약관에 정하는 이외의 이용자와 "OPOT"의 권리, 의무 및 책임사항에 관해서는 전기통신사업법 기 타 대한민국의 관련 법령과 상관습에 의합니다. 
 

  제2조(이용자의 정의)
 
① 이용자"란 "OPOT"에 접속하여 본 약관에 따라 "OPOT" 회원으로 가입하여 "OPOT"이 제공하는 서비스를 받는 자를 말합니다. 
				
	            </textarea>

	
	<div style="text-align: center">
	  <label><input type="checkbox" name="agree" id="agree"/> 약관에 동의합니다</label>
	  <br>
	  <input type="submit" value="회원가입" id="memBtn" class="btn btn-danger"> 
	  <input type="button" value="취소"    id="memBtn" class="btn btn-danger" onclick="javascript:history.back()">
	</div>
	</form>
	<br><br>
	
</div>

<script>
	function send() {
		if(document.getElementById("agree").checked==true){
			return true;	
		}else{
			alert("약관에 동의한 후 회원가입이 가능합니다.");
			return false;	
		}//if end		
	}//send() end
</script>


<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>	