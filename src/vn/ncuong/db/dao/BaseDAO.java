package vn.ncuong.db.dao;

import java.util.List;

public interface BaseDAO<K, E> {
	public List<E> findAll();
	public void add(E entity);
	public void update(E entity);
	public void remove(Integer id);
	public E findById(Integer id);
	public E findFirstRow();
	public long count();
}
