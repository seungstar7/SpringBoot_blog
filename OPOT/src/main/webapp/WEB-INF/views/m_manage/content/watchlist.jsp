<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

<%@ include file="../../header.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/chart.js@3.8.0/dist/chart.min.js"></script>


<!-- 본문시작 watchlist.jsp -->
   
   	<div class="pagetitle">
		<br>
		<span><img src="/images/pot_icon.png" alt="OPOT" width="50px"></span>
		<span><strong> 내가 본 작품 </strong></span>
	
	</div>
  	

	
  <div class="container-fluid text-center">	
	<div class="chart_wrap">
		<canvas id="myChart"></canvas>
	</div>
		
	<table class="table table-hover">
		<tr>
			<th>영화 코드</th>
			<th>영화 이름</th>
			<th>본 날짜</th>
			<th>시청 번호</th>
		</tr>

		<c:forEach var="dto" items="${watchlist}"> 	
			<tr>
				<td>${dto.mcode }</td>
				<td>
					<img src="../../storage/${ dto.mthum }" alt="${ dto.mtitle }" width="50px">
					<a href="<%=request.getContextPath()%>/contlist/contlistread.do?mcode=${ dto.mcode }">${ dto.mtitle }</a>
				</td>				
				<td>${dto.watch_time }</td>
				<td>${dto.watch_reg }</td>
			</tr>	
		</c:forEach>
		
	</table>

  </div>	




<script>

var labels = [];
<c:forEach var="list" items="${ key_names }">
	labels.push("${ list }");
</c:forEach>

var datas = [];
<c:forEach var="list" items="${ ratios }">
	datas.push("${ list }");
</c:forEach>

// 차트를 그럴 영역을 dom요소로 가져온다.
var chartArea = document.getElementById('myChart').getContext('2d');
// 차트를 생성한다. 
var myChart = new Chart(chartArea, {
    // ①차트의 종류(String)
    type: 'doughnut',
    // ②차트의 데이터(Object)
    data: {
    	labels: labels,
    	  datasets: [
    	    {
    	      label: 'Dataset 1',
    	      data: datas,
    	      backgroundColor: ['#D95252', '#F29C50', '#9AD914', '#7897BF', '#D955C3'],
              borderColor: '#F2F2F2',
              borderWidth: 3,
    	    }
    	  ]
    },
    // ⑩차트의 설정(Object)
    options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'top',
          },
          title: {
            display: true,
            text: '내가 좋아하는 장르는? (단위: %)'
          }
        }
      },
});
</script>

<!-- 본문끝 -->
<%@ include file="../../footer.jsp"%>