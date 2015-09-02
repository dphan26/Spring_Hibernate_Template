package vn.ncuong.test.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import vn.ncuong.db.dao.CategoryDAO;
import vn.ncuong.db.entity.Category;

public class TestCategoryDAO {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String[] configFile = new String[]{"/Site/WEB-INF/context/web-context.xml","/Site/WEB-INF/context/view-context.xml","/Site/WEB-INF/context/datasource-transaction-context.xml","/Site/WEB-INF/context/security-context.xml"};
		ApplicationContext context = new FileSystemXmlApplicationContext(configFile);
		CategoryDAO categoryDAO = (CategoryDAO) context.getBean("categoryDAOImpl");
		List<Category> categories = categoryDAO.findAllCategoryWithCache();
		for (Category category : categories) {
			System.out.println(category.getCategoryName());
		}
		System.out.println("-----------------");
		List<Category> categories1 = categoryDAO.findAllCategoryWithCache();
		for (Category category : categories1) {
			System.out.println(category.getCategoryName());
		}
		System.out.println("-------------------------------------");
		Category category = categoryDAO.findById(1);
		System.out.println(category.getCategoryName());
		System.exit(0);
	}
}
