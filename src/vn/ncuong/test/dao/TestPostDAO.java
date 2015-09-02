package vn.ncuong.test.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import vn.ncuong.db.dao.PostDAO;
import vn.ncuong.db.entity.Post;

public class TestPostDAO {
	public static void main(String[] args) {
		String[] configFile = new String[]{"/Site/WEB-INF/context/web-context.xml","/Site/WEB-INF/context/view-context.xml","/Site/WEB-INF/context/datasource-transaction-context.xml","/Site/WEB-INF/context/security-context.xml"};
		ApplicationContext context = new FileSystemXmlApplicationContext(configFile);
		PostDAO postDAO = (PostDAO) context.getBean("postDAOImpl");
		List<Post> posts = postDAO.findAllPostWithCache();
		for (Post post : posts) {
			System.out.println(post.getPostTitle());
		}
		System.out.println("---------------------------------");
		List<Post> posts1 = postDAO.findAllPostWithCache();
		for (Post post : posts1) {
			System.out.println(post.getPostTitle());
		}
		
		
		System.out.println("========================================");
		List<Post> post3 = postDAO.findLimitPostWithOrderById(0, 5);
		for (Post post : post3) {
			System.out.println(post.getPostId());
			System.out.println(post.getPostTitle());
			System.out.println(post.getPostMeta());
			System.out.println("--");
		}
		System.out.println("========");
		System.out.println("count: "+postDAO.count());
		System.exit(0);
	}
}
