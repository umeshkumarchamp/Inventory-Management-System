package com.inventory.services;

import com.inventory.models.auth.User;

public interface UserService {
	public User getUserById(Long id);
	
	public User userLogin(String username, String password);
}
