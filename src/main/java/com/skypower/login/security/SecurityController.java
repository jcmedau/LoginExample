package com.skypower.login.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Controller responsible for page access and definition of different types
 * of Users authorizations
 * 
 * During tests, the use of {@code @PreAuthorize("hasRole('ANY_ROLE)")} did
 * not work and was replaced by {@code @PreAuthorize("hasAuthority'ANY_ROLE)")}
 * 
 * The annotation {@Code @EnableMethodSecurity} needs to be used on the
 * SecurityConfiguration Class to make the authorizations work.
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Controller
public class SecurityController {
	
	@GetMapping("/")
	public String showHomePage() {
		return "index.html";
	}
	
	@GetMapping("/login")
	public String doLogin() {
		return "/login.html";
	}
	
	@GetMapping("/logout")
	public String doLogout() {
		return "/logout.html";
	}
	
	@GetMapping("/welcome")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")	// does not work with "hasRole('USER')"
	public String showWelcomePage() {
		return "/user/welcome.html";
	}
	
	@GetMapping("/restricted")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String showRestrictedPage() {
		return "/admin/restricted.html";
	}
}
