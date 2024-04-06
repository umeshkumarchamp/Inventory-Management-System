package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.models.auth.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsernameAndPassword(String username, String password);
	
}
