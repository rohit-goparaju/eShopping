package com.projects.eShopping.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.eShopping.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	public User findByUsername(String username);
}
