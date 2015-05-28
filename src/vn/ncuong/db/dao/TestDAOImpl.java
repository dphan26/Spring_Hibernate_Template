package vn.ncuong.db.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.ncuong.db.entity.Test;


@Repository
public class TestDAOImpl implements TestDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Test> getAllTest() {
		List<Test> tests = sessionFactory.getCurrentSession().createQuery("SELECT t FROM Test t").list();
		return tests;
	}

}
