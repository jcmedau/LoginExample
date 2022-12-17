package com.skypower.login;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/")
	public String showHomePage() {
		return "index.html";
	}
	
//	@GetMapping("/login")
//	public String showLoginPage(Model model) {
//		UserCredentials credentials = new UserCredentials();
//		model.addAttribute(credentials);
//		return "login.html";
//	}
//	
//	@PostMapping(value = "login")
//	public String receiveCredentials (@ModelAttribute ("userCredentials") UserCredentials credentials) {
//		return "index.html";
//	}
	
	@GetMapping("/welcome")
	@PreAuthorize("hasAuthority('USER')")
	public String showWelcomePage() {
		return "/user/welcome.html";
	}
	
	@GetMapping("/restricted")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String showRestrictedPage() {
		return "/admin/restricted.html";
	}
}
