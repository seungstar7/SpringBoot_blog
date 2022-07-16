<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<%@ include file="../header.jsp"%>

<!-- 본문시작 -->
<div>	
	
		<button id="like" name="like" class="btn">테스트</button>
		
		<form method="post">
		<label>이름을 입력하세요</label>
		<input type="text" name="username" class="user">   //이름을 입력하는 text 상자
		<input type="button" id="submit" value="입력">   //버튼을 누르면 스크립트 실행
		</form>
		
	<div id="message"></div>
		
</div>




<script>
$(document).ready(function(){
	$('#submit').click(function(){   //s버튼을 클릭하였을 때
		let sendData = "username="+$('input[name=username]').val();   //폼의 이름 값을 변수 안에 담아줌
		$.ajax({
			type:'post',   //post 방식으로 전송
			url:'test.jsp',   //데이터를 주고받을 파일 주소
			data:sendData,   //위의 변수에 담긴 데이터를 전송해준다.
			dataType:'html',   //html 파일 형식으로 값을 담아온다.
			success : function(data){   //파일 주고받기가 성공했을 경우. data 변수 안에 값을 담아온다.
				$('#message').html(data);  //현재 화면 위 id="message" 영역 안에 data안에 담긴 html 코드를 넣어준다. 
			}
		});
	});
});




</script>





<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>