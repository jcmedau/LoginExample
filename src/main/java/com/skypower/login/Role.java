package com.skypower.login;

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
