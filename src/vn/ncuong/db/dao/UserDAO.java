package vn.ncuong.db.dao;

import java.util.List;

import vn.ncuong.db.entity.User;



public interface UserDAO {
	
	public List<User> getAllUser();
	public User findByUsername(String username);
	
}
