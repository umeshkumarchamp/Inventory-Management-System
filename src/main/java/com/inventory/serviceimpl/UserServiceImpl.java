package com.inventory.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.models.auth.User;
import com.inventory.repository.UserRepository;
import com.inventory.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User getUserById(Long id) {
		Optional<User> checkUser = userRepo.findById(id);
		User user = null; 
		if(checkUser.isPresent()) {
			user = checkUser.get();
			return user;
		}
		return user;
	}

	@Override
	public User userLogin(String username, String password) {
		return userRepo.findByUsernameAndPassword(username, password);
	}

}
