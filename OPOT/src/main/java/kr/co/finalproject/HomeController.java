package kr.co.finalproject;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kr.co.finalproject.contlist.ContlistDAO;
import kr.co.finalproject.contlist.WatchListDAO;
import kr.co.finalproject.search.SearchKeyDAO;
import kr.co.finalproject.webmaster.RecommendDAO;

@Controller
public class HomeController {

	public HomeController() {
		System.out.println("-----HomeController() 객체 생성");
	}//constructor end

	//결과확인 http://localhost:9090/home.do
	@RequestMapping("home.do")
	public ModelAndView home() {
		ModelAndView mav=new ModelAndView();
		ContlistDAO dao=new ContlistDAO();
		WatchListDAO watchdao=new WatchListDAO();
		SearchKeyDAO searchdao=new SearchKeyDAO();
		RecommendDAO recdao=new RecommendDAO();
		
		mav.addObject("list", dao.contlistAll());
		mav.addObject("rank", watchdao.rankRead());
		mav.addObject("keywords", searchdao.readRandom());
		mav.addObject("themes", recdao.readRecThemes());

		mav.setViewName("index");
		return mav;
	}//home() end

	
    @RequestMapping(value = "searchsuggest.do", produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String searchsuggest(@RequestParam Map<String, Object> map, Locale locale, Model model) {
    	
    	ArrayList<String> suggestList=null;
    	ContlistDAO contdao = new ContlistDAO();
    	Gson gson = new Gson();
    	
		try {
			  String keyword = (String)map.get("keyword");
			  suggestList=contdao.mainSuggest(keyword);
			  //System.out.println(suggestList);
			  
		}catch (Exception e) {
			System.out.println("응답실패: " + e);
		}
		
		return gson.toJson(suggestList);
		
    }

	
	
}//class end