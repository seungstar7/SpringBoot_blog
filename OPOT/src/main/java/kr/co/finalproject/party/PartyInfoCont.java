package kr.co.finalproject.party;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.finalproject.member.SubscribeInfoDAO;
import kr.co.finalproject.member.SubscribeInfoDTO;

	
@Controller
public class PartyInfoCont {
	
	PartyInfoDAO dao=null;
	SubscribeInfoDAO subdao=null;
	PaymentCardDAO carddao=null;

	
	public PartyInfoCont() {
		dao=new PartyInfoDAO(); 
		carddao = new PaymentCardDAO();
		System.out.println("-----PartyInfoCont() 객체 생성");
	}//constructor end
	
	//결과확인 http://localhost:9090/party/partyadd.do
	@RequestMapping(value = "party/partyadd.do",  method = RequestMethod.GET)
	public String partyadd() {
		return "party/partyadd";
	}//partyadd() end
	

	@RequestMapping(value = "party/partyadd.do",  method = RequestMethod.POST)
	public ModelAndView partyadd(HttpServletRequest req, PaymentCardDTO dto) {
		ModelAndView mav=new ModelAndView();
		
		String ott_name=req.getParameter("ott_name");
		String party_role=req.getParameter("party_role");
		int ott_price=0;
		
		//OTT테이블 만들까..?? 로고랑 해서..??
		if(ott_name.equals("netflix")) {
			ott_price=17000;
		}else if(ott_name.equals("tving")) {
			ott_price=13900;
		}else if(ott_name.equals("watcha")) {
			ott_price=12900;
		}else if(ott_name.equals("disney")) {
			ott_price=9900;
		}

		mav.addObject("ott_name", ott_name);
		mav.addObject("ott_price", ott_price);
		
		HttpSession session = req.getSession();

		if(session.getAttribute("s_mem_id")==null || session.getAttribute("s_mem_pw")==null || session.getAttribute("s_mem_lv")==null ) {
			mav.setViewName("m_manage/login");
			String msg="<p>파티 매칭을 위해 로그인해주세요</p>";
			mav.addObject("msg", msg);
		}else {
			if(party_role.equals("party_host")) {
				mav.setViewName("party/host/intro");
			}else if(party_role.equals("party_member")) {
				String mem_id=session.getAttribute("s_mem_id").toString();
				dto=carddao.cardRead(mem_id);
				if(dto==null) {
					mav.setViewName("party/member/cardIns");
				}else {
					mav.addObject("dto", dto);
					mav.setViewName("party/member/cardread");
					int service_fee=500; //파티원 수수료
					
					int payback_amount=0;
					int party_pcost=0;		
					payback_amount=(ott_price/4)*3;
					party_pcost=(ott_price/4)*1+service_fee;
					mav.addObject("mem_id",mem_id);
					mav.addObject("party_pcost", party_pcost);
					mav.addObject("payback_amount", payback_amount);
				}
			}
		}
			
		return mav;
		
	}//partyadd() end
	
	
	@RequestMapping(value = "party/host/payback.do", method = RequestMethod.POST)
	public ModelAndView payback(HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		
		String ott_name=req.getParameter("ott_name");
		int ott_price=Integer.parseInt(req.getParameter("ott_price"));
		
		int service_fee=400; //파티장 수수료
		
		int payback_amount=0;
		int payback_day=0;
		int first_payback_month=0;
		
		payback_amount=(ott_price/4)*3-service_fee;
		
		GregorianCalendar now=new GregorianCalendar();
		payback_day=now.get(Calendar.DATE);
		first_payback_month=now.get(Calendar.MONTH)+2;
		
		mav.addObject("service_fee", service_fee);
		mav.addObject("ott_name", ott_name);
		mav.addObject("ott_price", ott_price);
		mav.addObject("payback_amount", payback_amount);
		mav.addObject("payback_day", payback_day);
		mav.addObject("first_payback_month", first_payback_month);

		mav.setViewName("party/host/payback");
		
		return mav;
		
	}//payback() end
	
	
	
	
	@RequestMapping(value = "party/host/account.do",  method = RequestMethod.POST)
	public ModelAndView account(HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		PartyInfoDTO dto=null;
		
		//String s_mem_id="";//실제로는 session정보 받아올것
		
		HttpSession session = req.getSession();
		String s_mem_id=session.getAttribute("s_mem_id").toString();
		
		dto=dao.readBank(s_mem_id);
		
		if(dto==null) {	
			mav.setViewName("party/host/accountInsert");//등록된 계좌가 없으면 insert로
			
		}else {
			mav.addObject("dto", dto);
			mav.setViewName("party/host/accountUpdate");//등록된 계좌가 있으면 update로
		}
		
		String ott_name=req.getParameter("ott_name");
		int ott_price=Integer.parseInt(req.getParameter("ott_price"));
		int payback_amount=Integer.parseInt(req.getParameter("payback_amount"));
		
		mav.addObject("ott_name", ott_name);
		mav.addObject("ott_price", ott_price);
		mav.addObject("payback_amount", payback_amount);
		
		return mav;
		
	}//account() end
	
	
	
	@RequestMapping(value = "party/host/ottinfo.do",  method = RequestMethod.POST)
	public ModelAndView ottinfo(HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		
		String ott_name=req.getParameter("ott_name");
		int ott_price=Integer.parseInt(req.getParameter("ott_price"));
		int payback_amount=Integer.parseInt(req.getParameter("payback_amount"));
		String bank_name=req.getParameter("bank_name").trim();
		String bank_account=req.getParameter("bank_account").trim();
		
		mav.addObject("ott_name", ott_name);
		mav.addObject("ott_price", ott_price);
		mav.addObject("payback_amount", payback_amount);
		mav.addObject("bank_name", bank_name);
		mav.addObject("bank_account", bank_account);
		
		mav.setViewName("party/host/ottinfo");
		
		return mav;
		
	}//ottinfo() end
	
	
	@RequestMapping(value = "party/host/checkout.do",  method = RequestMethod.POST)
	public ModelAndView checkout(HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		
		HttpSession session = req.getSession();
		String s_mem_id=session.getAttribute("s_mem_id").toString();
		
		String ott_name=req.getParameter("ott_name");
		int ott_price=Integer.parseInt(req.getParameter("ott_price"));
		int payback_amount=Integer.parseInt(req.getParameter("payback_amount"));
		String bank_name=req.getParameter("bank_name").trim();
		String bank_account=req.getParameter("bank_account").trim();
		String ott_id=req.getParameter("ott_id").trim();
		String ott_pw=req.getParameter("ott_pw").trim();
		
		//파티ID생성하기
		//n20220620130255 (ott명 첫글자+현재시각)
		String party_id="";
		party_id += ott_name.substring(0, 1);
		Date now = new Date();
		SimpleDateFormat dateFrm = new SimpleDateFormat("yyyyMMddHHmmss");
		String nowStr = dateFrm.format(now);
		party_id += nowStr;
		
		mav.addObject("ott_name", ott_name);
		mav.addObject("ott_price", ott_price);
		mav.addObject("payback_amount", payback_amount);
		mav.addObject("bank_name", bank_name);
		mav.addObject("bank_account", bank_account);
		mav.addObject("ott_id", ott_id);
		mav.addObject("ott_pw", ott_pw);
		mav.addObject("s_mem_id", s_mem_id);
		mav.addObject("party_id", party_id);
		
		mav.setViewName("party/host/checkout");
		
		return mav;
		
	}//checkout() end
	

	
	//결과확인 http://localhost:9090/party/host/checkoutTest.do
	@RequestMapping("party/host/checkoutTest.do")
	public String checkoutTest() {
		return "party/host/checkout";
	}//checkoutTest() end
	
	
	
	@RequestMapping("party/host/create.do")
	public ModelAndView create(@ModelAttribute PartyInfoDTO dto, SubscribeInfoDTO subdto, PartyWaitingDTO waitdto) {

		ModelAndView mav=new ModelAndView();
		mav.setViewName("party/host/msgView");
		
		int cnt=dao.insert(dto);
		String msg="";
	
		if(cnt==0) {
			
			msg+="<script>";
			msg+="    alert('수정 실패\\n정보를 다시 확인해 주세요');";
			msg+="    location.href='javascript:history.back();'";
			msg+="</script>";

		
		}else {
			
			msg+="<script>";
			msg+="    alert('파티 등록 및 대기인원 자동매칭 되었습니다');";
			msg+="    $(location).attr('href', 'http://localhost:9090/home.do');";	 
			msg+="</script>";
			
			//구독 OTT정보 행추가
			subdao=new SubscribeInfoDAO();
			
			String subscribe_no="";
			
			Date now = new Date();
			SimpleDateFormat dateFrm = new SimpleDateFormat("yyyyMMdd");
			String nowStr = dateFrm.format(now);
			
			subscribe_no=subdao.subnoCreate(nowStr);
			
			subdto.setSubscribe_no(subscribe_no);
			
			int subcnt=subdao.subinsert(subdto);
			if(subcnt==0) {
				System.out.println("구독정보 등록 실패");
			}else{
				System.out.println("구독정보 등록 성공");
			}
			
			//대기리스트에 현재 파티장이 만든 ott를 희망하는 파티원이 있을 시 매칭시키기
			//파티원 매칭이 성공하면 파티정보의 매칭인원은 1명 증가시키고 대기리스트에서는 삭제함
			//매칭인원이 4명이 될 때까지 반복
			int matching_no = dao.matchingNoRead(dto);
			
			System.out.println("매칭인원 : "+matching_no);
			
			while(matching_no<4) {
				int waiting_no=dao.autoMatching(dto, waitdto);
				if(waiting_no==0) {
					break;
				}
				matching_no++;
			}
			
		}//if end
		mav.addObject("msg", msg);
		return mav;
	
	}//create() end
	
	
	@RequestMapping(value = "/party/partyajax.do", method = RequestMethod.GET)
	@ResponseBody
	public String message(HttpServletRequest req) {		
		String ott_name="";
		try {			
			ott_name=req.getParameter("ott_name");			
		} catch (Exception e) {
			System.out.println("응답실패 : " + e);
		}//end
		System.out.println(ott_name);
		return ott_name;
	}//message() end	
	

	
	
	
	
}//class end
