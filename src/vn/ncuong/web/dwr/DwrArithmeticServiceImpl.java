package vn.ncuong.web.dwr;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
@RemoteProxy
public class DwrArithmeticServiceImpl implements DwrArithmeticService {

	@Override
	@RemoteMethod
	public Integer add(Integer num1, Integer num2){
		return num1 + num2;
	}

}
