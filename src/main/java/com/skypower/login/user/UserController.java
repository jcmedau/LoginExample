package com.skypower.login.user;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skypower.login.role.UserRole;
import com.skypower.login.role.UserRoleService;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Controller responsible for User Entity CRUD
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;

	@GetMapping("/admin/allUsers")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String allUsers(@RequestParam(value = "column", required = false) String column, Model model) {
		model.addAttribute("users", userService.findAll(column));
		return "/admin/all_users.html";
	}
	
	/*
	 * The next two methods are responsible for the inclusion of new users in the database
	 */

	@GetMapping("admin/insert")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String showForm (Model model) {
		User user = new User();
		Boolean[] hasRoles = new Boolean[userRoleService.count().intValue()];
		Arrays.fill(hasRoles, false);
		user.setHasEachRole(hasRoles);
		model.addAttribute("user", user);
		
		List<UserRole> allRoles = userRoleService.findAll();
		model.addAttribute("allRoles", allRoles);
		
		return "admin/insert_user.html";
	}

	@RequestMapping (value = "admin/insert", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String doRegistration(@ModelAttribute("user") User user) {
		
		List<UserRole> allRoles = userRoleService.findAll();
		for (int i = 0; i < allRoles.size(); i ++) {
			if (user.getHasEachRole()[i] != null) {
				user.addRole(allRoles.get(i));
			}
		}
		userService.save(user);
		return "redirect:/admin/allUsers";
	}
	
	
	
	
}
