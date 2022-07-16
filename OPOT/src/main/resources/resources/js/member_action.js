var isRun = false;
var id_check = null, pw_check, birth_check, email_check, phonecheck, check, newpw_check;

function idCheck(){
	var mem_id=document.getElementById("mem_id").value;
	mem_id = mem_id.trim();
	
	if(mem_id.length < 3 || mem_id == null){
		$("#checkId").html("아이디는 4글자 이상 입력해 주세요");
		$("#checkId").attr('color', 'red');
		document.getElementById("mem_id").focus();
		id_check = 1;
		check = 1;
        return false; 
	} else {
	
		$(document).on('click', '#idcheck', function(){
		let user_id = $("#mem_id").val(); 
		
		if( isRun ){
			//alert("처리중");
			return false;
		}
		isRun = true;
		
		$.ajax({
			url : "/IdCheck.do",
			type : "POST",
			data : { mem_id : user_id },
			datatype : "JSON",
			success : function(result){
				isRun = false;
				
				if(result == 0){
					$("#checkId").html("이미 사용중인 아이디입니다");
					$("#checkId").attr('color', 'red');
					id_check = 1;
					check = 1;
					return false;
				} else {
					$("#checkId").html("사용 가능한 아이디입니다");
					$("#checkId").attr('color', 'blue');
					id_check = 0;
					check = 0;
					return true;
				}
			}, 
			error : function(){
				isRun = false;
				alert("서버요청실패");
			} 
		});
	});
	}
	
	
	return false;
}


function emailCheck() {
   var mem_email=document.getElementById("mem_email").value; 
   mem_email=mem_email.trim();
   var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

   if(exptext.test(mem_email)==false){
   	   //이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우         
	   $("#checkEmail").html("이메일 형식을 확인해주세요");
	   $("#checkEmail").attr('color', 'green');
	   document.getElementById("mem_email").focus();
       return false;
   }//if end
   else {
	   $(document).on('blur', '#mem_email', function(){		 	 let user_email = $("#mem_email").val(); 
	         if( isRun ){
				//alert("처리중");
				return false;
			}
			isRun = true;
			
			$.ajax({
				url : "/EmailCheck.do",
				type : "POST",
				data : { mem_email : user_email },
				datatype : "JSON",
				success : function(result){
					isRun = false;
					if(result == 1){
						$("#checkEmail").html("사용 가능한 이메일 입니다.");
						$("#checkEmail").attr('color', 'blue');
						email_check = 0;
						check = 0;
						return true;
					} else {
						$("#checkEmail").html("중복된 이메일 입니다.");
						$("#checkEmail").attr('color', 'red');
						email_check = 1;
						check = 1;
						return false;
					}
					
				}, 
				error : function(){
					isRun = false;
					alert("서버요청실패");
				} 
			});
		});
	  }
         
         return true;
}//emailCheck() end

function phoneCheck(){
	var mem_phone = document.getElementById("mem_phone").value;
	mem_phone = mem_phone.trim();
	var p_check = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
	if(p_check == "" || p_check.test(mem_phone) == false){
		//alert("전화번호 형식을 확인해주세요");
		document.getElementById("mem_phone").focus();
		phonecheck = 1;
		check = 1;
		return false;
	} else {
		$(document).on('blur', '#mem_phone', function(){ 
		let user_phone = $("#mem_phone").val();
		if( isRun ){
			//alert("처리중");
			return false;
		}
		isRun = true;
		
		$.ajax({
			url : "/PhoneCheck.do",
			type : "POST",
			data : { mem_phone : user_phone },
			datatype : "JSON",
			success : function(result){
				isRun = false;
				if(result == 1){	
					$("#checkPhone").html("사용 가능한 전화번호입니다");
					$("#checkPhone").attr('color', 'blue');
					phonecheck = 0;
					check = 0;
					return true;
				}else{
					$("#checkPhone").html("이미 사용중인 전화번호입니다");
					$("#checkPhone").attr('color', 'red');
					phonecheck = 1;
					check = 1;
					return false;
				}
			},
			error : function(){
					isRun = false;
					alert("서버요청실패");
			} 
		});
	  });
	}
}



function birthCheck(){
		var mem_birth = document.getElementById("mem_birth").value;
		mem_birth = mem_birth.trim();
		var exp = /\d{2}([0]\d|[1][0-2])([0][1-9]|[1-2]\d|[3][0-1])[-]*[1-4]/;
		
		if(exp == ""){
			$("#checkEmail").html("생년월일을 입력해 주세요");
			$("#checkEmail").attr('color', 'green');
		}else {
			if(exp.test(mem_birth) == false){
				$("#checkBirth").html("잘못된 생년월일 형식입니다");
				$("#checkBirth").attr('color', 'red');
				document.getElementById("mem_birth").focus();
				birth_check = 1;
				check = 1;
				return false;
			}else {
				$("#checkBirth").html("올바른 형식입니다");
				$("#checkBirth").attr('color', 'blue');
				birth_check = 0;
				check = 0;
				return true;
			}
		}
}//birthCheck()

function pwCheck(){
	var mem_pw=document.getElementById("mem_pw").value;
	var re_pw = document.getElementById("re_pw").value;

	mem_pw=mem_pw.trim();	
	re_pw = re_pw.trim();
	
	
	if(mem_pw.length  < 4 || mem_pw == null){
		$("#checkPw").html("비밀번호를 4자리 이상 입력해주세요");
		$("#checkPw").attr('color', 'green');
		document.getElementById("mem_pw").focus();
		return false;
		
	}else {
		if(re_pw != mem_pw){
			$("#checkPw").html("비밀번호가 일치하지 않습니다");
			$("#checkPw").attr('color', 'red');
			document.getElementById("mem_pw").focus();
			pw_check = 1;
			check = 1;
			return false;
			
		}else {
			$("#checkPw").html("비밀번호가 일치합니다");
			$("#checkPw").attr('color', 'blue');
			pw_check = 0;
			check = 0;
			return true;
		}
	}	
}

function newpwCheck(){
	var mem_pw=document.getElementById("mem_pw").value;
	var newre_pw = document.getElementById("newre_pw").value;
	var new_pw = document.getElementById("new_pw").value;
	mem_pw=mem_pw.trim();	
	newre_pw = newre_pw.trim();
	new_pw = new_pw.trim();
		 
	if(mem_pw == new_pw){
		alert("기존 비밀번호와 동일합니다");
		document.getElementById("new_pw").focus;
		newpw_check = 1;
		check = 1;
		return false;
	}else {
		if(newre_pw != new_pw){
			$("#checknPw").html("비밀번호가 일치하지 않습니다");
			$("#checknPw").attr('color', 'red');
			newpw_check = 1;
			check = 1;
		
			return false;
		}else {
			$("#checknPw").html("비밀번호가 일치합니다");
			$("#checknPw").attr('color', 'blue');
			newpw_check = 0;
			check = 0;
			return true;
		}
	}
}



function findId(){
	var mem_phone= document.getElementById("mem_phone").value;
	var mem_email =document.getElementById("mememail").value;
	mem_phone = mem_phone.trim();
	mem_email = mem_email.trim();
	var p_check = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	 
	if(p_check == "" || p_check.test(mem_phone) == false){
		//alert("전화번호 형식을 확인해주세요");
		$("#checkPhone").html("전화번호 형식을 확인해주세요");
		$("#checkPhone").attr('color', 'green');
		phonecheck = 1;
		return false;
	}else if(exptext = "" || exptext.test(mem_email) == false){
		//alert("이메일 형식을 확인해주세요");
	   $("#checkEmail").html("이메일 형식을 확인해주세요");
	   $("#checkEmail").attr('color', 'green');
		email_check = 1;
		return false;
	}else {
		return true;
	}
}

function findPw(){
	var mem_id = document.getElementById("mem_id").value;
	var mem_phone= document.getElementById("mem_phone").value;
	var mem_email = documnet.getelementById("mem_email.").vula
	
	mem_id = mem_id.trim();
	mem_email=mem_email.trim();
	mem_phone = mem_phone.trim();
	
	var p_check = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	
	if(p_check == "" || p_check.test(idphone) == false){
		//alert("전화번호 형식을 확인해주세요");
		phonecheck = 1;
		return false;
	}else if(exptext = "" || exptext.test(idemail) == false){
		//alert("이메일형식이 올바르지 않습니다.");
		email_check = 1;
		return false;
	}else if(id.length < 3 || id == null){
		alert("아이디는 4글자 이상 입력해 주세요.");
	}else {
		return true;
	}
}





function FormCheck(){
	//alert(check);
	
	if(id_check != 0 && id_check != 1){
		//alert("아이디 중복확인이 필요합니다");
		document.getElementById("mem_id").focus;
		return false;
	}else {
		id_check = mem_id.idCheck();
		return true;
	}

	
	if(check == 1){
		return false;
	}else {
		return true;
	}
	
}



function updateFormCheck(){
	//alert(check);

	
	if(check == 1){
		return false;
	}else {
		return true;
	}
	
}

