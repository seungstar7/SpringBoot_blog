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
	}

function account_check(){
	var bank_account = document.getElementById("bank_account").value;
	bank_account = bank_account.trim();
	var bank_check = /^([0-9])-?([0-9]{3,4})-?([0-9]{6})$/;
	if(bank_check == "" || p_check.test(mem_phone) == false){
		alert("전화번호 형식에 맞게 써주시길 바랍니다.");
		document.getElementById("mem_phone").focus();
		phonecheck = 1;
		return false;
	} else {
		alert("해당 유형이 맞습니다.");
		phonecheck = 1;
		return true;
	}
	
}
	