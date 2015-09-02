package vn.ncuong.db.dao;

import java.util.List;

import vn.ncuong.db.entity.Test;



public interface TestDAO extends BaseDAO<Integer, Test> {
	public List<Test> getAllTest();
}
