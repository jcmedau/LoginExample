package com.skypower.login;

import com.skypower.login.role.Role;
import com.skypower.login.role.UserRole;
import com.skypower.login.role.UserRoleService;
import com.skypower.login.user.User;
import com.skypower.login.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.time.LocalDate;

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
	 * This Bean is responsible for creating all Roles and a first ADMIN User during system
	 * deployment on an empty database. This User must be used for the creation of a real
	 * Administrator and deleted afterwards.
	 */

	@Bean
	CommandLineRunner commandLineRunner(UserService userService,
													UserRoleService roleService) {
		return args -> {
			if (roleService.count() == 0) {
				for (Role role : Role.values()) {
					roleService.save(new UserRole(role));
				}
				userService.encodePasswordAndSave(new User("Super",
																		 "Administrator",
																		 "456",
																		 "sudo",
																		 true,
																		 roleService.findByName(Role.ADMIN.name()),
																		 Date.valueOf(LocalDate.now())));
			}
		};
	}
}
