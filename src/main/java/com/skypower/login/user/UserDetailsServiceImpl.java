package com.skypower.login.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This Class implements the UserDetailsService Interface and needs to @Override
 * the loadUserByUsername method
 * 
 * It is used in the SecurityConfiguration Class to authenticate the user upon login
 * 
 *  This application uses the User email as the username. This is the reason for the method
 *  to call the findByEmail Repository method.
 *
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	final UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		 return userRepository.findActiveUser(username)
				 .map(SecurityUser::new)
				 .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}
}
