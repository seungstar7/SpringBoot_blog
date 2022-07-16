package kr.co.finalproject.contlist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.finalproject.review.ReviewDAO;
import kr.co.finalproject.review.ReviewDTO;
import kr.co.finalproject.search.SearchKeyDAO;
import kr.co.finalproject.search.SearchKeyDTO;
import net.utility.Utility;

@Controller
public class ContlistController {

   ContlistDAO dao = null;
   ReviewDAO revdao = null;
   SearchKeyDAO skdao = null;
   WatchListDAO watchdao = null;
   PeopleDAO pdao=null;

   
   public ContlistController() {
      dao = new ContlistDAO();
      revdao = new ReviewDAO();
      skdao = new SearchKeyDAO();
      watchdao = new WatchListDAO();
      System.out.println("-----ContlistCont() 객체 생성");
   }// constructor end

   @RequestMapping("/contlist/contlist.do")
   public ModelAndView contlist() {
	      ModelAndView mav = new ModelAndView();

	      ArrayList<ContlistDTO> list = null;
	      
	      int nowPage=1;
	      int recordPerPage=8;
	      String col ="";
	      String word="";
	      String sort="";
	      
	      list = dao.list(col, word, sort, nowPage, recordPerPage);
	      
	      mav.setViewName("contlist/contlist");
	      mav.addObject("list", list);
	      mav.addObject("nowPage", nowPage);
	      mav.addObject("gernes", skdao.readGene());

	      return mav;
   }
   
   
    @ResponseBody	
	@RequestMapping({"contlist/morecontents.do", "morecontents.do"})
	private ArrayList<ContlistDTO> morecontents(@RequestParam Map<String, Object> map) {
		
       ArrayList<ContlistDTO> list = null;
   	
		try {
			
			String strNowPage= (String)map.get("nowPage");
			String searchkey= (String)map.get("searchkey"); //홈 화면에서 입력된 검색어
			String key_code= (String)map.get("key_code");   //키워드 검색
			String pno= (String)map.get("pno");				//인물 검색
			String ott= (String)map.get("ott");				//ott 검색
			String mdate= (String)map.get("mdate");			//개봉일 검색
			String gerne= (String)map.get("gerne");			//장르 검색
			String mrate= (String)map.get("mrate"); 		//별점검색
            String sort= (String)map.get("sort");
          
            if(sort==null) {
        	  sort="";
            }
	          
			int nowPage = Integer.parseInt(strNowPage);
		    int recordPerPage=8;
			
	        String col ="";
	        String word="";
		    
			if(!(searchkey.equals(""))) {		        
				pno=dao.readPno(searchkey);				
			}else if(!(key_code.equals(""))) {				
				col="key_code";
				word=key_code;
			}else if(!(pno.equals(""))) {				
				col="pno";
				word=pno;
			}else if(!(mdate.equals(""))) {				
				col="mdate";
				word=mdate;
			}else if(!(gerne.equals(""))) {				
				col="key_code";
				word=gerne;
			}else if(!(mrate.equals(""))) {	
				//System.out.println(mrate);
				col="mrate";
				word=mrate;
			}
			
			list=dao.list(col, word, sort, nowPage, recordPerPage);
			
			//System.out.println(ott);
			
			if(!(ott.equals("N"))) {//ott버튼을 누른 상태일 때
									
				if(!(searchkey.equals(""))) {
				      pno=dao.readPno(searchkey);
				      if(pno==null) { pno=""; }
				      list = dao.mainottsearch(ott, searchkey, pno, nowPage, recordPerPage);
				}else {
					  list = dao.ottRead(ott, col, word, sort, nowPage, recordPerPage);
				}				

			}
					    
		}catch (Exception e) {
			System.out.println("응답실패: " + e);
		}
		
		return list;
		
	}//morecontents() end
   
   
   

   @RequestMapping("contlist/contlistread.do")
   public ModelAndView contlistread(ContlistDTO dto, ReviewDTO dto2, SearchKeyDTO dto3, PeopleDTO pdto, HttpServletRequest req) {
      ModelAndView mav = new ModelAndView();
      int mcode = Integer.parseInt(req.getParameter("mcode"));
      
      dto=dao.contlistread(mcode);
      String key_code = dto.getKey_code();
      HttpSession session = req.getSession();
      
      ArrayList<SearchKeyDTO> keylist=new ArrayList<>() ;

      StringTokenizer kc = new StringTokenizer(key_code, " || ");      
      while(kc.hasMoreTokens()) { //토큰할 문자가 있는지?          
    	  keylist.add(skdao.SearchKey(kc.nextToken()));    	  
      }
      
      if(session.getAttribute("s_mem_id")!=null && session.getAttribute("s_mem_lv")!=null && session.getAttribute("s_mem_pw")!=null) {

			String mem_lv=session.getAttribute("s_mem_lv").toString();
			mav.addObject("mem_lv", mem_lv);    	  
      }
      
      pdao=new PeopleDAO();
      
      String directors = dto.getDirector();
      String actors = dto.getActor();
      ArrayList<PeopleDTO> directorlist = new ArrayList<>();
      ArrayList<PeopleDTO> actorlist = new ArrayList<>();
    
      
      StringTokenizer dirSt = new StringTokenizer(directors, ", ");
      while(dirSt.hasMoreTokens()) { //토큰할 문자가 있는지?
          
    	  directorlist.add(pdao.readPeople(dirSt.nextToken()));
    	  
      }
      
      StringTokenizer actSt = new StringTokenizer(actors, ", ");
      while(actSt.hasMoreTokens()) { //토큰할 문자가 있는지?
          
    	  String pno = actSt.nextToken();
    	  //System.out.println(pno);

    	  actorlist.add(pdao.readPeople(pno));
      }
      
      ArrayList<ReviewDTO> reviewlist = new ArrayList<ReviewDTO>();
      
      int nowPage=1;
      int recordPerPage=3;
      
      reviewlist = revdao.list(nowPage, recordPerPage, mcode);
      mav.setViewName("contlist/contlistread");
      mav.addObject("dto", dto);
      mav.addObject("keylist", keylist);
      mav.addObject("directorlist", directorlist);
      mav.addObject("actorlist", actorlist);
      mav.addObject("reviewlist", reviewlist);

      
      return mav;
   }

   
   @RequestMapping("contlist/contlistwatch.do")
   public ModelAndView create(ContlistDTO contdto, WatchListDTO dto, HttpServletRequest req) {
	   ModelAndView mav = new ModelAndView();
	   
       int mcode = Integer.parseInt(req.getParameter("mcode"));
      
       contdto=dao.contlist(mcode);
       String maudio=contdto.getMaudio(); 
	   
       mav.setViewName("contlist/contentwatch");
       mav.addObject("maudio", maudio);
	   
	   HttpSession session = req.getSession();
	   String mem_id=session.getAttribute("s_mem_id").toString();
	   
	   Date now = new Date();
	   SimpleDateFormat dateFrm = new SimpleDateFormat("yyyyMMddHHmmss");

	   String dateToStr = dateFrm.format(now);	   
	      
	   String watch_reg= watchdao.watchregCreate(dateToStr);
	   
	   dto.setWatch_reg(watch_reg);
	   dto.setMem_id(mem_id);
	   
	   int cnt=watchdao.create(dto);
		if(cnt==0) {
			System.out.println("시청 목록 등록 실패");
		}else {
			System.out.println("시청 목록 등록 성공");
		}//if end
	   
	   return mav;
   }
   
   
   @RequestMapping("mainsearch.do")
   public ModelAndView mainsearch(HttpServletRequest req) {
      ModelAndView mav = new ModelAndView();
      
      String searchkey=req.getParameter("searchkey").trim();
      String searchname=req.getParameter("searchkey").trim();
      //searchname=searchname.replace(" ", "");
      //searchkey=searchkey.replace(" ", "");
     
      String pno=dao.readPno(searchname);
      
      ArrayList<ContlistDTO> list = null;
      
      String msg=searchkey+" : 검색 결과";
      
      int nowPage=1;
      int recordPerPage=8;
      
      list = dao.mainsearch(searchkey, pno, nowPage, recordPerPage);

      mav.setViewName("contlist/contlist");
      mav.addObject("list", list);
      mav.addObject("msg", msg);

      return mav;
   }
   
   
   @RequestMapping({"keysearch.do", "contlist/keysearch.do"})
   public ModelAndView keysearch(HttpServletRequest req) {
      ModelAndView mav = new ModelAndView();
      
      String word=req.getParameter("key_code");
      String key_name=req.getParameter("key_name");
      
      ArrayList<ContlistDTO> list = null;
      
      String msg="#"+key_name+" : 검색 결과";
      
      int nowPage=1;
      int recordPerPage=8;
      String col ="key_code";
      String sort="";
      
      list = dao.list(col, word, sort, nowPage, recordPerPage);
      
      mav.setViewName("contlist/contlist");
      mav.addObject("list", list);
      mav.addObject("msg", msg);

      return mav;
   }

	
    @ResponseBody	
	@RequestMapping({"contlist/ottsearch.do", "ottsearch.do"})
	private ArrayList<ContlistDTO> ottsearch(@RequestParam Map<String, Object> map) {
		
        ArrayList<ContlistDTO> list = null;
    	
		try {
			
			String strNowPage= (String)map.get("nowPage");
			String searchkey= (String)map.get("searchkey"); //홈 화면에서 입력된 검색어
			String key_code= (String)map.get("key_code");   //키워드 검색
			String pno= (String)map.get("pno");				//인물 검색
			String ott= (String)map.get("ott");				//ott 검색
			String mdate= (String)map.get("mdate");			//개봉일 검색
			String gerne= (String)map.get("gerne");			//장르 검색
			String mrate= (String)map.get("mrate"); 		//별점검색
            String sort= (String)map.get("sort");
          
            if(sort==null) {
        	  sort="";
            }
	          
			int nowPage = Integer.parseInt(strNowPage);
		    int recordPerPage=8;
			
	        String col ="";
	        String word="";

			
			if(!(searchkey.equals(""))) {
			      pno=dao.readPno(searchkey);
  				  //System.out.println(pno);
			      if(pno==null) { pno=""; }
			}

			if(!(searchkey.equals(""))) {		        
				pno=dao.readPno(searchkey);				
			}else if(!(key_code.equals(""))) {				
				col="key_code";
				word=key_code;
			}else if(!(pno.equals(""))) {				
				col="pno";
				word=pno;
			}else if(!(mdate.equals(""))) {				
				col="mdate";
				word=mdate;
			}else if(!(gerne.equals(""))) {				
				col="key_code";
				word=gerne;
			}else if(!(mrate.equals(""))) {				
				col="mrate";
				word=mrate;
			}	      
				
			if(!(searchkey.equals(""))) {
			      pno=dao.readPno(searchkey);
			      if(pno==null) { 
			    	  list=dao.mainottsearch(ott, searchkey, nowPage, recordPerPage);
			      }else {
			    	  list = dao.mainottsearch(ott, searchkey, pno, nowPage, recordPerPage);
			      }
			      
			}else {
				  list = dao.ottRead(ott, col, word, sort, nowPage, recordPerPage);
			}				

			
		}catch (Exception e) {
			System.out.println("응답실패: " + e);
		}
		
		return list;
		
	}//ottsearch() end
	
   
    

    
	
    @RequestMapping("peoplesearch.do")
    public ModelAndView peoplesearch(HttpServletRequest req) {
       ModelAndView mav = new ModelAndView();
       
       String word=req.getParameter("pno");
       String pname=req.getParameter("pname");
      
       ArrayList<ContlistDTO> list = null;
       
       String msg=pname+" : 검색 결과";
       
       int nowPage=1;
       int recordPerPage=8;
       String col ="pno";
       String sort="";
       
       list = dao.list(col, word, sort, nowPage, recordPerPage);

       mav.setViewName("contlist/contlist");
       mav.addObject("list", list);
       mav.addObject("msg", msg);

       return mav;
    }
	
    
    @RequestMapping(value = "contlist/reviewForm.do", method = RequestMethod.GET)
    public ModelAndView reviewAll(@ModelAttribute ContlistDTO dto, ReviewDTO dto2) {
       ModelAndView mav=new ModelAndView();
       
       
       int mcode = dto.getMcode();
       dto2 = revdao.reviewAll(mcode);
    
          mav.setViewName("review/reviewForm");
          mav.addObject("dto", dto);
          mav.addObject("dto2", dto2);
          mav.addObject("mcode", mcode);

       return mav;

    }
    
    
    
    @RequestMapping(value = "contlist/contlistins.do", method = RequestMethod.POST)
    public ModelAndView rev_ins(@ModelAttribute ContlistDTO dto, ReviewDTO dto2, HttpServletRequest req) {
       ModelAndView mav=new ModelAndView();
       
       int mcode = Integer.parseInt(req.getParameter("mcode"));
       dto2.setMcode(mcode);
       
       HttpSession session = req.getSession();
       String mem_id =session.getAttribute("s_mem_id").toString();
       dto2.setMem_id(mem_id);
       
       String rev_spo=Utility.checkNull(req.getParameter("rev_spo"));
    

          if(rev_spo.equals("O")) { 
             dto2.setRev_spo("O");
          }else {
             dto2.setRev_spo("X");
          }
          
       int cnt = revdao.rev_ins(dto2);
       
       
       if(cnt==1) {//리뷰 입력 성공, 컨텐츠 리스트의 별점을 수정
          
          //1) 새로 넣은 리뷰의 별점+기존에 있던 별점을 평균내서 가져오기 DAO select 
          double mrate = (int)revdao.rev_rateHap(mcode);
          //2) 1)1에서 불러온 값을 매개변수로 넘겨서 CONTLIST를 update 해준다
          dao.mrateUpdate(mrate, mcode);
          
       }else {
          System.out.println("리뷰  입력 실패");
       }
       
        
       dto=dao.contlistread(mcode);
       String key_code = dto.getKey_code();
       
       ArrayList<SearchKeyDTO> keylist=new ArrayList<>() ;

       StringTokenizer kc = new StringTokenizer(key_code, " || ");      
       while(kc.hasMoreTokens()) { //토큰할 문자가 있는지?          
     	  keylist.add(skdao.SearchKey(kc.nextToken()));    	  
       }
       
       if(session.getAttribute("s_mem_id")!=null && session.getAttribute("s_mem_lv")!=null && session.getAttribute("s_mem_pw")!=null) {

 			String mem_lv=session.getAttribute("s_mem_lv").toString();
 			mav.addObject("mem_lv", mem_lv);    	  
       }
       
       pdao=new PeopleDAO();
       
       String directors = dto.getDirector();
       String actors = dto.getActor();
       ArrayList<PeopleDTO> directorlist = new ArrayList<>();
       ArrayList<PeopleDTO> actorlist = new ArrayList<>();
     
       
       StringTokenizer dirSt = new StringTokenizer(directors, ", ");
       while(dirSt.hasMoreTokens()) { //토큰할 문자가 있는지?
           
     	  directorlist.add(pdao.readPeople(dirSt.nextToken()));
     	  
       }
       
       StringTokenizer actSt = new StringTokenizer(actors, ", ");
       while(actSt.hasMoreTokens()) { //토큰할 문자가 있는지?
           
     	  String pno = actSt.nextToken();
     	  //System.out.println(pno);

     	  actorlist.add(pdao.readPeople(pno));
       }
       
       ArrayList<ReviewDTO> reviewlist = new ArrayList<ReviewDTO>();
       
       int nowPage=1;
       int recordPerPage=3;
       
       reviewlist = revdao.list(nowPage, recordPerPage, mcode);
       mav.setViewName("contlist/contlistread");
       mav.addObject("dto", dto);
       mav.addObject("keylist", keylist);
       mav.addObject("directorlist", directorlist);
       mav.addObject("actorlist", actorlist);
       mav.addObject("reviewlist", reviewlist);

       return mav;

    }
   
    
    @RequestMapping({"reviewdelete.do", "contlist/reviewdelete.do"})
    public ModelAndView delete(@ModelAttribute ReviewDTO revdto, ContlistDTO dto, HttpServletRequest req) {
       ModelAndView mav=new ModelAndView();
       mav.setViewName("review/msgView");//어디로 보낼지 바꾸기
       int rev_code=Integer.parseInt(req.getParameter("rev_code"));
       
       revdto= revdao.readReviewOne(rev_code);
       
       HttpSession session = req.getSession();
       
       String s_mem_id="";
       
       if(session.getAttribute("s_mem_id")!=null) {
    	   s_mem_id=session.getAttribute("s_mem_id").toString();
       }
       
       String mem_id=revdto.getMem_id();
       String msg="";
       if(!(s_mem_id.equals(mem_id))) {//세션정보의 글의 작성자가 일치하지 않을때는 수정페이지로 넘어가지 않음
          
          msg="<p> 작성자 본인만 글을 삭제할 수 있습니다</p>";

       }else {
           int cnt=revdao.delete(rev_code, revdto.getMcode());
           if(cnt==0) {   
              msg="<p>해당 글 삭제 실패</p>";
           }else {
              msg="<p>해당 글 삭제 성공</p>";
           }//if end
       }

       mav.addObject("msg", msg);
       mav.setViewName("review/msgView");

       return mav;
    }//delete() end
    
    
    @RequestMapping({"reviewupdate.do", "contlist/reviewupdate.do"})
    public ModelAndView update(@ModelAttribute ReviewDTO dto, HttpServletRequest req) {
       ModelAndView mav=new ModelAndView();
       int rev_code=Integer.parseInt(req.getParameter("rev_code"));
       dto= revdao.readReviewOne(rev_code);
       
       HttpSession session = req.getSession();
       
       String s_mem_id="";
       
       if(session.getAttribute("s_mem_id")!=null) {
    	   s_mem_id=session.getAttribute("s_mem_id").toString();
       }
       
       String mem_id=dto.getMem_id();
       
       if(!(s_mem_id.equals(mem_id))) {//세션정보의 글의 작성자가 일치하지 않을때는 수정페이지로 넘어가지 않음
          
          String msg="<p> 작성자 본인만 글을 수정할 수 있습니다</p>";
             mav.addObject("msg", msg);
             mav.setViewName("review/msgView");

       }else {
           mav.addObject("dto", dto);
           mav.setViewName("review/reviewUpdate");
       }
       
       return mav;
    
    }// update() end
    

    
    
    
    @RequestMapping(value = "reviewupdateproc.do", method = RequestMethod.POST)
    public ModelAndView updateproc(@ModelAttribute ReviewDTO dto2,  HttpServletRequest req) {
       ModelAndView mav=new ModelAndView();
       mav.setViewName("review/msgView");
       
       String rev_spo=Utility.checkNull(req.getParameter("rev_spo"));
       
       if(rev_spo.equals("O")) { 
          dto2.setRev_spo("O");
       }else {
          dto2.setRev_spo("X");
       }
        
       HttpSession session = req.getSession();
       
       String s_mem_id=session.getAttribute("s_mem_id").toString();
       int rev_code = Integer.parseInt(req.getParameter("rev_code"));
          
          int cnt = revdao.updateproc(dto2, rev_code, s_mem_id);
          
          if(cnt==0) {
             String msg="<p> 글 수정 실패</p>";
                mav.addObject("msg", msg);
          }else {
             String msg="<p> 글 수정이 완료 되었습니다</p>";
                mav.addObject("msg", msg);
          }//if end
       
       return mav;
    
    }

    
    
    @RequestMapping("/contlist/reviewList.do")
    public ModelAndView reviewListAjax(HttpServletRequest req) {
       ModelAndView mav = new ModelAndView();
       int mcode = Integer.parseInt(req.getParameter("mcode"));
       ArrayList<ReviewDTO> list = null;
       
       int recordPerPage=3;
       int nowPage=1;
       if(req.getParameter("nowPage")!=null){
          nowPage=Integer.parseInt(req.getParameter("nowPage"));
       }//if end
       
       list = revdao.list(nowPage, recordPerPage, mcode);
       System.out.println(list);
       mav.setViewName("review/reviewListAjax");
       mav.addObject("list", list);
       mav.addObject("nowPage", nowPage);

       return mav;
    }
    
    
    
    @ResponseBody   
    @RequestMapping("contlist/morereviews.do")
    private ArrayList<ReviewDTO> morereviews(@RequestParam Map<String, Object> map) {
       
        ArrayList<ReviewDTO> list = null;
       
       try {
          String strMcode= (String)map.get("mcode");
          int mcode= Integer.parseInt(strMcode);
          String strNowPage= (String)map.get("page");
          int nowPage = Integer.parseInt(strNowPage);
          
          //System.out.println(nowPage);
          
             int recordPerPage=3;
           list = revdao.list(nowPage, recordPerPage, mcode);
           
           //System.out.println(list);
           
       }catch (Exception e) {
          System.out.println("응답실패: " + e);
       }
       
       return list;
       
    }//morerivews() end

    
    @ResponseBody   
    @RequestMapping({"contlist/searchfield.do", "searchfield.do"})
    private ArrayList<ContlistDTO> gerneSearches(@RequestParam Map<String, Object> map) {
       
        ArrayList<ContlistDTO> list = null;
       
       try {
          String col= (String)map.get("col");
          String word= (String)map.get("word");
          String strNowPage= (String)map.get("nowPage");
          int nowPage = Integer.parseInt(strNowPage);
          String sort= (String)map.get("sort");
          
          if(sort==null) {
        	  sort="";
          }
          
           //System.out.println(searchfield);
           int recordPerPage=8;
           list = dao.list(col, word, sort, nowPage, recordPerPage);
           
           //System.out.println(list);
           
       }catch (Exception e) {
          System.out.println("ajax 응답 실패: " + e);
       }
       
       return list;
       
    }
    
    
    @ResponseBody   
    @RequestMapping({"contlist/sorting.do", "sorting.do"})
    private ArrayList<ContlistDTO> sorting(@RequestParam Map<String, Object> map) {
       
        ArrayList<ContlistDTO> list = null;
        
       try {
          String sort= (String)map.get("sort");
          String strNowPage= (String)map.get("nowPage");
          String searchkey= (String)map.get("searchkey"); //홈 화면에서 입력된 검색어
          String key_code= (String)map.get("key_code");   //키워드 검색
          String pno= (String)map.get("pno");            //인물 검색
          String ott= (String)map.get("ott");            //ott 검색
          String mdate= (String)map.get("mdate");         //개봉일 검색
          String gerne= (String)map.get("gerne");         //장르 검색
          String mrate= (String)map.get("mrate");       //별점검색
          
          int nowPage = Integer.parseInt(strNowPage);
           int recordPerPage=8;
          
            String col ="";
            String word="";
           
          if(!(searchkey.equals(""))) {              
             pno=dao.readPno(searchkey);            
          }else if(!(key_code.equals(""))) {            
             col="key_code";
             word=key_code;
          }else if(!(pno.equals(""))) {            
             col="pno";
             word=pno;
          }else if(!(mdate.equals(""))) {            
             col="mdate";
             word=mdate;
          }else if(!(gerne.equals(""))) {            
             col="key_code";
             word=gerne;
          }else if(!(mrate.equals(""))) {            
             col="mrate";
             word=mrate;
          }
          
          list=dao.list(col, word, sort, nowPage, recordPerPage);
          
          //System.out.println(list);
          
          if(ott!="N") {//ott버튼을 누른 상태일 때
                            
             if(!(searchkey.equals(""))) {
                   pno=dao.readPno(searchkey);
                   if(pno==null) { pno=""; }
             }            
                         
               list = dao.ottRead(ott, col, word, sort, nowPage, recordPerPage);
          }
          
          
       }catch (Exception e) {
          System.out.println("응답실패: " + e);
       }
       
       return list;
       
    }//sorting() end
    
   
}// class end