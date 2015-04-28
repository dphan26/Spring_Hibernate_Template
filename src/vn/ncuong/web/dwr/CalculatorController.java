package vn.ncuong.web.dwr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.ncuong.service.DwrArithmeticService;



@Controller
public class CalculatorController {
	
	@Autowired
	private DwrArithmeticService dwrArithmeticService;
	
	@RequestMapping(value="admin/add") 
	public String getAjaxAddPage(){
		return "admin/test_ajax";
	}
	
	
}
