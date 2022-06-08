package kr.co.mymelon.mediagroup;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MediagroupCont {

	MediagroupDAO dao=null;
	
	public MediagroupCont() {
		dao=new MediagroupDAO(); //DB연결 객체 생성
		System.out.println("-----MediagroupCont() 객체 생성됨");
	}//end
	
	
	//결과확인 http://localhost:9097/mediagroup/create.do
	
	@RequestMapping(value = "mediagroup/create.do", method = RequestMethod.GET)
	public String createForm() {
		return "mediagroup/createForm";  // /WEB-INF/views/mediagroup/createForm.jsp
	}//createForm() end
	
	
	@RequestMapping(value = "mediagroup/create.do", method = RequestMethod.POST)
	public ModelAndView createProc(@ModelAttribute MediagroupDTO dto) {
		                          //String title 써도 됨
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mediagroup/msgView");
		
		int cnt=dao.create(dto);
		if(cnt==0) {
            String msg="<p>미디어 그룹 등록 실패</p>";
            String img="<img src='../images/fail.png'>";
            String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
            String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
            mav.addObject("msg", msg);
            mav.addObject("img", img);
            mav.addObject("link1", link1);
            mav.addObject("link2", link2);
		}else {
            String msg="<p>미디어 그룹 등록 성공</p>";
            String img="<img src='../images/sound.png'>";
            String link1="<input type='button' value='계속등록' onclick='location.href=\"create.do\"'>";
            String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
            mav.addObject("msg", msg);
            mav.addObject("img", img);
            mav.addObject("link1", link1);
            mav.addObject("link2", link2); 
		}//if end
		
		return mav;
		
	}//createProc() end
	
	
	@RequestMapping("mediagroup/list.do")
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mediagroup/list");
		mav.addObject("list", dao.list());
		return mav;
	}//list() end
	
	
	
	@RequestMapping(value = "mediagroup/delete.do", method = RequestMethod.GET)
	public ModelAndView deleteForm(int mediagroupno){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mediagroup/deleteForm"); // /WEB-INF/views/mediagroup/deleteForm.jsp
		mav.addObject("mediagroupno", mediagroupno);
		return mav;		
	}//deleteForm() end
	
	
	
	@RequestMapping(value = "mediagroup/delete.do", method = RequestMethod.POST)
	public ModelAndView deleteProc(int mediagroupno) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mediagroup/msgView");
		
		int cnt=dao.delete(mediagroupno);
		if(cnt==0) {
			String msg="<p>미디어 그룹 삭제 실패</p>";
            String img="<img src='../images/fail.png'>";
            String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
            String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
            mav.addObject("msg", msg);
            mav.addObject("img", img);
            mav.addObject("link1", link1);
            mav.addObject("link2", link2);
		}else {
			String msg="<p>미디어 그룹 삭제 성공</p>";
            String img="<img src='../images/sound.png'>";
            String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
            mav.addObject("msg", msg);
            mav.addObject("img", img);
            mav.addObject("link2", link2);            
		}//if end
		return mav;
	}//deleteProc() end
	
	
	
	@RequestMapping(value = "mediagroup/update.do", method = RequestMethod.GET)
	public ModelAndView updateForm(int mediagroupno) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("mediagroup/updateForm");
		mav.addObject("dto", dao.read(mediagroupno)); //DB에서 수정할 행 가져오기
		return mav;
	}//updateForm() end
	
	
	
	@RequestMapping(value = "mediagroup/update.do", method = RequestMethod.POST)
	public ModelAndView updateProc(HttpServletRequest req) { //@ModelAttribute MediagroupDTO dto로 받아도 됨
		
		int mediagroupno=Integer.parseInt(req.getParameter("mediagroupno"));
        String title=req.getParameter("title").trim();
        
        MediagroupDTO dto=new MediagroupDTO();
        dto.setMediagroupno(mediagroupno);
        dto.setTitle(title);
        
        ModelAndView mav=new ModelAndView();
        mav.setViewName("mediagroup/msgView");
        int cnt=dao.update(dto);
		if(cnt==0) {
			String msg="<p>미디어 그룹 수정 실패</p>";
            String img="<img src='../images/fail.png'>";
            String link1="<input type='button' value='다시시도' onclick='javascript:history.back()'>";
            String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
            mav.addObject("msg", msg);
            mav.addObject("img", img);
            mav.addObject("link1", link1);
            mav.addObject("link2", link2);
		}else {
			String msg="<p>미디어 그룹 수정 성공</p>";
            String img="<img src='../images/sound.png'>";
            String link2="<input type='button' value='그룹목록' onclick='location.href=\"list.do\"'>";
            mav.addObject("msg", msg);
            mav.addObject("img", img);
            mav.addObject("link2", link2);            
		}//if end
        
        return mav;		
	}//updateProc() end
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//class end

