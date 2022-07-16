<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<script type="text/javascript" src="../js/bank.js"></script>

<style>
</style>
<!-- 본문시작 member_bank.jsp -->

<!-- 은행계좌 변경 -->
	<div>
		<h1>변경할 계좌의 은행을 선택해주세요</h1>
	</div>

	<div class="container">
		<div class="col-sm-4" id="bank_citi" onselect="bank_check()">
			<br> <a href="#form_set" data-toggle="collapse">
				<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
					id="bank_select" name="bank_select" value="씨티"
					onclick="bank_check(this.value)">
					<img src="../../images/bank_citi.png" alt="Random Name" width="80"
						height="80"><br>
					<span>씨티은행</span>
				</button>
			</a>
		</div>
		<div class="col-sm-4" id="bank_epost" onselect="bank_check()">
			<br> <a href="#form_set" data-toggle="collapse">
				<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
					id="bank_select" name="bank_select" value="우체국"
					onclick="bank_check(this.value)">
					<img src="../../images/bank_epost.png" alt="Random Name" width="80"
						height="80"><br>
					<span>우체국은행</span>
				</button>
			</a>
		</div>
		<div class="col-sm-4" id="bank_hana" onselect="bank_check()">
			<br> <a href="#form_set" data-toggle="collapse">
				<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
					id="bank_select" name="bank_select" value="하나"
					onclick="bank_check(this.value)">
					<img src="../../images/bank_hana.png" alt="Random Name" width="80"
						height="80"><br>
					<span>하나은행</span>
				</button>
			</a>
		</div>
		<div class="col-sm-4" id="bank_ibk" onselect="bank_check()">
			<br> <a href="#form_set" data-toggle="collapse">
				<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
					id="bank_select" name="bank_select" value="기업"
					onclick="bank_check(this.value)">
					<img src="../../images/bank_ibk.png" alt="Random Name" width="80"
						height="80"><br>
					<span>기업은행</span>
				</button>
			</a>
		</div>
		<div class="col-sm-4" id="bank_kakao" onselect="bank_check()">
			<br> <a href="#form_set" data-toggle="collapse">
				<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
					id="bank_select" name="bank_select" value="카카오"
					onclick="bank_check(this.value)">
					<img src="../../images/bank_kakao.png" alt="Random Name" width="80"
						height="80"><br>
					<span>카카오뱅크</span>
				</button>
			</a>
		</div>
		<div class="col-sm-4" id="bank_kb" onselect="bank_check()">
			<br> <a href="#form_set" data-toggle="collapse">
				<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
					id="bank_select" name="bank_select" value="국민"
					onclick="bank_check(this.value)">
					<img src="../../images/bank_kb.png" alt="Random Name" width="80"
						height="80"><br>
					<span>국민은행</span>
				</button>
			</a>
		</div>
		<div class="col-sm-4" id="bank_nh" onselect="bank_check()">
			<br> <a href="#form_set" data-toggle="collapse">
				<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
					id="bank_select" name="bank_select" value="농협"
					onclick="bank_check(this.value)">
					<img src="../../images/bank_nh.png" alt="Random Name" width="80"
						height="80"><br>
					<span>농협은행</span>
				</button>
			</a>
		</div>
		<div class="col-sm-4" id="bank_shinhan" onselect="bank_check()">
			<br> <a href="#form_set" data-toggle="collapse">
				<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
					id="bank_select" name="bank_select" value="신한"
					onclick="bank_check(this.value)">
					<img src="../../images/bank_shinhan.png" alt="Random Name"
						width="80" height="80"><br>
					<span>신한은행</span>
				</button>
			</a>
		</div>
		<div class="col-sm-4" id="bank_woori" onselect="bank_check()">
			<br> <a href="#form_set" data-toggle="collapse">
				<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
					id="bank_select" name="bank_select" value="우리"
					onclick="bank_check(this.value)">
					<img src="../../images/bank_woori.png" alt="Random Name" width="80"
						height="80"><br>
					<span>우리은행</span>
				</button>
			</a>
		</div>
	</div>
	<div class="container text-center">
	  <div id="form_set" class="collapse">
		<form name="frm" method="post">
		 <strong> 기존 계좌 </strong>
	     <p id="oldbank_name"> ${ bankdto.bank_name } </p>
	     <input type="text" class="form-control input-lg" size="30" id="oldbank_account" name="oldbank_account" placeholder="기존 계좌번호" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" required 
	     value="${ bankdto.bank_account }" readonly /> 
		 <br><br>
		 <strong>변경 계좌</strong>
		 <input type="text" id="bank_name" name="bank_name" readonly>
		 <input type="text" class="form-control input-lg" size="30" id="bank_account" name="bank_account" placeholder="변경 계좌번호 입력" onfocusout="bankaccountCheck()" required /> 
		 <input type="submit" value="변경" class="w-btn-neon2">
		</form>
	   </div>
	 </div>


<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>