<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script src="../js/member_action.js"></script>
	<meta charset="UTF-8">
	<title>아이디/비밀번호 찾기</title>
</head>
<body>
	<h3>비밀번호 찾기</h3>
	 아이디 : <input type="text" id="findpw_id" name="findpw_id"> 
	
	 전화번호 : <input type="text" id="findid_phone" name="findpw_phone">	
	 	
	 이메일 : <input type="text" id="findid_email" name="findpw_email">
	 
	 <button type="submit" id="findidBtn" onclick="findPw()">비밀번호 찾기</button>
	 <button type="button" id="cancle" onclick="javascript:history.back()">취소</button>
</body>
</html>