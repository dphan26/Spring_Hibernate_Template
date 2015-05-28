package vn.ncuong.db.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.ncuong.db.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<User> getAllUser() {
		List<User> users = sessionFactory.getCurrentSession()
				.createQuery("SELECT u FROM User u").list();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public User findByUsername(String username) {

		String query = "SELECT u FROM User u JOIN FETCH u.role r WHERE u.username = :username";
		List<User> users = sessionFactory.getCurrentSession()
				.createQuery(query).setParameter("username", username).list();
		return users.size() > 0 ? users.get(0) : null;
	}

}
