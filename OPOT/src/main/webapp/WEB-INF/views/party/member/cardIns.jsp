<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>

<!-- 본문시작 cardupdate.jsp -->

<!-- 파티원 정보 입력 -->
<script>
	function validatecardnumber(cardnumber) {

		//빈칸과 대시 제거
		var card_no = document.getElementById("card_no").value;
		var cardnumber = cardnumber.replace(/[ -]/g, '');

		//카드 번호가 유효한지 검사
		//정규식이 캡처 그룹들 중 하나에 들어있는 숫자를 캡처
		var match = /^(?:(94[0-9]{14})|(4[0-9]{12}(?:[0-9]{3})?)|(5[1-5][0-9]{14})|(6(?:011|5[0-9]{2})[0-9]{12})|(3[47][0-9]{13})|(3(?:0[0-5]|[68][0-9])[0-9]{11})|((?:2131|1800|35[0-9]{3})[0-9]{11}))$/
				.exec(cardnumber);

		if (match) {

			//정규식 캡처 그룹과 같은 순서로 카드 종류 나열
			var types = [ 'BC', 'Visa', 'MasterCard', 'Discover',
					'American Express', 'Diners Club', 'JCB' ];

			//일치되는 캡처 그룹 검색
			//일치부 배열의 0번째 요소 (전체 일치부중 첫 일치부)를 건너뜀

			for (var i = 1; i < match.length; i++) {
				if (match[i]) {
					//해당 그룹에 대한 카드 종류를 표시
					document.getElementById('notice').innerHTML = '<div class="alert alert-success"><p id="card"></p><p id="cardcom"></p><div>';
					if (types[i - 1] == 'Visa') {
						document.getElementById('card').innerHTML = " <img src='../../images/card_Visa.png' width='40px'>  ";
						document.getElementById('cardcom').value = types[i - 1];
						document.getElementById('cardcom').innerHTML = types[i - 1];
					} else if (types[i - 1] == 'BC') {
						document.getElementById('card').innerHTML = " <img src='../../images/card_bc.png' width='40px'>  ";
						document.getElementById('cardcom').value = types[i - 1];
						document.getElementById('cardcom').innerHTML = types[i - 1];
					} else if (types[i - 1] == 'MasterCard') {
						document.getElementById('card').innerHTML = " <img src='../../images/card_master.png' width='40px'>  ";
						document.getElementById('cardcom').value = types[i - 1];
						document.getElementById('cardcom').innerHTML = types[i - 1];
					} else if (types[i - 1] == 'Discover') {
						document.getElementById('card').innerHTML = " <img src='../../images/card_discover.jpg' width='40px'>  ";
						document.getElementById('cardcom').value = types[i - 1];
						document.getElementById('cardcom').innerHTML = types[i - 1];
					} else if (types[i - 1] == 'American Express') {
						document.getElementById('card').innerHTML = " <img src='../../images/card_AmericanExpres.png' width='40px'>  ";
						document.getElementById('cardcom').value = types[i - 1];
						document.getElementById('cardcom').innerHTML = types[i - 1];
					} else if (types[i - 1] == 'Diners Club') {
						document.getElementById('card').innerHTML = " <img src='../../images/card_dinersclub.jpg' width='40px'>  ";
						document.getElementById('cardcom').value = types[i - 1];
						document.getElementById('cardcom').innerHTML = types[i - 1];
					} else if (types[i - 1] == 'JCB') {
						document.getElementById('card').innerHTML = " <img src='../../images/card_jcb.png' width='40px'>  ";
						document.getElementById('cardcom').value = types[i - 1];
						document.getElementById('cardcom').innerHTML = types[i - 1];
					}//if end

					break;
				}//if end
			}//for end

		} else {
			document.getElementById('notice').innerHTML = '<div class="alert alert-danger"><p id="cardcom"></p>유효하지 않는 카드번호입니다<div>';
			document.getElementById('cardcom').value = null;
		}
	}//function end

	function check() {
		var cardcom = document.getElementById("cardcom").value;
		if (cardcom == null) {
			alert("유효하지 않는 카드 번호입니다 \n카드번호를 확인 바랍니다");
			document.getElementById("card_no").focus();
			return false;
		}
		


		var card_pw = document.getElementById("card_pw").value;
		if (card_pw == null) {
			alert("비밀번호를 입력 바랍니다");
			document.getElementById("card_pw").focus();
			return false;
		}

		return true;
	}//check() end
</script>


<br><br><br>
<h1>결제정보 등록</h1>
<div class="container">
	<div class="col-sm-4" id="bank_citi">
		<br> <a href="#form_set" data-toggle="collapse">
			<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
				id="bank_select" name="bank_select" value="씨티"
				onclick="bank_check(this.value)">
				<img src="../../images/bank_citi.png" alt="Random Name" width="80"
					height="80"><br> <span>씨티은행</span>
			</button>
		</a>
	</div>
	<div class="col-sm-4" id="bank_epost">
		<br> <a href="#form_set" data-toggle="collapse">
			<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
				id="bank_select" name="bank_select" value="우체국"
				onclick="bank_check(this.value)">
				<img src="../../images/bank_epost.png" alt="Random Name" width="80"
					height="80"><br> <span>우체국은행</span>
			</button>
		</a>
	</div>
	<div class="col-sm-4" id="bank_hana">
		<br> <a href="#form_set" data-toggle="collapse">
			<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
				id="bank_select" name="bank_select" value="하나"
				onclick="bank_check(this.value)">
				<img src="../../images/bank_hana.png" alt="Random Name" width="80"
					height="80"><br> <span>하나은행</span>
			</button>
		</a>
	</div>
	<div class="col-sm-4" id="bank_ibk">
		<br> <a href="#form_set" data-toggle="collapse">
			<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
				id="bank_select" name="bank_select" value="기업"
				onclick="bank_check(this.value)">
				<img src="../../images/bank_ibk.png" alt="Random Name" width="80"
					height="80"><br> <span>기업은행</span>
			</button>
		</a>
	</div>
	<div class="col-sm-4" id="bank_kakao">
		<br> <a href="#form_set" data-toggle="collapse">
			<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
				id="bank_select" name="bank_select" value="카카오"
				onclick="bank_check(this.value)">
				<img src="../../images/bank_kakao.png" alt="Random Name" width="80"
					height="80"><br> <span>카카오뱅크</span>
			</button>
		</a>
	</div>
	<div class="col-sm-4" id="bank_kb">
		<br> <a href="#form_set" data-toggle="collapse">
			<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
				id="bank_select" name="bank_select" value="국민"
				onclick="bank_check(this.value)">
				<img src="../../images/bank_kb.png" alt="Random Name" width="80"
					height="80"><br> <span>국민은행</span>
			</button>
		</a>
	</div>
	<div class="col-sm-4" id="bank_nh">
		<br> <a href="#form_set" data-toggle="collapse">
			<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
				id="bank_select" name="bank_select" value="농협"
				onclick="bank_check(this.value)">
				<img src="../../images/bank_nh.png" alt="Random Name" width="80"
					height="80"><br> <span>농협은행</span>
			</button>
		</a>
	</div>
	<div class="col-sm-4" id="bank_shinhan">
		<br> <a href="#form_set" data-toggle="collapse">
			<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
				id="bank_select" name="bank_select" value="신한"
				onclick="bank_check(this.value)">
				<img src="../../images/bank_shinhan.png" alt="Random Name"
					width="80" height="80"><br> <span>신한은행</span>
			</button>
		</a>
	</div>
	<div class="col-sm-4" id="bank_woori">
		<br> <a href="#form_set" data-toggle="collapse">
			<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
				id="bank_select" name="bank_select" value="우리"
				onclick="bank_check(this.value)">
				<img src="../../images/bank_woori.png" alt="Random Name" width="80"
					height="80"><br> <span>우리은행</span>
			</button>
		</a>
	</div>
</div>


<div class="container text-center">
	<div id="form_set" class="collapse">
		<form name="cardfrm" id="cardfrm" method="post"
			action="member.do" onsubmit="return check()">
		<input type="hidden" id="ott_name" name="ott_name" value="${ ott_name }"> 
		<input type="hidden" id="ott_price" name="ott_price" value="${ ott_price }">
		
			<hr>
			<hr>
			<hr>
			<h3>카드 정보를 입력해주세요</h3>
			<div class="container text-center">
				<input type="text" autocomplete="off" size="20" name="card_no"
					id="card_no" class="form-control input-lg" name="cardnumber"
					onkeyup="validatecardnumber(this.value)" size="20" maxlength="16"
					placeholder="카드 번호를 입력 해 주세요"
					oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
					required />
				<div id='notice'></div>
				<br> <strong>카드 유효기간을 입력해주세요</strong>
				<table class="table">
					<tr>
						<td><select class="form-control input-lg" name="card_m" id="card_m">
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
						</select></td>
						<td><select class="form-control input-lg" name="card_y" id="card_y">
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
						</select></td>
					</tr>
				</table>

				<strong>카드 비밀번호 앞 2자리를 입력해주세요</strong> <input class="form-control input-lg"
					type="password" name="card_pw" id="card_pw" maxlength="2"
					autocomplete="off"
					oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
					required /> 
					<input type="hidden" name="card_com" id="card_com">
					
					 <br> <input type="submit" value="입력" class="w-btn-neon2">
				
			</div>
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
			document.getElementById('card_com').value = bank_select;

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
			document.getElementById('card_com').value = bank_select;
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
			document.getElementById('card_com').value = bank_select;
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
			document.getElementById('card_com').value = bank_select;
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
			document.getElementById('card_com').value = bank_select;
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
			document.getElementById('card_com').value = bank_select;
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
			document.getElementById('card_com').value = bank_select;
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
			document.getElementById('card_com').value = bank_select;
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
			document.getElementById('card_com').value = bank_select;
		}

	}//end
</script>




<!-- 본문끝 -->

<%@ include file="../../footer.jsp"%>