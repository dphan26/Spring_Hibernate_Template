package vn.ncuong.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class TestController {
	
	@RequestMapping("admin/test")
	ModelAndView testAdmin(){
		ModelAndView model = new ModelAndView("admin/test");
		return model;
	}
	
	@RequestMapping("site/test")
	ModelAndView testSite(){
		ModelAndView model = new ModelAndView("site/test");
		return model;
	}
	
}
