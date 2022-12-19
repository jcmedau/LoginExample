package com.skypower.login.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Service responsible for the handling of UserRole Objects
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Service
public class UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	public void save(UserRole userRole) {
		userRoleRepository.save(userRole);
	}
	
	public UserRole findByName (String name) {
		return userRoleRepository.findByName(name);
	}
}
