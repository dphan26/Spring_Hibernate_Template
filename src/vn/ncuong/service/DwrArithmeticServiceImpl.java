package vn.ncuong.service;

import org.directwebremoting.annotations.RemoteMethod;
import org.springframework.stereotype.Service;
@Service
public class DwrArithmeticServiceImpl implements DwrArithmeticService{

	@Override
	@RemoteMethod
	public Integer add(Integer num1, Integer num2){
		return num1 + num2;
	}

}
