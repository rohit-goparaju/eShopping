package com.projects.eShopping.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projects.eShopping.model.User;
import com.projects.eShopping.repo.UserRepo;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	UserRepo repo;
	
	
	public UserDetailsService(UserRepo repo) {
		super();
		this.repo = repo;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User existingUser = repo.findByUsername(username);
		
		return new com.projects.eShopping.model.UserDetails(existingUser);
	}
}
