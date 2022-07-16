<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html>
<html lang="en">
<head>
  <title>OPOT</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="/css/layout.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script src="../js/jquery-3.6.0.min.js"></script>
  <script src="../js/jquery.cookie.js"></script>
  <script src="../js/moment-with-locales.js"></script>

<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<!-- 메인카테고리 시작 -->
<nav class="navbar navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
	      <a href="<%=request.getContextPath()%>/home.do">
	          <img src="/images/logo.png" alt="HOME" width="180px">
	      </a>
    </div>
    <div>
      <ul class="nav navbar-nav navbar-right">
	      	<li><a href="<%=request.getContextPath()%>/contlist/contlist.do">컨텐츠</a></li>
	        <li><a href="<%=request.getContextPath()%>/party/partyadd.do">파티매칭</a></li>
	        <c:choose>
	          <c:when test="${ s_mem_id==null || s_mem_pw==null || s_mem_lv==null }">
	            <li><a href="<%=request.getContextPath()%>/login.do">로그인</a></li>	            
	          </c:when>
	          <c:when test="${ s_mem_lv=='A' }">
	            <li><a href="<%=request.getContextPath()%>/webmaster/webmaster.do">관리자페이지</a></li>	            
	          </c:when>
	          <c:otherwise>
	            <li><a href="<%=request.getContextPath()%>/m_manage/mypage.do">마이페이지</a></li>
	          </c:otherwise>
	        </c:choose>
	  </ul>
    </div>
  </div>
</nav>
<!-- 메인카테고리 끝 -->
<div id="wrap" class="container-fluid bg-1 text-center">

<!-- 본문 시작 -->

	본문 내용 여기에 입력




<!-- 본문 끝 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

</div>
	<!-- Footer -->
	<footer >

	  <div class="container-fluid bg-3 text-center">
	    <a href="<%=request.getContextPath()%>/notice/notice.do">공지사항</a> &nbsp;&nbsp;
	    <a href="">문의사항</a>
	  </div>
	  
	  <div class="container-fluid bg-4 text-center">
	  	  <a href="<%=request.getContextPath()%>/home.do">
	          <img src="/images/logo_white_grey.png" alt="HOME" width="100px">
	      </a>
		  <p>Copyright &copy; OPOT</p> 
		  <p>SOLDESK 1조</p>
		  <p>조원들 Contact Info</p>
	  </div>
	  

	</footer>


</body>
</html>
