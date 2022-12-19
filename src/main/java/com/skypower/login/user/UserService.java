package com.skypower.login.user;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public void save (User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}	
	
	public Long count() {
		return userRepository.count();
	}
}
