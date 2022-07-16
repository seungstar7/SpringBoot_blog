<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>
<style>
</style>
<!-- 본문시작 accountUpdate.jsp -->

<!-- 은행계좌 업데이트 (DB에 저장된 계좌가 있을 때) -->
<div>
	<h1>계좌 변경</h1>
</div>
<div class="container">
	<div class="col-sm-4" id="bank_citi">
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
	<div class="col-sm-4" id="bank_epost">
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
	<div class="col-sm-4" id="bank_hana">
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
	<div class="col-sm-4" id="bank_ibk">
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
	<div class="col-sm-4" id="bank_kakao">
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
	<div class="col-sm-4" id="bank_kb">
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
	<div class="col-sm-4" id="bank_nh">
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
	<div class="col-sm-4" id="bank_shinhan">
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
	<div class="col-sm-4" id="bank_woori">
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

	<br> <br> <br> <br>


	<div class="container text-center">
		<div id="form_set" class="collapse">
			<form name="frm" method="post" action="ottinfo.do">
				<input type="hidden" id="ott_name" name="ott_name"
					value="${ ott_name }"> <input type="hidden" id="ott_price"
					name="ott_price" value="${ ott_price }"> <input
					type="hidden" id="payback_amount" name="payback_amount"
					value="${ payback_amount }"> <input type="hidden"
					id="bank_name" name="bank_name"> <input type="text"
					class="form-control input-lg" autocomplete="off" size="20"
					id="bank_account" name="bank_account" placeholder="본인명의 계좌번호 입력"
					oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
					required /> <br> <br> <input type="submit" value="다음"
					class="w-btn-neon2">
			</form>
		</div>


	</div>

<script>
	function bank_check(bank_select) {
		if (bank_select == '농협') {
			document.getElementById('bank_citi').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='씨티' onclick='bank_check(this.value)'><img src='../../images/bank_citi.png' alt='Random Name' width='80' height='80'><br><span>씨티은행</span></button>"
			document.getElementById('bank_epost').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우체국' onclick='bank_check(this.value)'><img src='../../images/bank_epost.png' alt='Random Name' width='80' height='80'><br><span>우체국은행</span></button>"
			document.getElementById('bank_hana').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='하나' onclick='bank_check(this.value)'><img src='../../images/bank_hana.png' alt='Random Name' width='80' height='80'><br><span>하나은행</span></button>"
			document.getElementById('bank_ibk').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='기업' onclick='bank_check(this.value)'><img src='../../images/bank_ibk.png' alt='Random Name' width='80' height='80'><br><span>기업은행</span> </button>"
			document.getElementById('bank_kakao').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='카카오' onclick='bank_check(this.value)'><img src='../../images/bank_kakao.png' alt='Random Name' width='80' height='80'><br><span>카카오뱅크</span></button>"
			document.getElementById('bank_kb').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='국민' onclick='bank_check(this.value)'><img src='../../images/bank_kb.png' alt='Random Name' width='80' height='80'><br><span>국민은행</span></button>"
			document.getElementById('bank_nh').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='농협' onclick='bank_check(this.value)'><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='30' ></span><img src='../../images/bank_nh.png' alt='Random Name' width='80' height='80'><br><span>농협은행</span></button>"
			document.getElementById('bank_shinhan').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='신한' onclick='bank_check(this.value)'><img src='../../images/bank_shinhan.png' alt='Random Name' width='80' height='80'><br><span>신한은행</span></button>"
			document.getElementById('bank_woori').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우리' onclick='bank_check(this.value)'><img src='../../images/bank_woori.png' alt='Random Name' width='80' height='80'><br><span>우리은행</span></button>"
			document.getElementById('bank_name').value = bank_select;

		} else if (bank_select == '씨티') {
			document.getElementById('bank_citi').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='씨티' onclick='bank_check(this.value)'><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='30' ></span><img src='../../images/bank_citi.png' alt='Random Name' width='80' height='80'><br><span>씨티은행</span></button>"
			document.getElementById('bank_epost').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우체국' onclick='bank_check(this.value)'><img src='../../images/bank_epost.png' alt='Random Name' width='80' height='80'><br><span>우체국은행</span></button>"
			document.getElementById('bank_hana').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='하나' onclick='bank_check(this.value)'><img src='../../images/bank_hana.png' alt='Random Name' width='80' height='80'><br><span>하나은행</span></button>"
			document.getElementById('bank_ibk').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='기업' onclick='bank_check(this.value)'><img src='../../images/bank_ibk.png' alt='Random Name' width='80' height='80'><br><span>기업은행</span> </button>"
			document.getElementById('bank_kakao').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='카카오' onclick='bank_check(this.value)'><img src='../../images/bank_kakao.png' alt='Random Name' width='80' height='80'><br><span>카카오뱅크</span></button>"
			document.getElementById('bank_kb').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='국민' onclick='bank_check(this.value)'><img src='../../images/bank_kb.png' alt='Random Name' width='80' height='80'><br><span>국민은행</span></button>"
			document.getElementById('bank_nh').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='농협' onclick='bank_check(this.value)'><img src='../../images/bank_nh.png' alt='Random Name' width='80' height='80'><br><span>농협은행</span></button>"
			document.getElementById('bank_shinhan').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='신한' onclick='bank_check(this.value)'><img src='../../images/bank_shinhan.png' alt='Random Name' width='80' height='80'><br><span>신한은행</span></button>"
			document.getElementById('bank_woori').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우리' onclick='bank_check(this.value)'><img src='../../images/bank_woori.png' alt='Random Name' width='80' height='80'><br><span>우리은행</span></button>"
			document.getElementById('bank_name').value = bank_select;
		} else if (bank_select == '우체국') {
			document.getElementById('bank_citi').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='씨티' onclick='bank_check(this.value)'><img src='../../images/bank_citi.png' alt='Random Name' width='80' height='80'><br><span>씨티은행</span></button>"
			document.getElementById('bank_epost').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우체국' onclick='bank_check(this.value)'><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='30' ></span><img src='../../images/bank_epost.png' alt='Random Name' width='80' height='80'><br><span>우체국은행</span></button>"
			document.getElementById('bank_hana').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='하나' onclick='bank_check(this.value)'><img src='../../images/bank_hana.png' alt='Random Name' width='80' height='80'><br><span>하나은행</span></button>"
			document.getElementById('bank_ibk').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='기업' onclick='bank_check(this.value)'><img src='../../images/bank_ibk.png' alt='Random Name' width='80' height='80'><br><span>기업은행</span> </button>"
			document.getElementById('bank_kakao').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='카카오' onclick='bank_check(this.value)'><img src='../../images/bank_kakao.png' alt='Random Name' width='80' height='80'><br><span>카카오뱅크</span></button>"
			document.getElementById('bank_kb').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='국민' onclick='bank_check(this.value)'><img src='../../images/bank_kb.png' alt='Random Name' width='80' height='80'><br><span>국민은행</span></button>"
			document.getElementById('bank_nh').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='농협' onclick='bank_check(this.value)'><img src='../../images/bank_nh.png' alt='Random Name' width='80' height='80'><br><span>농협은행</span></button>"
			document.getElementById('bank_shinhan').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='신한' onclick='bank_check(this.value)'><img src='../../images/bank_shinhan.png' alt='Random Name' width='80' height='80'><br><span>신한은행</span></button>"
			document.getElementById('bank_woori').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우리' onclick='bank_check(this.value)'><img src='../../images/bank_woori.png' alt='Random Name' width='80' height='80'><br><span>우리은행</span></button>"
			document.getElementById('bank_name').value = bank_select;
		} else if (bank_select == '하나') {
			document.getElementById('bank_citi').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='씨티' onclick='bank_check(this.value)'><img src='../../images/bank_citi.png' alt='Random Name' width='80' height='80'><br><span>씨티은행</span></button>"
			document.getElementById('bank_epost').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우체국' onclick='bank_check(this.value)'><img src='../../images/bank_epost.png' alt='Random Name' width='80' height='80'><br><span>우체국은행</span></button>"
			document.getElementById('bank_hana').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='하나' onclick='bank_check(this.value)'><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='30' ></span><img src='../../images/bank_hana.png' alt='Random Name' width='80' height='80'><br><span>하나은행</span></button>"
			document.getElementById('bank_ibk').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='기업' onclick='bank_check(this.value)'><img src='../../images/bank_ibk.png' alt='Random Name' width='80' height='80'><br><span>기업은행</span> </button>"
			document.getElementById('bank_kakao').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='카카오' onclick='bank_check(this.value)'><img src='../../images/bank_kakao.png' alt='Random Name' width='80' height='80'><br><span>카카오뱅크</span></button>"
			document.getElementById('bank_kb').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='국민' onclick='bank_check(this.value)'><img src='../../images/bank_kb.png' alt='Random Name' width='80' height='80'><br><span>국민은행</span></button>"
			document.getElementById('bank_nh').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='농협' onclick='bank_check(this.value)'><img src='../../images/bank_nh.png' alt='Random Name' width='80' height='80'><br><span>농협은행</span></button>"
			document.getElementById('bank_shinhan').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='신한' onclick='bank_check(this.value)'><img src='../../images/bank_shinhan.png' alt='Random Name' width='80' height='80'><br><span>신한은행</span></button>"
			document.getElementById('bank_woori').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우리' onclick='bank_check(this.value)'><img src='../../images/bank_woori.png' alt='Random Name' width='80' height='80'><br><span>우리은행</span></button>"
			document.getElementById('bank_name').value = bank_select;
		} else if (bank_select == '기업') {
			document.getElementById('bank_citi').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='씨티' onclick='bank_check(this.value)'><img src='../../images/bank_citi.png' alt='Random Name' width='80' height='80'><br><span>씨티은행</span></button>"
			document.getElementById('bank_epost').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우체국' onclick='bank_check(this.value)'><img src='../../images/bank_epost.png' alt='Random Name' width='80' height='80'><br><span>우체국은행</span></button>"
			document.getElementById('bank_hana').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='하나' onclick='bank_check(this.value)'><img src='../../images/bank_hana.png' alt='Random Name' width='80' height='80'><br><span>하나은행</span></button>"
			document.getElementById('bank_ibk').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='기업' onclick='bank_check(this.value)'><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='30' ></span><img src='../../images/bank_ibk.png' alt='Random Name' width='80' height='80'><br><span>기업은행</span> </button>"
			document.getElementById('bank_kakao').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='카카오' onclick='bank_check(this.value)'><img src='../../images/bank_kakao.png' alt='Random Name' width='80' height='80'><br><span>카카오뱅크</span></button>"
			document.getElementById('bank_kb').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='국민' onclick='bank_check(this.value)'><img src='../../images/bank_kb.png' alt='Random Name' width='80' height='80'><br><span>국민은행</span></button>"
			document.getElementById('bank_nh').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='농협' onclick='bank_check(this.value)'><img src='../../images/bank_nh.png' alt='Random Name' width='80' height='80'><br><span>농협은행</span></button>"
			document.getElementById('bank_shinhan').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='신한' onclick='bank_check(this.value)'><img src='../../images/bank_shinhan.png' alt='Random Name' width='80' height='80'><br><span>신한은행</span></button>"
			document.getElementById('bank_woori').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우리' onclick='bank_check(this.value)'><img src='../../images/bank_woori.png' alt='Random Name' width='80' height='80'><br><span>우리은행</span></button>"
			document.getElementById('bank_name').value = bank_select;
		} else if (bank_select == '카카오') {
			document.getElementById('bank_citi').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='씨티' onclick='bank_check(this.value)'><img src='../../images/bank_citi.png' alt='Random Name' width='80' height='80'><br><span>씨티은행</span></button>"
			document.getElementById('bank_epost').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우체국' onclick='bank_check(this.value)'><img src='../../images/bank_epost.png' alt='Random Name' width='80' height='80'><br><span>우체국은행</span></button>"
			document.getElementById('bank_hana').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='하나' onclick='bank_check(this.value)'><img src='../../images/bank_hana.png' alt='Random Name' width='80' height='80'><br><span>하나은행</span></button>"
			document.getElementById('bank_ibk').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='기업' onclick='bank_check(this.value)'><img src='../../images/bank_ibk.png' alt='Random Name' width='80' height='80'><br><span>기업은행</span> </button>"
			document.getElementById('bank_kakao').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='카카오' onclick='bank_check(this.value)'><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='30' ></span><img src='../../images/bank_kakao.png' alt='Random Name' width='80' height='80'><br><span>카카오뱅크</span></button>"
			document.getElementById('bank_kb').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='국민' onclick='bank_check(this.value)'><img src='../../images/bank_kb.png' alt='Random Name' width='80' height='80'><br><span>국민은행</span></button>"
			document.getElementById('bank_nh').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='농협' onclick='bank_check(this.value)'><img src='../../images/bank_nh.png' alt='Random Name' width='80' height='80'><br><span>농협은행</span></button>"
			document.getElementById('bank_shinhan').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='신한' onclick='bank_check(this.value)'><img src='../../images/bank_shinhan.png' alt='Random Name' width='80' height='80'><br><span>신한은행</span></button>"
			document.getElementById('bank_woori').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우리' onclick='bank_check(this.value)'><img src='../../images/bank_woori.png' alt='Random Name' width='80' height='80'><br><span>우리은행</span></button>"
			document.getElementById('bank_name').value = bank_select;
		} else if (bank_select == '국민') {
			document.getElementById('bank_citi').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='씨티' onclick='bank_check(this.value)'><img src='../../images/bank_citi.png' alt='Random Name' width='80' height='80'><br><span>씨티은행</span></button>"
			document.getElementById('bank_epost').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우체국' onclick='bank_check(this.value)'><img src='../../images/bank_epost.png' alt='Random Name' width='80' height='80'><br><span>우체국은행</span></button>"
			document.getElementById('bank_hana').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='하나' onclick='bank_check(this.value)'><img src='../../images/bank_hana.png' alt='Random Name' width='80' height='80'><br><span>하나은행</span></button>"
			document.getElementById('bank_ibk').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='기업' onclick='bank_check(this.value)'><img src='../../images/bank_ibk.png' alt='Random Name' width='80' height='80'><br><span>기업은행</span> </button>"
			document.getElementById('bank_kakao').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='카카오' onclick='bank_check(this.value)'><img src='../../images/bank_kakao.png' alt='Random Name' width='80' height='80'><br><span>카카오뱅크</span></button>"
			document.getElementById('bank_kb').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='국민' onclick='bank_check(this.value)'><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='30' ></span><img src='../../images/bank_kb.png' alt='Random Name' width='80' height='80'><br><span>국민은행</span></button>"
			document.getElementById('bank_nh').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='농협' onclick='bank_check(this.value)'><img src='../../images/bank_nh.png' alt='Random Name' width='80' height='80'><br><span>농협은행</span></button>"
			document.getElementById('bank_shinhan').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='신한' onclick='bank_check(this.value)'><img src='../../images/bank_shinhan.png' alt='Random Name' width='80' height='80'><br><span>신한은행</span></button>"
			document.getElementById('bank_woori').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우리' onclick='bank_check(this.value)'><img src='../../images/bank_woori.png' alt='Random Name' width='80' height='80'><br><span>우리은행</span></button>"
			document.getElementById('bank_name').value = bank_select;
		} else if (bank_select == '신한') {
			document.getElementById('bank_citi').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='씨티' onclick='bank_check(this.value)'><img src='../../images/bank_citi.png' alt='Random Name' width='80' height='80'><br><span>씨티은행</span></button>"
			document.getElementById('bank_epost').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우체국' onclick='bank_check(this.value)'><img src='../../images/bank_epost.png' alt='Random Name' width='80' height='80'><br><span>우체국은행</span></button>"
			document.getElementById('bank_hana').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='하나' onclick='bank_check(this.value)'><img src='../../images/bank_hana.png' alt='Random Name' width='80' height='80'><br><span>하나은행</span></button>"
			document.getElementById('bank_ibk').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='기업' onclick='bank_check(this.value)'><img src='../../images/bank_ibk.png' alt='Random Name' width='80' height='80'><br><span>기업은행</span> </button>"
			document.getElementById('bank_kakao').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='카카오' onclick='bank_check(this.value)'><img src='../../images/bank_kakao.png' alt='Random Name' width='80' height='80'><br><span>카카오뱅크</span></button>"
			document.getElementById('bank_kb').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='국민' onclick='bank_check(this.value)'><img src='../../images/bank_kb.png' alt='Random Name' width='80' height='80'><br><span>국민은행</span></button>"
			document.getElementById('bank_nh').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='농협' onclick='bank_check(this.value)'><img src='../../images/bank_nh.png' alt='Random Name' width='80' height='80'><br><span>농협은행</span></button>"
			document.getElementById('bank_shinhan').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='신한' onclick='bank_check(this.value)'><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='30' ></span><img src='../../images/bank_shinhan.png' alt='Random Name' width='80' height='80'><br><span>신한은행</span></button>"
			document.getElementById('bank_woori').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우리' onclick='bank_check(this.value)'><img src='../../images/bank_woori.png' alt='Random Name' width='80' height='80'><br><span>우리은행</span></button>"
			document.getElementById('bank_name').value = bank_select;
		} else if (bank_select == '우리') {
			document.getElementById('bank_citi').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='씨티' onclick='bank_check(this.value)'><img src='../../images/bank_citi.png' alt='Random Name' width='80' height='80'><br><span>씨티은행</span></button>"
			document.getElementById('bank_epost').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우체국' onclick='bank_check(this.value)'><img src='../../images/bank_epost.png' alt='Random Name' width='80' height='80'><br><span>우체국은행</span></button>"
			document.getElementById('bank_hana').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='하나' onclick='bank_check(this.value)'><img src='../../images/bank_hana.png' alt='Random Name' width='80' height='80'><br><span>하나은행</span></button>"
			document.getElementById('bank_ibk').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='기업' onclick='bank_check(this.value)'><img src='../../images/bank_ibk.png' alt='Random Name' width='80' height='80'><br><span>기업은행</span> </button>"
			document.getElementById('bank_kakao').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='카카오' onclick='bank_check(this.value)'><img src='../../images/bank_kakao.png' alt='Random Name' width='80' height='80'><br><span>카카오뱅크</span></button>"
			document.getElementById('bank_kb').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='국민' onclick='bank_check(this.value)'><img src='../../images/bank_kb.png' alt='Random Name' width='80' height='80'><br><span>국민은행</span></button>"
			document.getElementById('bank_nh').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='농협' onclick='bank_check(this.value)'><img src='../../images/bank_nh.png' alt='Random Name' width='80' height='80'><br><span>농협은행</span></button>"
			document.getElementById('bank_shinhan').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='신한' onclick='bank_check(this.value)'><img src='../../images/bank_shinhan.png' alt='Random Name' width='80' height='80'><br><span>신한은행</span></button>"
			document.getElementById('bank_woori').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='bank_select' name='bank_select' value='우리' onclick='bank_check(this.value)'><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='30' ></span><img src='../../images/bank_woori.png' alt='Random Name' width='80' height='80'><br><span>우리은행</span></button>"
			document.getElementById('bank_name').value = bank_select;
		}

	}//end
</script>



<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>