package com.skypower.login.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
	
	@GetMapping("/")
	public String showHomePage() {
		return "index.html";
	}
	
	@GetMapping("/welcome")
	@PreAuthorize("hasAuthority('USER')")	// does not work with "hasRole('USER')"
	public String showWelcomePage() {
		return "/user/welcome.html";
	}
	
	@GetMapping("/restricted")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String showRestrictedPage() {
		return "/admin/restricted.html";
	}
}
