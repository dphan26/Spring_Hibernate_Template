package vn.ncuong.db.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.ncuong.cache.CacheConstants;
import vn.ncuong.db.entity.Category;
@Repository
public class CategoryDAOImpl extends BaseDAOImpl<Integer, Category> implements CategoryDAO{
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Category> findAllCategoryWithCache() {
		List<Category> categories = null;
		String cacheName = CacheConstants.SiteGlobal.SITE_GLOBAL_CACHE;
		String valueKey = CacheConstants.CategoryDAO.FIND_ALL_CATEGORY;
		if (cacheHelper.containsKey(cacheName, valueKey)) {
			categories = (List<Category>) cacheHelper.getValueFromCache(cacheName, valueKey);
		} else {
			categories = this.findAll();
			cacheHelper.putToCache(cacheName, valueKey, categories);
		}
		return categories;
	}

}
