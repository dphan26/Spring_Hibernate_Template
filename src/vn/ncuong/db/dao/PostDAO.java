package vn.ncuong.db.dao;

import java.util.List;

import vn.ncuong.db.entity.Post;

public interface PostDAO extends BaseDAO<Integer, Post>{
	public List<Post> findAllPostWithCache();
	public List<Post> findLimitPostWithOrderById(int position, int itemPerGroup);
}
