package kr.co.finalproject.party;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PartyMemCont {

	PaymentCardDAO dao=new PaymentCardDAO();
	PartyWaitingDAO partydao = new PartyWaitingDAO();
	PartyInfoDAO pinfodao = new PartyInfoDAO();
	PartyMemberDAO pmemdao = new PartyMemberDAO();

	public PartyMemCont() {
		System.out.println("-----PartyMemCont() 객체 생성");
	}//end



	@RequestMapping(value = "party/member.do" , method = RequestMethod.POST)
	public ModelAndView memberaccount(@ModelAttribute PaymentCardDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
			
		String ott_name=req.getParameter("ott_name");
		int ott_price=Integer.parseInt(req.getParameter("ott_price"));
		
		int service_fee=500; //파티원 수수료
		
		int payback_amount=0;
		int party_pcost=0;
		
		payback_amount=(ott_price/4)*3;
		
		party_pcost=(ott_price/4)*1+service_fee;
		
		mav.addObject("ott_name", ott_name);
		mav.addObject("ott_price", ott_price);
		mav.addObject("party_pcost", party_pcost);
		mav.addObject("payback_amount", payback_amount);
		
		//String mem_id="kimkim12";//session정보 받아오기
		HttpSession session = req.getSession();
		String s_mem_id=session.getAttribute("s_mem_id").toString();
		
		String card_m = req.getParameter("card_m");
		String card_y = req.getParameter("card_y");
		String card_exp= card_m + "/" + card_y;
		dto.setCard_exp(card_exp);
		dto.setMem_id(s_mem_id);
		
		mav.addObject("mem_id",s_mem_id);

		int cnt=dao.cardIns(dto);
		if(cnt==0) {
            String msg="<p>카드 등록 실패</p>";
            mav.addObject("msg", msg);
            mav.setViewName("party/member/msgView");
		}else {
			mav.setViewName("party/member/memberMatch");
		}//if end
		return mav;
	}//memberMatch() end
	
	
	@RequestMapping(value = "party/membermatch.do" , method = RequestMethod.POST)
	public ModelAndView memberMatchWait(@ModelAttribute  PartyWaitingDTO dto, PartyInfoDTO pinfodto , PartyMemberDTO pmemdto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();		
		
		String ott_name=req.getParameter("ott_name");
		int ott_price=Integer.parseInt(req.getParameter("ott_price"));
		String mem_id=req.getParameter("mem_id");//세션변수
		int party_pcost=Integer.parseInt(req.getParameter("party_pcost"));

		Date now = new Date();
	    SimpleDateFormat dateFrm = new SimpleDateFormat("yyyyMMddHHmmss");
	    String nowStr = dateFrm.format(now);
	    
		PartyWaitingDAO waitdao=new PartyWaitingDAO();
		String party_ordnumber=waitdao.ordnoCreate(nowStr);		

	    pmemdto.setParty_ordnumber(party_ordnumber);
	    pmemdto.setParty_pcost(party_pcost);
	    pmemdto.setMem_id(mem_id);//세션으로 아이디 가져오기	    
		
		mav.addObject("ott_name", ott_name);
		mav.addObject("ott_price", ott_price);
		mav.addObject("party_pcost", party_pcost);
		mav.addObject("party_ordnumber", party_ordnumber);
		mav.addObject("mem_id",mem_id);
		
		dto.setMem_id(mem_id);//세션으로 아이디 가져오기
		
		pinfodto=pinfodao.read(ott_name);
		if(pinfodto==null){
			int cnt=partydao.memberwait(dto);
			if(cnt==0) {
	            String msg="<h3>매칭 대기 실패</h3>";
	            mav.addObject("msg", msg);
	            mav.setViewName("party/member/msgView");
			}else {
	            mav.setViewName("party/member/memberMatching");
			}//if end
		}else {
			int cntmatch=pinfodao.match(pinfodto);
			if(cntmatch==0) {
				String msg="<p>매칭 실패</p>";
	            mav.addObject("msg", msg);
	            mav.setViewName("party/member/msgView");
			}else {
				int result=pmemdao.ordersheet(pinfodto, pmemdto);
				if(result==0){
					String msg="<p>매칭만 성공 되었습니다 주문서 발급은 아직입니다</p>";
		            mav.addObject("msg", msg);
		            mav.setViewName("party/member/msgView");
				}else {
		            mav.setViewName("party/member/memberMatched");
				}//if end
			}//if end
			
		}//if end			
		
		return mav;
	}//memberMatch end

	
	
	@RequestMapping(value = "party/cardupdate.do" , method = RequestMethod.POST)
	public ModelAndView cardupdate(@ModelAttribute PaymentCardDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		HttpSession session = req.getSession();
		String mem_id=session.getAttribute("s_mem_id").toString();

		dto=dao.cardRead(mem_id);
		mav.addObject("dto", dto);
		mav.setViewName("party/member/cardupdate");
		return mav;
	}//cardupdate() end

	
	@RequestMapping(value = "party/cardupdateproc.do" , method = RequestMethod.POST)
	public ModelAndView cardupdateproc(@ModelAttribute PaymentCardDTO dto, HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		HttpSession session = req.getSession();
		String s_mem_id=session.getAttribute("s_mem_id").toString();

		String card_m = req.getParameter("card_m");
		String card_y = req.getParameter("card_y");
		String card_exp= card_m + "/" + card_y;
		dto.setCard_exp(card_exp);
		dto.setMem_id(s_mem_id);
		String msg="";

		mav.addObject("mem_id",s_mem_id);

		int cnt=dao.updatecard(dto);
		if(cnt==0) {
			msg+="<script>";
			msg+="    alert('수정 실패\\n정보를 다시 확인해 주세요');";
			msg+="    location.href='javascript:history.back();'";
			msg+="</script>";
		}else {
			msg+="<script>";
			msg+="    alert('카드정보가 변경 되었습니다');";
			msg+="    location.href='javascript:history.go(-2);'";
			msg+="</script>";
		}//if end
		mav.setViewName("party/member/msgView");
		mav.addObject("msg", msg);
		return mav;
	}//cardupdateproc() end
	

}//class end