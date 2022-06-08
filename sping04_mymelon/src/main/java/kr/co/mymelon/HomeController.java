package kr.co.mymelon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	public HomeController() {
		System.out.println("-----HomeController() 객체 생성됨");
	}//end
	
	
	//mymelon프로젝트의 첫페이지 호출
	//결과확인 http://localhost:9097/home.do
	
	@RequestMapping("/home.do")
	public ModelAndView home() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/mediagroup/list.do");//redirect : 등록한 명령어를 호출하 수 있다
		return mav;
	}//home() end
	
}//class end
