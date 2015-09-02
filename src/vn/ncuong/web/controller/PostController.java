package vn.ncuong.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class PostController {

	@RequestMapping("site/post")
	public ModelAndView login(){
		ModelAndView model = new ModelAndView("post");
		return model;
	}
}
