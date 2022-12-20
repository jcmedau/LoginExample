package com.skypower.login.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

	@GetMapping("/admin/allUsers")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String allUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "/admin/all_users.html";
	}
}
