package com.skypower.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void save (ApplicationUser user) {
//		String password = user.getPassword();
//		user.setPassword(new BCryptPasswordEncoder().encode(password));
		userRepository.save(user);
	}
	
}
