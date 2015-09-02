package vn.ncuong.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class CalculatorController {
	@RequestMapping("site/calculator")
	public ModelAndView login(){
		ModelAndView model = new ModelAndView("calculator");
		return model;
	}

}
