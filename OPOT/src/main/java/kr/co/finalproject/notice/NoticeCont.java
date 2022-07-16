package kr.co.finalproject.notice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.utility.Paging;
import net.utility.Utility;


@Controller
public class NoticeCont {
	
	NoticeDAO dao=null;
	
	public NoticeCont() {
		dao=new NoticeDAO();
		System.out.println("-----NoticeCont() 객체생성");
	}//NoticeCont() 
	
	
	//결과확인 http://localhost:9090/notice/notice.do
	@RequestMapping(value = "notice/notice.do")
	public ModelAndView list(HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		
		String word=req.getParameter("word");
		String col=req.getParameter("col");
		word=Utility.checkNull(word);
		col=Utility.checkNull(col);
		
		int recordPerPage=10;
		
		int nowPage=1;
		if(req.getParameter("nowPage")!=null){
			nowPage=Integer.parseInt(req.getParameter("nowPage"));
		}//if end
		int totalRecord=dao.count(col, word);
		
		
		mav.setViewName("notice/noticelist");
		String paging=new Paging().paging2(totalRecord, nowPage, recordPerPage, col, word, "notice.do");
		mav.addObject("paging",paging);
		mav.addObject("list", dao.list(col, word, nowPage, recordPerPage));
		return mav;
	}//notice() end

	
	@RequestMapping("notice/noticeForm.do")
	public ModelAndView write() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("notice/noticeForm");
		return mav;
	}//write() end
	
	@RequestMapping(value = "notice/noticecreate.do", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute NoticeDTO dto) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("notice/msgView");
		
		int cnt=dao.create(dto);
		if(cnt==0) {
			String msg="<p>글 등록 실패</p>";
            mav.addObject("msg", msg);
		}else {
			String msg="<p>글 등록 성공</p>";
            mav.addObject("msg", msg);
		}//if end
		return mav;
	}//create() end
	
	@RequestMapping(value = "notice/noticeread.do", method = RequestMethod.GET)
	public ModelAndView read(@ModelAttribute NoticeDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("notice/noticeread");
		int n_num=Integer.parseInt(req.getParameter("n_num"));
		
			
		dto=dao.read(n_num);
		if(dto==null){
			String msg="<p>해당 글 없음</p>";
            mav.addObject("msg", msg);
		}else{
			dao.incrementCnt(n_num);
			mav.addObject("dto", dto);
			String msg="<p>글 불러옴</p>";
            mav.addObject("msg", msg);
		}//if end
		
		return mav;
	}//read() end
	

	
	@RequestMapping("notice/noticedelelte.do")
	public ModelAndView delete(HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("notice/msgView");
		int n_num=Integer.parseInt(req.getParameter("n_num"));
		
		int cnt=dao.delete(n_num);
		if(cnt==0) {
			String msg="<p>해당 글 삭제 실패</p>";
            mav.addObject("msg", msg);
		}else {
			String msg="<p>해당 글 삭제 성공</p>";
            mav.addObject("msg", msg);
		}//if end
		
		return mav;
	}//delete() end
	
	
	@RequestMapping("notice/noticeupdate.do")
	public ModelAndView update(@ModelAttribute NoticeDTO dto,HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("notice/noticeupdate");
		int n_num=Integer.parseInt(req.getParameter("n_num"));
		mav.addObject("n_num", n_num);
		dto=dao.read(n_num);
		mav.addObject("dto", dto);
		return mav;
	}//write() end
	
	
	
	@RequestMapping(value = "notice/noticeupdateproc.do", method = RequestMethod.POST)
	public ModelAndView updateproc(@ModelAttribute NoticeDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("notice/msgView");
		int n_num=Integer.parseInt(req.getParameter("n_num"));
		mav.addObject("n_num", n_num);
		
		int cnt=dao.updateproc(dto,n_num);
		if(cnt==0){
			String msg="<p>해당 글 수정 실패</p>";
            mav.addObject("msg", msg);
		}else {
			String msg="<p>해당 글 수정 성공</p>";
            mav.addObject("msg", msg);
		}//if end
		
		return mav;
	}
	
	

	
}//class end