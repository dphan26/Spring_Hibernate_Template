package vn.ncuong.test.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import vn.ncuong.db.dao.TestDAO;
import vn.ncuong.db.entity.Test;



public class TestTestDAO {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String[] configFile = new String[]{"/Site/WEB-INF/context/web-context.xml","/Site/WEB-INF/context/view-context.xml","/Site/WEB-INF/context/datasource-transaction-context.xml","/Site/WEB-INF/context/security-context.xml"};
		ApplicationContext context = new FileSystemXmlApplicationContext(configFile);
		TestDAO testDAO = (TestDAO) context.getBean("testDAOImpl");
		List<Test> tests = testDAO.getAllTest();
		for (Test test : tests) {
			System.out.println(test.getId());
			System.out.println(test.getName());
			System.out.println("-----------------------------");
		}
	}
}
