package vn.ncuong.db.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.ncuong.cache.CacheHelper;

@SuppressWarnings({ "unchecked" })
public class BaseDAOImpl<K, E> implements BaseDAO<K, E> {
	protected final Log log = LogFactory.getLog(getClass());	
	
	protected CacheHelper cacheHelper = CacheHelper.getInstance();
	protected Class<E> entityClass;
	@Autowired
	protected SessionFactory sessionFactory;

	public BaseDAOImpl() {
		Type type = getClass().getGenericSuperclass();
		if(type instanceof ParameterizedType) {
			ParameterizedType genericSuperclass = (ParameterizedType)type;
			this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
		}
	}

	@Override
	@Transactional
	public List<E> findAll() {
		List<E> entities = sessionFactory.getCurrentSession().createQuery("SELECT e FROM "+entityClass.getName()+" e").list();
		return entities;
	}

	@Override
	@Transactional
	public void add(E entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	@Transactional
	public void update(E entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	@Override
	@Transactional
	public void remove(Integer id) {
		E entity = findById(id);
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	@Transactional
	public E findById(Integer id) {
		E entity = (E) sessionFactory.getCurrentSession().get(entityClass, id);
		return entity;
	}
	
	@Override
	@Transactional
	public E findFirstRow(){
		return null;
	}
	
	@Override
	@Transactional
	public long count(){
		long count = (long) sessionFactory.getCurrentSession().createQuery("select count(*) from "+ entityClass.getName()).uniqueResult();
		return count;
	}

}