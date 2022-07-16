package kr.co.finalproject.qna;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.utility.Paging;
import net.utility.Utility;



@Controller
public class QnaCont {
	
	QnaDAO dao=null;
	
	public QnaCont() {	
		dao = new QnaDAO();
		System.out.println("-----QnaCont() 객체생성");	
	}
	
	//결과확인 http://localhost:9090/qna/qna.do
	@RequestMapping(value = "qna/qna.do")
	public ModelAndView list(HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		
		String word=req.getParameter("word");//검색어
		String col=req.getParameter("col");//검색칼럼
		word=Utility.checkNull(word); //문자열값이 null이면 빈문자열로 치환
		col =Utility.checkNull(col);
		
		int recordPerPage=10;
		
		int nowPage=1;
		if(req.getParameter("nowPage")!=null){
			nowPage=Integer.parseInt(req.getParameter("nowPage"));
		}//if end
		int totalRecord=dao.count(col, word);
			
		HttpSession session = req.getSession();
		
		if(session.getAttribute("s_mem_id")!=null && session.getAttribute("s_mem_lv")!=null && session.getAttribute("s_mem_pw")!=null) {
			String mem_lv=session.getAttribute("s_mem_lv").toString();
			mav.addObject("mem_lv", mem_lv);
		}
		mav.setViewName("qna/qnalist");
		String paging=new Paging().paging2(totalRecord, nowPage, recordPerPage, col, word, "qna.do");
		mav.addObject("paging",paging);
		mav.addObject("list", dao.list(col, word, nowPage, recordPerPage));

		
		return mav;
	}//list) end

	
	@RequestMapping(value = "qna/qnaread.do", method = RequestMethod.GET)
	public ModelAndView read(@ModelAttribute QnaDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("qna/qnaread");
		int qna_num=Integer.parseInt(req.getParameter("qna_num"));
		
		HttpSession session = req.getSession();
		
		
		dto=dao.read(qna_num); 
		if(dto==null){
			String msg="<p>해당 글 없음</p>";
	        mav.addObject("msg", msg);
		}else{
			dao.incrementCnt(qna_num);
	        if(session.getAttribute("s_mem_id")==null || session.getAttribute("s_mem_lv")==null || session.getAttribute("s_mem_pw")==null) {
				String msg="<p>로그인 후 글 수정 및 삭제 가능합니다</p>";
				mav.addObject("dto", dto);
	            mav.addObject("msg", msg);
			}else {	
				mav.addObject("dto", dto);
				String msg="<p>로그인 됨</p>";
		        mav.addObject("msg", msg);
		        String mem_id=session.getAttribute("s_mem_id").toString();
		        String mem_pw=session.getAttribute("s_mem_pw").toString();
				mav.addObject("mem_id", mem_id);
				mav.addObject("mem_pw", mem_pw);
			}//if end
		}//if end
		
			
		return mav;
	}//read() end
	
	@RequestMapping(value = "qna/qnaForm.do", method = RequestMethod.POST)
	public ModelAndView Form(HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		
		HttpSession session = req.getSession();
		
		String mem_pw=session.getAttribute("s_mem_pw").toString();
		mav.addObject("mem_pw", mem_pw);
		
		mav.setViewName("qna/qnaForm");
		return mav;
	}//form() end
	
	@RequestMapping(value = "qna/qnaProc.do", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute QnaDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("qna/msgView");
		
		String ip = req.getRemoteAddr();
		
		HttpSession session = req.getSession();
		
		dto.setIp(ip);
		if(session.getAttribute("s_mem_id")==null || session.getAttribute("s_mem_lv")==null || session.getAttribute("s_mem_pw")==null) {
			String msg="<p>로그인 해주세요</p>";
            mav.addObject("msg", msg);
		}else {
			String mem_id=session.getAttribute("s_mem_id").toString();
			dto.setMem_id(mem_id);
			int cnt=dao.create(dto);
			if(cnt==0) {
	            String msg="<p>qna 등록 실패</p>";
	            mav.addObject("msg", msg);
			}else {
				String msg="<p>qna 등록 성공</p>";
	            mav.addObject("msg", msg);
				//mav.setViewName("index");
			}//if end
		}
		return mav;
	}//create end
	
	
	@RequestMapping(value = "qna/qnadelete.do", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute QnaDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		HttpSession session = req.getSession();
		
		int qna_num=Integer.parseInt(req.getParameter("qna_num"));
		
		if(session.getAttribute("s_mem_lv").toString().equals("A")) {
			String msg="";
			String link1="";
			String link2="";

			int cnt=dao.delete(qna_num);
			if(cnt==0) {
				
				msg+="<script>";
				msg+="    alert('삭제 실패');";
				msg+="    location.href='javascript:history.back();'";
				msg+="</script>";	

			}else {
				
				msg+="<p>삭제 성공</p>";
				//link1+="<a href=\"<%=request.getContextPath()%>/home.do\">홈으로</a>";
				//link2+="<a href=\"<%=request.getContextPath()%>/qna/qna.do\">문의사항목록</a>";
				
				mav.addObject("link1", link1);	
				mav.addObject("link2", link2);	
				
			}//if end
			
			mav.addObject("msg", msg);	
			mav.setViewName("qna/msgView");
			
		}else {
	        mav.addObject("msg", "답글 삭제를");
	        mav.addObject("qna_num", qna_num);
			mav.setViewName("qna/qnaPwCheck");
		}		

		return mav;
	}//delete end
	
	
	@RequestMapping(value = "qna/qnadelete.do", method = RequestMethod.POST)
	public ModelAndView deletecheck(@ModelAttribute QnaDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		
		int qna_num=Integer.parseInt(req.getParameter("qna_num"));
		String qna_pw=req.getParameter("qna_pw");
		
		dto=dao.pwcheck(qna_num, qna_pw);
		String msg="";
		String link1="";
		String link2="";
		
		if(dto==null) {
			msg+="<script>";
			msg+="    alert('비밀번호가 일치하지 않습니다');";
			msg+="    location.href='javascript:history.back();'";
			msg+="</script>";
		}else {
			int cnt=dao.delete(qna_num);
			if(cnt==0) {
				msg+="<script>";
				msg+="    alert('삭제 실패');";
				msg+="    location.href='javascript:history.back();'";
				msg+="</script>";
			}else {
				msg+="<p>삭제 성공</p>";
				//link1+="<a href=\"<%=request.getContextPath()%>/home.do\">홈으로</a>";
				//link2+="<a href=\"<%=request.getContextPath()%>/qna/qna.do\">문의사항목록</a>";
				
				mav.addObject("link1", link1);	
				mav.addObject("link2", link2);	

			}	
		}
		mav.addObject("msg", msg);	
		mav.setViewName("qna/msgView");
		return mav;
	}//delete end	
	
	
	
	@RequestMapping(value = "qna/qnaupdate.do", method = RequestMethod.GET)
	public ModelAndView update(@ModelAttribute QnaDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		HttpSession session = req.getSession();
		
		int qna_num=Integer.parseInt(req.getParameter("qna_num"));
		
		if(session.getAttribute("s_mem_lv").toString().equals("A")) {

			dto=dao.read(qna_num);

			mav.addObject("dto", dto);
			mav.setViewName("qna/qnaupdate");
			
		}else {
	        mav.addObject("msg", "답글 수정을");
	        mav.addObject("qna_num", qna_num);
			mav.setViewName("qna/qnaPwCheck");
		}		

		return mav;
	}//update end	

	
	@RequestMapping(value = "qna/qnaupdate.do", method = RequestMethod.POST)
	public ModelAndView updatecheck(@ModelAttribute QnaDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		
		int qna_num=Integer.parseInt(req.getParameter("qna_num"));
		String qna_pw=req.getParameter("qna_pw");
		
		dto=dao.pwcheck(qna_num, qna_pw);
		String msg="";

		
		if(dto==null) {
			msg+="<script>";
			msg+="    alert('비밀번호가 일치하지 않습니다');";
			msg+="    location.href='javascript:history.back();'";
			msg+="</script>";
			mav.addObject("msg", msg);	
			mav.setViewName("qna/msgView");
		}else {
	
			dto=dao.read(qna_num);
			mav.addObject("dto", dto);	
			mav.setViewName("qna/qnaupdate");		
		}

		return mav;
	}//updatecheck end	
	
	
	@RequestMapping(value = "qna/qnaupdateProc.do", method = RequestMethod.POST)
	public ModelAndView updateProc(@ModelAttribute QnaDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		
		int cnt=dao.updateproc(dto);
		String msg="";
		
		if(cnt==0) {
			msg+="<script>";
			msg+="    alert('수정이 실패했습니다');";
			msg+="    location.href='javascript:history.back();'";
			msg+="</script>";
		}else{
			msg="<p>글 수정이 완료 되었습니다</p>";
		}//if end
		
        mav.addObject("msg", msg);
		mav.setViewName("qna/msgView");
			
		return mav;
	}//updateproc end
	
	
	@RequestMapping(value = "qna/qnareply.do", method = RequestMethod.GET)
	public ModelAndView qnareply(HttpServletRequest req, QnaDTO dto) {
		ModelAndView mav=new ModelAndView();
		
		int qna_num=Integer.parseInt(req.getParameter("qna_num"));
		int qna_grpno=Integer.parseInt(req.getParameter("qna_grpno"));
		int qna_indent=Integer.parseInt(req.getParameter("qna_indent"));
		int qna_ansnum=Integer.parseInt(req.getParameter("qna_ansnum"));
		
		dto=dao.read(qna_num);

        mav.addObject("dto", dto);
        mav.addObject("qna_num", qna_num);
        mav.addObject("qna_grpno", qna_grpno);
        mav.addObject("qna_indent", qna_indent);		
        mav.addObject("qna_ansnum", qna_ansnum);
		mav.setViewName("qna/qnaReply");
			
		return mav;
	}//qnareply end

	
	@RequestMapping(value = "qna/replyProc.do", method = RequestMethod.POST)
	public ModelAndView replyProc(HttpServletRequest req, QnaDTO dto) {
		ModelAndView mav=new ModelAndView();
		
		HttpSession session = req.getSession();
		
		String mem_id=session.getAttribute("s_mem_id").toString();
		
		int qna_grpno=Integer.parseInt(req.getParameter("qna_grpno"));
		int qna_indent=Integer.parseInt(req.getParameter("qna_indent"));
		int qna_ansnum=dao.ansnum(qna_grpno);
		
		String ip = req.getRemoteAddr();
		dto.setIp(ip);
		dto.setQna_grpno(qna_grpno);
		dto.setQna_indent(qna_indent+1);
		dto.setQna_ansnum(qna_ansnum+1);
		dto.setMem_id(mem_id);
		
		int cnt=dao.replycreate(dto);
		String msg="";
		
		if(cnt==0) {
			msg+="<script>";
			msg+="    alert('답글 등록이 실패했습니다');";
			msg+="    location.href='javascript:history.back();'";
			msg+="</script>";
		}else{
			msg="<p>답글 등록이 완료 되었습니다</p>";
		}//if end
		
        mav.addObject("msg", msg);
		mav.setViewName("qna/msgView");
			
		return mav;
	}//qnareply end
	
	
	
}//class end
