package com.skypower.login.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * This Class implements the UserDetailsService Interface and needs to override the loadUserByUsername method.
 * 
 * It is used in the SecurityConfiguration Class to authenticate the user upon login.
 * 
 *  This application uses the User email as the username. This is the reason for the method to call the 
 *  @Code findByEmail Repository method.
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	final UserService userService;
	
	public CustomUserDetailsService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Locates the user based on the username. Throws a @Code UsernameNotFoundException if the user does not exist.
	 * 
	 * @param username
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 return userService.findActiveUser(username)
				 .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}
}
