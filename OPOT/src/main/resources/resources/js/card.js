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
						document.getElementById('card').innerHTML = " <img src='../../images/Visa.png' width='40px'>  ";
						document.getElementById('cardcom').value = types[i - 1];
						document.getElementById('cardcom').innerHTML = types[i - 1];
					} else if (types[i - 1] == 'BC') {
						document.getElementById('card').innerHTML = " <img src='../../images/bccard.png' width='40px'>  ";
						document.getElementById('cardcom').value = types[i - 1];
						document.getElementById('cardcom').innerHTML = types[i - 1];
					} else if (types[i - 1] == 'MasterCard') {
						document.getElementById('card').innerHTML = " <img src='../../images/master.png' width='40px'>  ";
						document.getElementById('cardcom').value = types[i - 1];
						document.getElementById('cardcom').innerHTML = types[i - 1];
					} else if (types[i - 1] == 'Discover') {
						document.getElementById('card').innerHTML = " <img src='../../images/discover.jpg' width='40px'>  ";
						document.getElementById('cardcom').value = types[i - 1];
						document.getElementById('cardcom').innerHTML = types[i - 1];
					} else if (types[i - 1] == 'American Express') {
						document.getElementById('card').innerHTML = " <img src='../../images/American_Expres.png' width='40px'>  ";
						document.getElementById('cardcom').value = types[i - 1];
						document.getElementById('cardcom').innerHTML = types[i - 1];
					} else if (types[i - 1] == 'Diners Club') {
						document.getElementById('card').innerHTML = " <img src='../../images/dinersclub.jpg' width='40px'>  ";
						document.getElementById('cardcom').value = types[i - 1];
						document.getElementById('cardcom').innerHTML = types[i - 1];
					} else if (types[i - 1] == 'JCB') {
						document.getElementById('card').innerHTML = " <img src='../../images/jcb.png' width='40px'>  ";
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

		var card_com = document.getElementById("card_com").value;
		if (card_com == '00') {
			alert("카드사를 선택 바랍니다");
			document.getElementById("card_com").focus();
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