package com.skypower.login.user;

import com.skypower.login.role.UserRole;
import com.skypower.login.role.UserRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

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
		
	private final UserService userService;
	private final UserRoleService userRoleService;

	public UserController(UserService userService, UserRoleService userRoleService) {
		this.userService = userService;
		this.userRoleService = userRoleService;
	}

	/**
	 * Displays the pages showing a list of all Users
	 */
	@GetMapping("/admin/allUsers")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String showAllUsers(
			@RequestParam(value = "column", required = false) String column,
																			  Model model) {
		model.addAttribute("users", userService.findAll(column));
		return "admin/all_users.html";
	}
	
	/*
	 * The next two methods are responsible for the inclusion of new users in the database
	 */

	@GetMapping("admin/insert")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String showCreateForm (Model model) {
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
		userService.encodePasswordAndSave(user);
		return "redirect:/admin/allUsers";
	}
		
	/*
	 * The next two methods are responsible for the user update in the database
	 */
	
	@GetMapping("admin/update/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String showUpdateForm (@PathVariable("userId") Long id, Model model) {
		User user = userService.findById(id);
		List<UserRole> allRoles = userRoleService.findAll();
		Boolean[] hasRoles = new Boolean[userRoleService.count().intValue()];
		
		for (int i = 0; i < allRoles.size(); i ++) {
			hasRoles[i] = user.hasRole(allRoles.get(i));
		}
		user.setHasEachRole(hasRoles);
		model.addAttribute("user", user);
		model.addAttribute("allRoles", allRoles);
		return "admin/update_user.html";
	}
	
	@PostMapping ("admin/update/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String doUpdate(@ModelAttribute("user") User user) {
		List<UserRole> allRoles = userRoleService.findAll();
		for (int i = 0; i < allRoles.size(); i ++) {
			if (user.getHasEachRole()[i] != null) {
				user.addRole(allRoles.get(i));
			}
		}
		userService.save (user);
		return "redirect:/admin/allUsers";
	}
	
	/*
	 * The next two methods are responsible for the user password reset
	 */
	
	@GetMapping("admin/reset/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String showResetForm (@PathVariable("userId") Long id, Model model) {
		User user = userService.findById(id);		
		model.addAttribute("user", user);		
		return "admin/reset_password.html";
	}
	
	@PostMapping ("admin/reset/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String doReset(@ModelAttribute("user") User user) {
		String newPassword = user.getPassword();
		User retrievedUser = userService.findById(user.getUserId());
		retrievedUser.setPassword(newPassword);
		userService.encodePasswordAndSave(retrievedUser);
		return "redirect:/admin/allUsers";
	}
	
	/*
	 * This method deletes a user from the database
	 */

	@GetMapping("/admin/delete/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String delete (@PathVariable("userId") Long id) {
		userService.delete(id);
		return "redirect:/admin/allUsers";
	}
}
