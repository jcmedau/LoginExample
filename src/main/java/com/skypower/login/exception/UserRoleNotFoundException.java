package com.skypower.login.exception;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Exception that should be thrown when a UserRole does not exist in the database
 *
 * @author J. C. Medau
 * @version 1.0
 */

public class UserRoleNotFoundException extends RuntimeException {
	
	public UserRoleNotFoundException (String message) {
		super (message);
	}
}
