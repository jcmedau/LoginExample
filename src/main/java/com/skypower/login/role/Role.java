package com.skypower.login.role;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Enumerator used to define all type of users with different privileges levels
 *
 * @author J. C. Medau
 * @version 1.0
 */

// GIT test

public enum Role {
	
	ADMIN ("Admin"),
	USER ("User"),
	TCI ("TCI"),
	TCE ("TCE"),
	RECORDS ("Records"),
	SCHEDULE ("Schedule"),
	;
	
	final String display;
	
	Role (String display) {
		this.display = display;
	}
	
	public String getDisplay() {
		return display;
	}
}
