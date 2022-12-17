package com.skypower.login.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skypower.login.ApplicationUser;
import com.skypower.login.Role;
import com.skypower.login.UserRole;
import com.skypower.login.UserRoleService;
import com.skypower.login.UserService;

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
			UserRole user = new UserRole();
			user.setRole(role.name());
			userRoleService.save(user);
		}
	}
	
	@Test
	public void createUsers() {
		ApplicationUser simpleUser = new ApplicationUser();
		simpleUser.setEmail("user");
		simpleUser.setFirstName("Simple");
		simpleUser.setLastName("User");
		simpleUser.setPassword("123");		
		simpleUser.setIsEnabled(true);
		simpleUser.addRole(userRoleService.findByName("USER"));
		
		ApplicationUser adminUser = new ApplicationUser();
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
