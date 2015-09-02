package vn.ncuong.db.dao;

import java.util.List;

import vn.ncuong.db.entity.Category;

public interface CategoryDAO extends BaseDAO<Integer, Category>{
	public List<Category> findAllCategoryWithCache();
}
