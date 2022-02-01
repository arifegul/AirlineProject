package com.airline.service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.airline.model.User;
import com.airline.repository.RoleRepository;
import com.airline.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepo;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User saveOneUser(User newUser) {
		newUser.setRoles(new HashSet<>(roleRepo.findAll()));
		return userRepository.save(newUser);
	}

	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User updateOneUser(Long userId, User newUser) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUsername(newUser.getUsername());
			foundUser.setPassword(newUser.getPassword());
			userRepository.save(foundUser);
			return foundUser;
		}else {
			return null;
		}
	}

	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
	}

	public User getOneUserByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}
	
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
	

	
}