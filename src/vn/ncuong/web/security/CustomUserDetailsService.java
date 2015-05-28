package vn.ncuong.web.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vn.ncuong.db.dao.UserDAO;
import vn.ncuong.db.entity.User;

public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDAO.findByUsername(username);
		UserDetails userDetails = null;
		if(null != user){
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());
			authorities.add(authority);
			userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
			
		} else {
			throw new UsernameNotFoundException(String.format("Could not find any user with username: %s", username));
		}
		
		return userDetails;
	}

}
