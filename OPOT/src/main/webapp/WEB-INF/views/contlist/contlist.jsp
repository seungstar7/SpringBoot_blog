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
	
	<div class="container" id="searchField">	
		<table class="table" id="searchFieldTb">
            <tr>         
               <th class="searchFiedlTh">연도별</th>
               <td>
                 <select class="form-control" id="mdate" name="mdate">
                   <option id="mdatedefault" value="">연도 선택</option>
                   <option value="2020" id="mdate20">2020년대</option>
                   <option value="2010" id="mdate10">2010년대</option>
                   <option value="2000" id="mdate00">2000년대</option>
                   <option value="1990" id="mdate90">1990년대</option>
                   <option value="1980" id="mdate80">1980년대</option>
                   <option value="1970" id="mdate70">1970년대</option>
                 </select>
               </td>                        
               <th class="searchFiedlTh">장르별</th>
               <td>
                 <select class="form-control" id="gerne" name="gerne">
                   <option id="gernedefault" value="">장르 선택</option>
                   		<c:forEach var="dto" items="${ gernes }">
                   			<option value="${ dto.key_code }" id="key_code">${ dto.key_name }</option>
                   		</c:forEach>
                 </select>
               </td>
               <th class="searchFiedlTh">별점별</th>
               <td>
                 <select class="form-control" id="mrate" name="mrate">
                   <option id="mratedefault" value="">별점 선택</option>
                   <option value="5">★★★★★</option>
                   <option value="4">★★★★☆</option>
                   <option value="3">★★★☆☆</option>
                   <option value="2">★★☆☆☆</option>
                   <option value="1">★☆☆☆☆</option>
                   <option value="0">☆☆☆☆☆</option>
                 </select>
               </td>
               <th class="searchFiedlTh"><button class="btn btn-danger" onclick="location.reload()">검색초기화</button></th>                
            </tr>
            <tr>
	            <td style="text-align:right;" colspan="2">
	            <select class="form-control" id="sorting" name="sorting">
	               <option value="">정렬 방식</option>
	               <option value="cri_like_low" id="cri_like_low">좋아요 낮은 순</option>
	               <option value="cri_like_high" id="cri_like_high">좋아요 높은 순</option>
	               <option value="mdate_low" id="mdate_low">제작연도 오름차 순</option>
	               <option value="mdate_high" id="mdate_high">제작연도 내림차 순</option>
	            </select>
	            </td>
	            <td colspan="5"></td>
	         </tr>
            <tr>
            	<td colspan="7"></td>
            </tr>
         </table>

         		
	</div>
		
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
		<input type="hidden" id="no" value="9">
		<input type="hidden" id="ottbtnPushed" value="N">
	</div>
    
</div>

	<script>	
	
	function searchParam(key) {
		  return new URLSearchParams(location.search).get(key);
	};
	
	
	$(".ott_search_btn").click(function(){
		$('#ottbtnPushed').attr('value', $(this).val());
		$('#nowPage').attr('value', 1);
		$('#no').attr('value', 1);
		var num=parseInt($("#no").val());
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
				mdate : $('#mdate').children("option:selected").val(),
				gerne : $('#gerne').children("option:selected").val(),
				mrate : $('#mrate').children("option:selected").val(),	
                sort : $('#sorting').children("option:selected").val(),                         
        	},		
            success:function(data){//success callback함수                
                $(".contents").empty();
                $(".searchedott").empty();
                $(".searchedott").append(msg+" : 검색결과");
				$.each(data,function(index, value) {	      			
					createContents(num, value);
					num++;				      
				 })				 
				 $('#no').attr('value', num);
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
		
		//alert($('#mrate').children("option:selected").val());
		//alert($('#nowPage').val());  
		var num=parseInt($("#no").val());		
		$.ajax({
			url: "morecontents.do",
			type:"get",   
			data: {
				nowPage : $('#nowPage').val(),
				searchkey : searchParam('searchkey'),
				key_code : searchParam('key_code'),
				pno : searchParam('pno'),
				ott : $('#ottbtnPushed').val(),
				mdate : $('#mdate').children("option:selected").val(),
				gerne : $('#gerne').children("option:selected").val(),
				mrate : $('#mrate').children("option:selected").val(),	
                sort : $('#sorting').children("option:selected").val(),                         
			},   
			success: function(data){				
				$.each(data,function(index, value) {	      			
					createContents(num, value);
					num++;				      
				 })				 
				 $('#no').attr('value', num);				   
			},
            error:function(error){
				alert("에러: " + error);
            }	
		});		
	}//loadNext() end
	
	
 
    
    $("select[name=mdate]").change(function(){
    	$('#mrate').children("option:selected").prop("selected", false); 	
    	$('#gerne').children("option:selected").prop("selected", false);
    	var col= "mdate";
    	var word= $(this).val();
    	var msg = $(this).val()+"년대";
    	searchResult(msg, col, word);
    })        
    
    
    $("select[name=gerne]").change(function(){
    	$('#mdate').children("option:selected").prop("selected", false);
    	$('#mrate').children("option:selected").prop("selected", false); 	
    	var col= "key_code";
    	var word= $(this).val();
    	var msg = $(this).children("option:selected").text();
    	searchResult(msg, col, word);
    })
    
    
    $("select[name=mrate]").change(function(){
    	
    	
    	$('#mdate').children("option:selected").prop("selected", false);
    	$('#gerne').children("option:selected").prop("selected", false);
    	var col= "mrate";
    	var word= $(this).val();
    	var msg = $(this).children("option:selected").text();
    	searchResult(msg, col, word);
    })    
    
 	
    function searchResult(msg, col, word){
        
		$('#nowPage').attr('value', 1);
  		$('#no').attr('value', 1);
		var num=parseInt($("#no").val());                 
	
        //alert(msg);
        $.ajax({
                url:"searchfield.do",  //요청명령어 
                type:"get",        //get방식
                data : {
                	   col : col,
                	   word : word,
                   	   nowPage : $('#nowPage').val(),
                       ott : $('#ottbtnPushed').val(),           
                       sort : $('#sorting').children("option:selected").val(),
                },      
                success:function(data){//success callback함수
                     $(".contents").empty();
                     $(".searchedott").empty();
                     $(".searchedott").append(msg+" : 검색결과");
     				 $.each(data,function(index, value) {     			
    					createContents(num, value);
    					num++;				      
    				 })				 
    				 $('#no').attr('value', num);	
                 },
                 error:function(error){
                 alert("에러: " + error);
              }
		});//ajax() end
	}

	
    $("select[name=sorting]").change(function(){
        
        $('#nowPage').attr('value', 1);
        $('#no').attr('value', 1);
        var num=parseInt($("#no").val());
        var msg= $(this).children("option:selected").text();
      $.ajax({
                 url:"sorting.do",  //요청명령어 
                 type:"get",         //get방식
                 data : {
                     sort : $(this).val(),
                     nowPage : $('#nowPage').val(),
                     ott : $('#ottbtnPushed').val(),
                     searchkey : searchParam('searchkey'),
                     key_code : searchParam('key_code'),
                     pno : searchParam('pno'),            
                     mdate : $('#mdate').children("option:selected").val(),
                     gerne : $('#gerne').children("option:selected").val(),
                     mrate : $('#mrate').children("option:selected").val(),                         
                },      
                success: function(data){
                   $(".contents").empty();
                   $(".searchsort").empty();
                   $(".searchsort").append(msg);
                  
                   
                    $.each(data, function(index, value) {                  
                       createContents(num, value);
                       num++;                  
                     })             
                     
                     $('#no').attr('value', num);   
                   
     
                 },
                    error:function(error){
                    alert("에러: " + error);
                    }   
      });//ajax() end
    })

    
    	
    function createContents(num, value){
   	
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
    	
    }//createContents() end	
    	
    
    if(document.URL=="http://localhost:9090/contlist/contlist.do"){//웹호스팅 시에 URL 변경해야함!
    	$('#searchField').show();
    }else{
    	$('#searchField').hide();
    }
    
				
	</script>

	<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>