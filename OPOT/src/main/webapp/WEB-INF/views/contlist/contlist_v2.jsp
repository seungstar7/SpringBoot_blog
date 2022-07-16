<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    

	<!-- 메인카테고리 끝 -->

	<!-- 본문 시작 -->
<div id="cont_list" class="container-fluid text-center">

	<div class="pagetitle">
		<br>
		<span><strong> 👀 컨텐츠들을 구경해보세요! 👀 </strong></span>
		<h5 class="searchkey">${ msg }</h5>
		<h5 class="searchedott"></h5>

		<br>
	</div>
			
	<div class="ott_search">
		<button class="ott_search_btn" id="all_btn" name="all_btn" value="all"><img src="../../images/icon_all_search.png"></button>						
		<button class="ott_search_btn" id="netflix_btn" name="netflix_btn" value="netflix"><img src="../../images/icon_netflix_search.png"></button>
		<button class="ott_search_btn" id="tving_btn" name="tving_btn" value="tving"><img src="../../images/icon_tving_search.png"></button>
		<button class="ott_search_btn" id="watcha_btn" name="watcha_btn" value="watcha"><img src="../../images/icon_watcha_search.png"></button>
		<button class="ott_search_btn" id="disney_btn" name="disney_btn" value="disney"><img src="../../images/icon_disney_search.png"></button>
		<p id="panel"></p>
	</div>
		
		<table class="table">
            <tr>         
               <th>연도별</th>
               <td>
                 <select class="form-control" id="mdate" name="mdate">
                         <option value="1980" id="mdate80">1980</option>
                         <option value="1990" id="mdate90">1990</option>
                         <option value="2000" id="mdate00">2000</option>
                         <option value="2010" id="mdate10">2010</option>
                         <option value="2020" id="mdate20">2020</option>
                  </select>
               </td>            
            </tr>
         </table>		
		
		<div class="contents">
			<c:set var="no" value="1"></c:set>
			<c:forEach var="dto" items="${list}">
				<div class="col-lg-3 col-md-4 col-sm-6">
					<div class="thumb" id="content${ no }">
					  
						<a href="<%=request.getContextPath()%>/contlist/contlistread.do?mcode=${ dto.mcode }">
							<img src="../../storage/${dto.mthum}" alt="movie"
							width="300px">
						</a>
						<div class="mtitle"><strong>${dto.mtitle}</strong></div>
						<div class='stars'>
				        	<c:forEach begin="1" end="${ dto.mrate }">★</c:forEach><c:forEach begin="${ dto.mrate+1 }" end="5">☆</c:forEach>
				        	${ dto.mrate }
				        </div>
						
					
						<c:if test="${dto.netflix eq 'O' }" >
							<img src="../../images/icon_netflix.png" width="50px">
						</c:if>
						<c:if test="${dto.tving eq 'O'  }" >
							<img src="../../images/icon_tving.png" width="50px">
						</c:if>
						<c:if test="${dto.watcha eq 'O'  }" >
							<img src="../../images/icon_watcha.png" width="50px">
						</c:if>
						<c:if test="${dto.disney eq 'O'  }" >
							<img src="../../images/icon_disney.png" width="50px">
						</c:if>
					</div>
				</div>
				<span class="hide">${ no=no+1 }</span>
 			</c:forEach>
 			
 			 					
		</div>	
			
	<div class="pageInfo" id="pageInfo">
		<input type="hidden" id="nowPage" value="1">
		<input type="hidden" id="no" value="1">
	</div>
    
</div>

	<script>	
	
	function searchParam(key) {
		  return new URLSearchParams(location.search).get(key);
	};
	
	$(".ott_search_btn").click(function(){
		
		$('#nowPage').attr('value', 1);
		//alert($(this).val());
		var msg = $(this).val();
		
	    $.ajax({
            url:"ottsearch.do",  //요청명령어 
            type:"get",        //get방식
        	data : {
        		nowPage : $('#nowPage').val(),
				ott : $(this).val(),
				searchkey : searchParam('searchkey'),
				key_code : searchParam('key_code'),
				pno : searchParam('pno'),				
        	},		
            success:function(data){//success callback함수
                
                $(".thumb").empty();
                $(".searchedott").empty();
                $(".searchedott").append(msg+" : 검색결과");

   			    var no=0;
            	$.each(data,function(index, value) { // 값이 여러개 일 때는 반복문 사용
	      			
	      			var stars="";
	                for(i=1; i<=value.mrate; i++){ stars+="★"; }
	                for(i=value.mrate+1; i<=5; i++){ stars+="☆"; }
            	
            	
            		netfliximg=$('<img>', {
            			'src' : '../../images/icon_netflix.png',
            			'width' : '50px',
            		});
            		
            		tvingimg=$('<img>', {
            			'src' : '../../images/icon_tving.png',
            			'width' : '50px',
            		});
            		
            		watchaimg=$('<img>', {
            			'src' : '../../images/icon_watcha.png',
            			'width' : '50px',
            		});
            		
            		disneyimg=$('<img>', {
            			'src' : '../../images/icon_disney.png',
            			'width' : '50px',
            		});

      			   	var identifier = ".thumb";
            		
          			$(identifier).eq(no).append("<input type='image' id='"+value.mcode+"' name='"+value.mcode+"' src='../../storage/"+value.mthum+"' alt='movie' width='300px' onclick=''>");

	      			$(identifier).eq(no).append("<div class='mtitle'><strong>"+value.mtitle+"</strong></div>");
	      			$(identifier).eq(no).append("<div class='stars'>"+stars+value.mrate+"</div>");
	      			if(value.netflix=="O"){$(identifier).eq(no).append(netfliximg);}
	      			if(value.tving=="O"){$(identifier).eq(no).append(tvingimg);}
	      			if(value.watcha=="O"){$(identifier).eq(no).append(watchaimg);}
	      			if(value.disney=="O"){$(identifier).eq(no).append(disneyimg);}
					
	      			$("input[name="+value.mcode+"]").attr('onclick', 'location.href="<%=request.getContextPath()%>/contlist/contlistread.do?mcode='+value.mcode+'"');
	      			
					no++;
					//alert(no);
					
			
                 })
            },
            error:function(error){
				alert("에러: " + error);
			}
   		});//ajax() end
			
	});	
	
	
	$(window).scroll(function(){  
		if($(document).height() <= $(window).scrollTop() + $(window).height()){    
			
			var nowPage=parseInt($('#nowPage').val());
			var newNowPage = nowPage+1;				
			$('#nowPage').attr('value', newNowPage);
		
			loadNext();  
		}
	});
	
	function loadNext(){  
		//alert("스크롤 이벤트");
		//alert($('#nowPage').val());
		var num=parseInt($("#no").val());
		//alert(num);
		/*
		for(i=num; i<=num+8; i++){
			$(".contents").append("<div class='col-lg-3 col-md-4 col-sm-6'><div class='thumb' id='content"+i+"'>")
		}
		*/
		
		$.ajax({
			url: "morecontents.do",     
			type:"get",   
			data: {
				nowPage : $('#nowPage').val(),
				searchkey : searchParam('searchkey'),
				key_code : searchParam('key_code'),
				pno : searchParam('pno'),
        	},   
			success: function(data){
				
				$.each(data,function(index, value) {
	      			
					$(".contents").append("<div class='col-lg-3 col-md-4 col-sm-6'><div class='thumb' id='content"+num+"'>")
					
					var stars="";
	                for(i=1; i<=value.mrate; i++){ stars+="★"; }
	                for(i=value.mrate+1; i<=5; i++){ stars+="☆"; }
            	
            		netfliximg=$('<img>', {
            			'src' : '../../images/icon_netflix.png',
            			'width' : '50px',
            		});
            		
            		tvingimg=$('<img>', {
            			'src' : '../../images/icon_tving.png',
            			'width' : '50px',
            		});
            		
            		watchaimg=$('<img>', {
            			'src' : '../../images/icon_watcha.png',
            			'width' : '50px',
            		});
            		
            		disneyimg=$('<img>', {
            			'src' : '../../images/icon_disney.png',
            			'width' : '50px',
            		});

      			   	var identifier = "#content"+num;
            		
          			$(identifier).append("<input type='image' id='"+value.mcode+"' name='"+value.mcode+"' src='../../storage/"+value.mthum+"' alt='movie' width='300px' onclick=''>");

	      			$(identifier).append("<div class='mtitle'><strong>"+value.mtitle+"</strong></div>");
	      			$(identifier).append("<div class='stars'>"+stars+value.mrate+"</div>");
	      			if(value.netflix=="O"){$(identifier).append(netfliximg);}
	      			if(value.tving=="O"){$(identifier).append(tvingimg);}
	      			if(value.watcha=="O"){$(identifier).append(watchaimg);}
	      			if(value.disney=="O"){$(identifier).append(disneyimg);}
					
	      			$("input[name="+value.mcode+"]").attr('onclick', 'location.href="<%=request.getContextPath()%>/contlist/contlistread.do?mcode='+value.mcode+'"');
	      			
					num++;
					//alert(no);
				      
				 })
				 
				 $('#no').attr('value', num);
				   
			},
            error:function(error){
				alert("에러: " + error);
            }	
		});
		
	}


    $("select[name=mdate]").change(function(){
        
        // alert( $(this).val());
         
          $('#nowPage').attr('value', 1);
           //alert($(this).val());
           var msg = $(this).val();
           
            $.ajax({
                 url:"mdateSearches.do",  //요청명령어 
                 type:"get",        //get방식
                data : {
                   mdate : $(this).val(),
                   nowPage : $('#nowPage').val(),
                   ott : $(this).val(),
                   searchkey : searchParam('searchkey'),
                   key_code : searchParam('key_code'),
                   pno : searchParam('pno'),            
                },      
                 success:function(data){//success callback함수
                     
                     $(".thumb").empty();
                     $(".searchedott").empty();
                     $(".searchedott").append(msg+"년대 : 검색결과");

                     var no=0;
                    $.each(data,function(index, value) { // 값이 여러개 일 때는 반복문 사용
                       
                       var stars="";
                        for(i=1; i<=value.mrate; i++){ stars+="★"; }
                        for(i=value.mrate+1; i<=5; i++){ stars+="☆"; }
                    
                    
                       netfliximg=$('<img>', {
                          'src' : '../../images/icon_netflix.png',
                          'width' : '50px',
                       });
                       
                       tvingimg=$('<img>', {
                          'src' : '../../images/icon_tving.png',
                          'width' : '50px',
                       });
                       
                       watchaimg=$('<img>', {
                          'src' : '../../images/icon_watcha.png',
                          'width' : '50px',
                       });
                       
                       disneyimg=$('<img>', {
                          'src' : '../../images/icon_disney.png',
                          'width' : '50px',
                       });

                          var identifier = ".thumb";
                       
                        $(identifier).eq(no).append("<input type='image' id='"+value.mcode+"' name='"+value.mcode+"' src='../../storage/"+value.mthum+"' alt='movie' width='300px' onclick=''>");

                       $(identifier).eq(no).append("<div class='mtitle'><strong>"+value.mtitle+"</strong></div>");
                       $(identifier).eq(no).append("<div class='stars'>"+stars+value.mrate+"</div>");
                       if(value.netflix=="O"){$(identifier).eq(no).append(netfliximg);}
                       if(value.tving=="O"){$(identifier).eq(no).append(tvingimg);}
                       if(value.watcha=="O"){$(identifier).eq(no).append(watchaimg);}
                       if(value.disney=="O"){$(identifier).eq(no).append(disneyimg);}
                    
                       $("input[name="+value.mcode+"]").attr('onclick', 'location.href="<%=request.getContextPath()%>/contlist/contlistread.do?mcode='+value.mcode+'"');
                       
                    no++;
                    //alert(no);
                    
              
                      })
                 },
                 error:function(error){
                 alert("에러: " + error);
              }
              });//ajax() end
    	})
	
		
	</script>

	<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>