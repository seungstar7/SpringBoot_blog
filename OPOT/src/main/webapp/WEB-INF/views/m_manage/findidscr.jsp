<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="./js/member_action.js" ></script>
	<meta charset="UTF-8">
	<title>아이디/비밀번호 찾기</title>
</head>
<body>
	<h3>아이디 찾기</h3>
	 전화번호 : <input type="text" id="idphone" name="findid_phone">	
	 	
	 이메일 : <input type="text" id="idemail" name="findid_email">
	 
	 <button type="submit" id="findidBtn" onclick="findId()">아이디 찾기</button>
	 <button type="button" id="cancle" onclick="javascript:history.back()">취소</button>
</body>
</html>