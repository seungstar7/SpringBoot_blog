package kr.co.finalproject.webmaster;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kr.co.finalproject.contlist.ContlistDAO;
import kr.co.finalproject.contlist.ContlistDTO;
import net.utility.UploadSaveManager;

@Controller
public class RecommendCont {

	RecommendDAO dao = null;
	
	public RecommendCont() {		
	  dao = new RecommendDAO();
      System.out.println("-----RecommendCont() 객체 생성");				
	}
	
	@RequestMapping("themelist.do")
	public ModelAndView themeList(ThemesDTO dto) {
	    ModelAndView mav = new ModelAndView();
		ArrayList<ThemesDTO> list=new ArrayList<ThemesDTO>();
	    
		list=dao.readThemes();

	    mav.setViewName("webmaster/recmanage/themeList");
        mav.addObject("list", list);
        return mav;
	}
	
	@RequestMapping("themeadd.do")
	public String themeAdd() {
		return "webmaster/recmanage/themeAdd";
	}

	@RequestMapping("recadd.do")
	public ModelAndView recAdd(HttpServletRequest req) {
	    ModelAndView mav = new ModelAndView();
	    
	    int t_num = Integer.parseInt(req.getParameter("t_num"));
		
	    mav.setViewName("webmaster/recmanage/recForm");
        mav.addObject("t_num", t_num);
        return mav;

	}
	
	
	@RequestMapping("theme.do")
	public ModelAndView themeRead(ThemesDTO dto, HttpServletRequest req) {
	    ModelAndView mav = new ModelAndView();
		ArrayList<RecommendDTO> list=null;
		
		int t_num = Integer.parseInt(req.getParameter("theme"));
		
		list=dao.readRecThemes(t_num);
		dto=dao.readTheme(t_num);
		
	    mav.setViewName("webmaster/recmanage/recList");
        mav.addObject("list", list);
        mav.addObject("dto", dto);
        return mav;
	}
	
	
	@RequestMapping("themeins.do")
	public ModelAndView themeIns(ThemesDTO dto, HttpServletRequest req) {
	    ModelAndView mav = new ModelAndView();

	    String basePath=req.getSession().getServletContext().getRealPath("/storage");
	    MultipartFile t_photoMF=dto.getT_photoMF();
	    
	    String t_photo=UploadSaveManager.saveFileSpring30(t_photoMF, basePath);
	    dto.setT_photo(t_photo);
	    
	    int cnt= dao.insert(dto);
		String msg="";
	    
        if(cnt==0){
			msg+="<script>";
			msg+="    alert('테마 등록 실패');";
			msg+="    location.href='javascript:history.back();'";
			msg+="</script>";	

         }else {        	 			
 			msg+="<script>";
 			msg+="    alert('테마 등록 성공');";
 			msg+="    location.href='javascript:history.go(-2);'";
 			msg+="</script>";
         }
        
        mav.addObject("msg", msg);
	    mav.setViewName("webmaster/msgView");
        return mav;
	}
	
	
	
	@RequestMapping("themeread.do")
	public ModelAndView themeread(RecommendDTO dto, HttpServletRequest req) {
	    ModelAndView mav = new ModelAndView();
	    ContlistDAO contdao = new ContlistDAO();
	    
		int r_num = Integer.parseInt(req.getParameter("r_num"));
	    
	    dto=dao.readRecTheme(r_num);
	    
	    String mcodes=dto.getMcodes();
  	    int mcode=0;	    
	    ArrayList<ContlistDTO> contents = new ArrayList<ContlistDTO>();
	    
	    StringTokenizer st = new StringTokenizer(mcodes, " || ");
	      while(st.hasMoreTokens()) {
	    	  
	    	  mcode=Integer.parseInt(st.nextToken());
	    	  contents.add(contdao.contlist(mcode));
	    	  
	      }	    
	    
	    //System.out.println("컨텐츠 목록: "+contents);
	    

        mav.addObject("dto", dto);	    
        mav.addObject("list", contents);	    
        mav.setViewName("webmaster/recmanage/recRead");
        return mav;
	}
    
    @RequestMapping(value = "moviesuggest.do", produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String moviesuggest(@RequestParam Map<String, Object> map, Locale locale, Model model) {
    	
    	ArrayList<String> suggestList=null;
    	ContlistDAO contdao = new ContlistDAO();
    	Gson gson = new Gson();
    	
		try {
			  String keyword = (String)map.get("keyword");
			  suggestList=contdao.contentSearch(keyword);
			  //System.out.println(suggestList);
			  
		}catch (Exception e) {
			System.out.println("응답실패: " + e);
		}
		
		return gson.toJson(suggestList);

    }
    
    
    
	@RequestMapping("recins.do")
	public ModelAndView recIns(RecommendDTO dto, HttpServletRequest req) {
	    ModelAndView mav = new ModelAndView();
	    ContlistDAO contdao = new ContlistDAO();

	    String basePath=req.getSession().getServletContext().getRealPath("/storage");
	    MultipartFile r_photoMF=dto.getR_photoMF();
	    
	    int recContents=Integer.parseInt(req.getParameter("m"));

	    String mcodes="";
	    String content="";

	    
	    for(int m=1; m<=recContents; m++) {
	    	
	    	content=req.getParameter("content"+m).trim();
	    	int mcode=contdao.mcodeRead(content);
	    	
	    	mcodes+=mcode+" || ";
	    	
	    }
	    
	    dto.setMcodes(mcodes);
	    
	    String r_photo=UploadSaveManager.saveFileSpring30(r_photoMF, basePath);
	    dto.setR_photo(r_photo);
	    
	    int cnt= dao.insert(dto);
		String msg="";
	    
        if(cnt==0){
			msg+="<script>";
			msg+="    alert('추천글 등록 실패');";
			msg+="    location.href='javascript:history.back();'";
			msg+="</script>";	

         }else {        	 			
 			msg+="<script>";
 			msg+="    alert('추천글 등록 성공');";
 			msg+="    location.href='javascript:history.go(-2);'";
 			msg+="</script>";
         }
        
        mav.addObject("msg", msg);
	    mav.setViewName("webmaster/msgView");
	    
        return mav;
	}
	
	
	
	@RequestMapping("recdelete.do")
	public ModelAndView recDelete(int t_num, int r_num, RecommendDTO dto, HttpServletRequest req) {
	    ModelAndView mav = new ModelAndView();
	    
	    dto=dao.readRecTheme(r_num);
	    String folder=req.getSession().getServletContext().getRealPath("/storage");
	    String r_photo=dto.getR_photo();
	    
	    
	    int cnt= dao.recDelete(r_num);
		String msg="";
	    
        if(cnt==0){
			msg+="<script>";
			msg+="    alert('추천글 삭제 실패');";
			msg+="    location.href='javascript:history.back();'";
			msg+="</script>";	

         }else {
         	UploadSaveManager.deleteFile(folder, r_photo);

 			msg+="<script>";
 			msg+="    alert('추천글 삭제 성공');";
 			msg+="    location.href='javascript:history.go(-2);'";
 			msg+="</script>";
         }
        
        mav.addObject("msg", msg);
	    mav.setViewName("webmaster/msgView");

        return mav;
	}
	
	
	
	@RequestMapping("recupdate.do")
	public ModelAndView recupdate(RecommendDTO dto, HttpServletRequest req) {
	    ModelAndView mav = new ModelAndView();
	    ContlistDAO contdao = new ContlistDAO();
	    
		int r_num = Integer.parseInt(req.getParameter("r_num"));
		int t_num = Integer.parseInt(req.getParameter("t_num"));
	    
	    dto=dao.readRecTheme(r_num);
	    
	    String mcodes=dto.getMcodes();
  	    int mcode=0;	    
	    ArrayList<ContlistDTO> contents = new ArrayList<ContlistDTO>();
	    
	    StringTokenizer st = new StringTokenizer(mcodes, " || ");
	      while(st.hasMoreTokens()) {
	    	  
	    	  mcode=Integer.parseInt(st.nextToken());
	    	  contents.add(contdao.contlist(mcode));
	    	  
	      }	    

        mav.addObject("t_num", t_num);
        mav.addObject("dto", dto);	    
        mav.addObject("list", contents);	    
        mav.addObject("themes", dao.readThemes());	    
        mav.setViewName("webmaster/recmanage/recUpdate");
        return mav;
	}

	
	
	
	@RequestMapping("recupdateproc.do")
	public ModelAndView recupdateproc(RecommendDTO dto, ThemesDTO tdto, HttpServletRequest req) {
	    ModelAndView mav = new ModelAndView();
	    ContlistDAO contdao = new ContlistDAO();
	    
		int r_num = Integer.parseInt(req.getParameter("r_num"));
	    String t_title=req.getParameter("t_title");
		
	    dto.setR_num(r_num);
	    dto.setT_num(dao.readTnum(t_title));
		
	    RecommendDTO oldDTO=dao.readRecTheme(r_num);
	    String oldR_photo=oldDTO.getR_photo();//기존 대표사진 파일명
	    
	    String basePath=req.getSession().getServletContext().getRealPath("/storage");
	    MultipartFile r_photoMF=dto.getR_photoMF();

	    int recContents=Integer.parseInt(req.getParameter("m"));

	    String mcodes="";
	    String content="";

	    
	    for(int m=1; m<=recContents; m++) {
	    	
	    	content=req.getParameter("content"+m).trim();	    	
	    	int mcode=contdao.mcodeRead(content);
	    	
	    	mcodes+=mcode+" || ";
	    	
	    }
	    
	    dto.setMcodes(mcodes);
	    
	    String r_photo=UploadSaveManager.saveFileSpring30(r_photoMF, basePath);
	    
	      if(r_photo=="") {//새로운 파일 업로드 안했다면
	    	  dto.setR_photo(oldR_photo);
	      }else {
	    	  dto.setR_photo(r_photo);
	    	  UploadSaveManager.deleteFile(basePath, oldR_photo);
	      }
	      
	    int cnt= dao.recUpdate(dto);
		String msg="";
	    
        if(cnt==0){
			msg+="<script>";
			msg+="    alert('추천글 수정 실패');";
			msg+="    location.href='javascript:history.back();'";
			msg+="</script>";	

         }else {        	 			
 			msg+="<script>";
 			msg+="    alert('추천글 수정 성공');";
 			msg+="    location.href='javascript:history.go(-2);'";
 			msg+="</script>";
         }
        
        mav.addObject("msg", msg);
	    mav.setViewName("webmaster/msgView");
        return mav;
	}
	
	
	
	
	
}//class end
