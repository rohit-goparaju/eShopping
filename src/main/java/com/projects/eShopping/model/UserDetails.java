package com.projects.eShopping.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public UserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}	
}
