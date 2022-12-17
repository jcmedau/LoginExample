package com.skypower.login;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	final UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
//		Optional<SecurityUser> su = userRepository.findByEmail(username).map(SecurityUser::new);
//		System.out.println(su);

		 return userRepository.findByEmail(username)
				 .map(SecurityUser::new)
				 .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}
}
