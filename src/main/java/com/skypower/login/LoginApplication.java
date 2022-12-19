package com.skypower.login;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skypower.login.role.Role;
import com.skypower.login.role.UserRole;
import com.skypower.login.role.UserRoleRepository;
import com.skypower.login.user.User;
import com.skypower.login.user.UserService;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Main Class for the Login Example Application
 *
 * @author J. C. Medau
 * @version 1.0
 */

@SpringBootApplication
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}
	
	/**
	 * This Bean is responsible for creating a first ADMIN User during system deployment on an empty database.
	 * This User must be used for the creation of a real Administrator and deleted afterwards.
	 */	
	
	@Bean
	CommandLineRunner commandLineRunner(UserService users, UserRoleRepository roles, PasswordEncoder encoder) {
		return args -> {			
			UserRole firstRole = new UserRole(Role.ADMIN.name()); 
			if (roles.count() == 0) {
				roles.save(firstRole);
				users.save(new User("Super", "Administrator", "456", "sudo", true, firstRole));
			}
		};
	}
}
