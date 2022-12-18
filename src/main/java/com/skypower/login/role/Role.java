package com.skypower.login.role;

public enum Role {
	
	ADMIN ("Admin"),
	USER ("User");
	
	String display;
	
	private Role (String display) {
		this.display = display;
	}
	
	public String getDisplay() {
		return display;
	}
}
