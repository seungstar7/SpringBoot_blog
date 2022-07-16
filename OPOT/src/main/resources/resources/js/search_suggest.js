var checkFirst = loopSend = false;
var lastKeyword = "";
var keyword="";

function search(val){
    if(checkFirst == false){
        //alert(val);
     	$('#suggest').empty();
        keyword=val;
        setTimeout("sendKeyword()", 1000);          
        loopSend = true;
    }
}


function sendKeyword(){
    if(loopSend == false) return;
	 	//alert(keyword);	 
	if(keyword !== ""){
              
	    $.ajax({
            url:"moviesuggest.do",
            type:"post",
        	data : {
				keyword : keyword,			
        	},		
            success:function(data){//success callback함수
 				
 				$.each(data,function(index, value) {
 					
 					var mtitle=value.mtitle;
 					
 					//alert(value);
 					//alert(index);
 					//alert(value.mtitle);
 					//alert(value.mcode);
 					//$('#suggestList').append(value.mtitle);
 					//$('#suggest').append('<input type="button" value="'+value.mtitle+'" class="searchedcont">');
 					//$('#suggest').append('<span>'+value.mcode+'</span>');
 					$('#suggest').append('<a href="javascript:select('+mtitle+');">'+value.mtitle+'</a>');
 					
 				})
 				
            },
            error:function(error){
				alert("에러: " + error);
			}
   		});//ajax() end             				

	}
}


function select(reData){
     frm.mcode1.value = reData;
     loopSend = checkFirst = false;
     $('#suggest').empty();
}



   $('#content').autocomplete({
        source : function(request, response) {
            $.ajax({
                  url : "moviesuggest.do"
                , type : "POST"
                , data : { keyword : $('#content').val() } // 검색 키워드
                , dataType : "JSON"
                , success : function(data){ // 성공
                    response(
						
                        $.map(data, function(item) {
                            return {
                                  label : item    //목록에 표시되는 값
                                , value : item    //선택 시 input창에 표시되는 값
                            };
                        })
                    );//response
                }
                ,
                error : function(){ //실패	
                    alert("통신 실패");
                }
            });
        }
        , minLength : 1    
        , autoFocus : false    
        , select : function(event, ui) {
            console.log(ui);//사용자가 오토컴플릿이 만들어준 목록에서 선택을 하면 반환되는 객체
            console.log(ui.item.label);
            console.log(ui.item.value);
        }
        , focus : function(event, ui) {
            return false;
        }
        , close : function(event) {
            console.log(event);
        }
    });
