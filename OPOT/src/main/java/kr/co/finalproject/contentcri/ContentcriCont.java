package kr.co.finalproject.contentcri;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.finalproject.contlist.ContlistDTO;


@Controller
public class ContentcriCont {
	private ContentcriDAO dao = null;
	
	int cnt = 0, like_check, watch_check, point_check;
	public ContentcriCont() {
		dao = new ContentcriDAO();
		System.out.println("-----ContentcriCont() 객체 생성");
	}

	
	@ResponseBody
	@RequestMapping(value= "/content_crilike.do", method = RequestMethod.POST)
	public void cri_like(HttpServletRequest req, ContentcriDTO dto, HttpServletResponse resp) {
		
		try {
			
			HttpSession session = req.getSession();
			int mcode = Integer.parseInt(req.getParameter("mcode"));
			String mem_id=(String)session.getAttribute("s_mem_id").toString();
			
			dto = new ContentcriDTO();
			
			ContentcriDTO cri_dto = dto;
			cri_dto.setMem_id(mem_id);
			cri_dto.setMcode(mcode);
			ContlistDTO listdto = new ContlistDTO();
			listdto.setMcode(mcode);
			
			//System.out.println(mcode);
			//System.out.println(mem_id);
			
			dto = dao.read(mcode, mem_id);
			
			if(dto == null) {
				like_check = 1;
				cri_dto.setCri_like(like_check);
				dao.insert(cri_dto);
				dao.listlike_update(listdto, like_check);
			}else {
				if(like_check == 1) {
					like_check = 0;
				}
				else { 
					like_check = 1;
				}
				System.out.println(like_check);
				cri_dto.setCri_like(like_check);
				dao.like_update(cri_dto);
				dao.listlike_update(listdto, like_check);
			}
			System.out.println("like_check: "+like_check+" watch_check: "+ watch_check + " point_check: " + point_check);
			
			PrintWriter out = resp.getWriter();
			int result = like_check;
			out.println(result + "");
			out.flush();
			out.close();
			
		} catch (Exception e) {			
			System.out.println("서버 응답 실패");			
		}
		
	}//cri_like() end
	

	@ResponseBody
	@RequestMapping(value= "/content_criwatch.do", method = RequestMethod.POST)
	public void cri_watch(HttpServletRequest req, @ModelAttribute("dto") ContentcriDTO dto, HttpServletResponse resp) {
		try {
			
			HttpSession session = req.getSession();
			int mcode = Integer.parseInt(req.getParameter("mcode"));
			String mem_id=(String)session.getAttribute("s_mem_id").toString();
			
			dto = new ContentcriDTO();
			ContentcriDTO cri_dto = dto;
			cri_dto.setMem_id(mem_id);
			cri_dto.setMcode(mcode);
			
			//System.out.println(mcode);
			//System.out.println(mem_id);
			
			dto = dao.read(mcode, mem_id);
			if(dto == null) {
				watch_check = 1;
				cri_dto.setCri_watch(watch_check);
				dao.insert(cri_dto);
			}else {
				if(watch_check == 1) 
					watch_check = 0;
				else {
					watch_check = 1;
				}
				System.out.println(watch_check);
				cri_dto.setCri_watch(watch_check);
				dao.watch_update(cri_dto);
			}
			
			System.out.println("like_check: "+like_check+" watch_check: "+ watch_check + " point_check: " + point_check);
			
			PrintWriter out = resp.getWriter();
			int result = watch_check;
			out.println(result + "");
			out.flush();
			out.close();
		} catch (Exception e) {
			
			System.out.println("서버 응답 실패");
			
		}
		
	}//cri_watch() end
	
	@ResponseBody
	@RequestMapping(value= "/content_cripoint.do", method = RequestMethod.POST)
	public void cri_point(HttpServletRequest req, ContentcriDTO dto, HttpServletResponse resp) {
		
		try {
			
			HttpSession session = req.getSession();
			int mcode = Integer.parseInt(req.getParameter("mcode"));
			String mem_id=(String)session.getAttribute("s_mem_id").toString();
			
			dto = new ContentcriDTO();
			ContentcriDTO cri_dto = dto;
			cri_dto.setMem_id(mem_id);
			cri_dto.setMcode(mcode);
			
			//System.out.println(mcode);
			//System.out.println(mem_id);
			
			dto = dao.read(mcode, mem_id);
			if(dto == null) {
				point_check = 1;
				cri_dto.setCri_point(point_check);
				dao.insert(cri_dto);
			}else {
				if(point_check == 1) {
					point_check = 0;
				}
				else { 
					point_check = 1;
				}
				System.out.println(point_check);
				cri_dto.setCri_point(point_check);
				dao.point_update(cri_dto);
			}
			
			System.out.println("like_check: "+like_check+" watch_check: "+ watch_check + " point_check: " + point_check);
			
			PrintWriter out = resp.getWriter();
			int result = point_check;
			out.println(result + "");
			out.flush();
			out.close();
			
		} catch (Exception e) {
			
			System.out.println("서버 응답 실패");
			
		}		
		
	}//cri_point() end

	
}//class end