package com.skypower.login.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
