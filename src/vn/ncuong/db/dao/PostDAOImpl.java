package vn.ncuong.db.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.ncuong.cache.CacheConstants;
import vn.ncuong.db.entity.Post;
@Repository
public class PostDAOImpl extends BaseDAOImpl<Integer, Post> implements PostDAO{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Post> findAllPostWithCache() {
		List<Post> posts = null;
		String cacheName = CacheConstants.SiteGlobal.SITE_GLOBAL_CACHE;
		String valueKey = CacheConstants.PostDAO.FIND_ALL_POST;
		if (cacheHelper.containsKey(cacheName, valueKey)) {
			posts = (List<Post>) cacheHelper.getValueFromCache(cacheName, valueKey);
		} else {
			posts = this.findAll();
			cacheHelper.putToCache(cacheName, valueKey, posts);
		}
		return posts;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Post> findLimitPostOrderById(int position, int numberOfPostInGroup) {
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT p FROM Post p JOIN FETCH  p.category ORDER BY p.postId DESC");
		query.setFirstResult(position);
		query.setMaxResults(numberOfPostInGroup);
		List<Post> posts = query.list();
		return posts;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Post> findLimitPostOrderByIdWithCache(int position, int numberOfPostInGroup) {
		List<Post> posts = null;
		String cacheName = CacheConstants.SiteGlobal.SITE_GLOBAL_CACHE;
		String valueKey = CacheConstants.PostDAO.FIND_LIMIT_POST_ORDER_BY_ID_WITHCACHE;
		if (cacheHelper.containsKey(cacheName, valueKey)) {
			posts = (List<Post>) cacheHelper.getValueFromCache(cacheName, valueKey);
		} else {
			posts = this.findLimitPostOrderById(position, numberOfPostInGroup);
			cacheHelper.putToCache(cacheName, valueKey, posts);
		}
		return posts;
	}

}
