<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본문시작 test.jsp -->

<!-- OTT/역할(파티장or파티원) 선택 -->

<div class="container-fluid text-center">

	<div id="start-wrap" style="margin-top: 100px">
		<a href="#party" data-toggle="collapse"><button id="start-btn" class='w-btn-neon2' type='button'><img src="/images/pot_icon.png" alt="OPOT" width="30px"> 파티 매칭 시작하기 <span class='caret'></span></button></a>
		
		<hr>
	</div>
<div id="party" class="collapse">
	<h1>OTT를 선택해 주세요</h1>
	<div class="col-lg-3 col-sm-6" id="netflix">
		<br> <a href="#ott_set" data-toggle="collapse">
			<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button" id="ott_select" name="ott_select"
				value="netflix" onclick="ott_check(this.value)">
				<img src="../../images/icon_netflix_search.png" alt="Random Name"
					width="255" height="255"><br>
					<span>Netflix</span>
			</button>
		</a>
	</div>
	<div class="col-lg-3 col-sm-6" id="tving">
		<br> <a href="#ott_set" data-toggle="collapse">
			<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button" id="ott_select" name="ott_select"
				value="tving" onclick="ott_check(this.value)">
				<img src="../../images/icon_tving_search.png" alt="Random Name"
					width="255" height="255"><br>
					<span>Tving</span>
			</button>
		</a>
	</div>
	<div class="col-lg-3 col-sm-6" id="watcha">
		<br> <a href="#ott_set" data-toggle="collapse">
			<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button" id="ott_select" name="ott_select"
				value="watcha" onclick="ott_check(this.value)">
				<img src="../../images/icon_watcha_search.png" alt="Random Name"
					width="255" height="255"><br>
					<span>Watcha</span>
			</button>
		</a>
	</div>
	<div class="col-lg-3 col-sm-6" id="disney">
		<br> <a href="#ott_set" data-toggle="collapse">
			<button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button" id="ott_select" name="ott_select"
				value="disney" onclick="ott_check(this.value)">
				<img src="../../images/icon_disney_search.png" alt="Random Name"
					width="255" height="255"><br>
					<span>Disney</span>
			</button>
		</a>
	</div>
</div>


	<div id="ott_set" class="collapse">

	</div>


		<div id="party_set" class="collapse">
			<h1>파티 역할을 선택해 주세요</h1>
			<div class="col-sm-6" id="party_host" >
				<br> <a href="#result" data-toggle="collapse">
					<button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id="party_select" name="party_select" value="party_host"
						onclick="party_select_check(this.value)">
						<img src="../../images/partyhost.png" alt="Random Name" width="255"
							height="255"><br>
							<span>파티장</span>
							
					</button>
				</a>
			</div>
			<div class="col-sm-6" id="party_member">
				<br> <a href="#result" data-toggle="collapse">
					<button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id="party_select" name="party_select" value="party_member"
						onclick="party_select_check(this.value)">
						<img src="../../images/partymem.png" alt="Random Name" width="255"
							height="255"><br>
							<span>파티원</span>
					</button>
				</a>
			</div>
		</div>

	<hr>
	<hr>


	<div id="result" class="collapse">
		<form name="frm" method="post" action="partyadd.do" onsubmit="return check()">
			<input type="hidden" id="party_role" name="party_role"> 
			<input type="hidden" id="ott_name" name="ott_name"> 
			<input type='submit' id='submit' class='btn btn-primary btn-lg' value='파티장으로 파티 생성'>
		</form>
	</div>
</div>

<script>

	$('#start-btn').click(function(){
		$('#start-wrap').css('margin-top', '50px');
	})
	
	function check() {
		var ott_name = document.getElementById('ott_name').value;
		if (ott_name == null) {
			alert("구독하실 OTT를 선택하세요")
			return false;
		}//if end
		return true;
	}

	function ott_check(ott_select) {
		if (ott_select == 'disney') {
			document.getElementById('ott_set').innerHTML = "<a href='#party_set' data-toggle='collapse'><button class='w-btn-neon2' type='button'>디즈니로 파티 역할 선택<span class='caret'></span></button></a><br><br><br><br><br>"
			document.getElementById('disney').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='disney' onclick='ott_check(this.value)' disabled><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='48'></span><img src='../../images/icon_disney_search.png'  class='img-circle person' alt='Random Name' width='255' height='255'><br><span>Disney</span></button><br><br><br><br><br>"
			document.getElementById('netflix').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='netflix' onclick='ott_check(this.value)'><img src='../../images/icon_netflix_search.png'  alt='Random Name' width='255' height='255'><br><span>Netflix</span></button>"
			document.getElementById('tving').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='tving' onclick='ott_check(this.value)' ><img src='../../images/icon_tving_search.png'  alt='Random Name' width='255' height='255'><br><span>Tving</span></button>"
			document.getElementById('watcha').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='watcha' onclick='ott_check(this.value)' ><img src='../../images/icon_watcha_search.png'  alt='Random Name' width='255' height='255'><br><span>Watcha</span></button>"
			document.getElementById('ott_name').value = ott_select;
		} else if (ott_select == 'netflix') {
			document.getElementById('ott_set').innerHTML = "<a href='#party_set' data-toggle='collapse'><button class='w-btn-neon2' type='button'>넷플릭스로 파티 역할 선택<span class='caret'></span></button></a><br><br><br><br><br>"
			document.getElementById('disney').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='disney' onclick='ott_check(this.value)' ><img src='../../images/icon_disney_search.png'  alt='Random Name' width='255' height='255'><br><span>Disney</span></button><br><br><br><br><br>"
			document.getElementById('netflix').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='netflix' onclick='ott_check(this.value)' disabled><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='48'></span><img src='../../images/icon_netflix_search.png'  class='img-circle person' alt='Random Name' width='255' height='255'><br><span>Netflix</span></button>"
			document.getElementById('tving').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='tving' onclick='ott_check(this.value)' ><img src='../../images/icon_tving_search.png'  alt='Random Name' width='255' height='255'><br><span>Tving</span></button>"
			document.getElementById('watcha').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='watcha' onclick='ott_check(this.value)' ><img src='../../images/icon_watcha_search.png'  alt='Random Name' width='255' height='255'><br><span>Watcha</span></button>"
			document.getElementById('ott_name').value = ott_select;
		} else if (ott_select == 'tving') {
			document.getElementById('ott_set').innerHTML = "<a href='#party_set' data-toggle='collapse'><button class='w-btn-neon2' type='button'>티빙으로 파티 역할 선택<span class='caret'></span></button></a><br><br><br><br><br>"
			document.getElementById('disney').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='disney' onclick='ott_check(this.value)' ><img src='../../images/icon_disney_search.png'  alt='Random Name' width='255' height='255'><br><span>Disney</span></button><br><br><br><br><br>"
			document.getElementById('netflix').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='netflix' onclick='ott_check(this.value)'><img src='../../images/icon_netflix_search.png'  alt='Random Name' width='255' height='255'><br><span>Netflix</span></button>"
			document.getElementById('tving').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='tving' onclick='ott_check(this.value)' disabled ><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='48'></span><img src='../../images/icon_tving_search.png'  class='img-circle person' alt='Random Name' width='255' height='255'><br><span>Tving</span></button>"
			document.getElementById('watcha').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='watcha' onclick='ott_check(this.value)' ><img src='../../images/icon_watcha_search.png'  alt='Random Name' width='255' height='255'><br><span>Watcha</span></button>"
			document.getElementById('ott_name').value = ott_select;
		} else if (ott_select == 'watcha') {
			document.getElementById('ott_set').innerHTML = "<a href='#party_set' data-toggle='collapse'><button class='w-btn-neon2' type='button'>왓챠로 파티 역할 선택<span class='caret'></span></button></a><br><br><br><br><br>"
			document.getElementById('disney').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='disney' onclick='ott_check(this.value)' ><img src='../../images/icon_disney_search.png'  alt='Random Name' width='255' height='255'><br><span>Disney</span></button><br><br><br><br><br>"
			document.getElementById('netflix').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='netflix' onclick='ott_check(this.value)'><img src='../../images/icon_netflix_search.png'  alt='Random Name' width='255' height='255'><br><span>Netflix</span></button>"
			document.getElementById('tving').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='tving' onclick='ott_check(this.value)' ><img src='../../images/icon_tving_search.png'  alt='Random Name' width='255' height='255'><br><span>Tving</span></button>"
			document.getElementById('watcha').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='ott_select' name='ott_select' value='watcha' onclick='ott_check(this.value)'disabled ><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='48'></span><img src='../../images/icon_watcha_search.png'  class='img-circle person' alt='Random Name' width='255' height='255'><br><span>Watcha</span></button>"
			document.getElementById('ott_name').value = ott_select;
		}

	}

	function party_select_check(party_select) {
		if (party_select == 'party_host') {
			document.getElementById('party_role').value = party_select;
			document.getElementById('party_member').innerHTML = "<br> <button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='party_select' name='party_select' value='party_member' onclick='party_select_check(this.value)'><img src='../../images/partymem.png' alt='Random Name' width='255' height='255'><br><span>파티원</span></a>"
			document.getElementById('party_host').innerHTML = "<br> <button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='party_select' name='party_select' value='party_host' onclick='party_select_check(this.value)' disabled><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='48'></span><img src='../../images/partyhost.png' class='img-circle person' alt='Random Name' width='255' height='255'><br><span>파티장</span></button></a>"
			document.getElementById('submit').outerHTML = "<input type='submit' id='submit' class='w-btn-neon2' value='파티장으로 파티 생성'>"
		} else if (party_select == 'party_member') {
			document.getElementById('party_role').value = party_select;
			document.getElementById('party_member').innerHTML = "<br><button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='party_select' name='party_select' value='party_member' onclick='party_select_check(this.value)' disabled ><span style='position: absolute; right: 10px;'><img src='../../images/check.png' width='48'></span><img src='../../images/partymem.png' class='img-circle person' alt='Random Name' width='255' height='255'><br><span>파티원</span></button></a>"
			document.getElementById('party_host').innerHTML = "<br> <button class='w-btn w-btn-gra2 w-btn-gra-anim' type='button' id='party_select' name='party_select' value='party_host' onclick='party_select_check(this.value)'><img src='../../images/partyhost.png' alt='Random Name' width='255' height='255'><br><span>파티원</span></button></a>"
			document.getElementById('submit').outerHTML = "<input type='submit' id='submit' class='w-btn-neon2' value='파티원으로 파티 가입'>"		
		}//if end

	}


</script>




<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>