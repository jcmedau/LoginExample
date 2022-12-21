package com.skypower.login.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Service for the User Entity
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Long count() {
		return userRepository.count();
	}	
	
	public void delete (Long id) {
		userRepository.deleteById(id);
	}
	
	public List<User> findAll(String column) {
		if (column == null) column = "firstName";
		return userRepository.findAll(Sort.by(Direction.ASC, column));
	}
	
	public User findByEmail (String email) {
		return userRepository.findActiveUser(email).get();
	}
	
	public User findById (Long id) {
		return userRepository.findById(id).get();
	}	
	
	public void save (User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
}
