package com.skypower.login.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skypower.login.role.Role;
import com.skypower.login.role.UserRole;
import com.skypower.login.role.UserRoleService;
import com.skypower.login.user.User;
import com.skypower.login.user.UserService;

@SpringBootTest
public class MyTests {

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	UserService userService;

	@Test
	public void createRoles() {

		Role[] allRoles = Role.values();
		for (Role role : allRoles) {
			if (userRoleService.findByName(role.name()) == null) {
				UserRole user = new UserRole();
				user.setRole(role.name());
				userRoleService.save(user);
			}
		}
	}

	@Test
	public void createUsers() {
		User simpleUser = new User();
		simpleUser.setEmail("user");
		simpleUser.setFirstName("Simple");
		simpleUser.setLastName("User");
		simpleUser.setPassword("123");
		simpleUser.setIsEnabled(true);
		simpleUser.addRole(userRoleService.findByName("USER"));

		User adminUser = new User();
		adminUser.setEmail("admin");
		adminUser.setFirstName("Admin");
		adminUser.setLastName("Super");
		adminUser.setPassword("456");
		adminUser.setIsEnabled(true);
		adminUser.addRole(userRoleService.findByName("USER"));
		adminUser.addRole(userRoleService.findByName("ADMIN"));

		System.out.println("Vou salvar...");

		userService.save(simpleUser);
		userService.save(adminUser);

	}
}
