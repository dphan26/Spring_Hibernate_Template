package vn.ncuong.test.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import vn.ncuong.db.dao.UserDAO;
import vn.ncuong.db.entity.User;



public class TestUserDAO {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String[] configFile = new String[]{"/Site/WEB-INF/context/web-context.xml","/Site/WEB-INF/context/view-context.xml","/Site/WEB-INF/context/datasource-transaction-context.xml","/Site/WEB-INF/context/security-context.xml"};
		ApplicationContext context = new FileSystemXmlApplicationContext(configFile);
		UserDAO userDAO = (UserDAO) context.getBean("userDAOImpl");
		List<User> users = userDAO.getAllUser();
		for (User user : users) {
			System.out.println(user.getId());
			System.out.println(user.getUserName());
			System.out.println(user.getFirstName() + " " + user.getLastName());
			System.out.println(user.getPassWord());
			System.out.println("----------------------------");
		}
		
		System.exit(0);
	}
}
