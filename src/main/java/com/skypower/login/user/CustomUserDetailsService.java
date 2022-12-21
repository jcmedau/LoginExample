package com.skypower.login.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * This Class implements the UserDetailsService Interface and needs to @Override
 * the loadUserByUsername method
 * 
 * It is used in the SecurityConfiguration Class to authenticate the user upon login
 * 
 *  This application uses the User email as the username. This is the reason for the method
 *  to call the findByEmail Repository method.
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	final UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		 return userRepository.findActiveUser(username)
				 .map(SecurityUser::new)
				 .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}
}
