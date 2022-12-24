package com.skypower.login.role;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.skypower.login.exception.UserRoleNotFoundException;

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

	private final UserRoleRepository userRoleRepository;
		
	public UserRoleService(UserRoleRepository userRoleRepository) {
		this.userRoleRepository = userRoleRepository;
	}

	/**
	 * @return the number of UserRoles in the database
	 */
	public Long count() {
		return userRoleRepository.count();
	}
	
	/**
	 * @return a List<UserRole> with all UserRole objects in the database
	 */
	public List<UserRole> findAll() {
		return userRoleRepository.findAll(Sort.by(Direction.ASC, "roleName"));
	}
	
	/**
	 * 
	 * @param name
	 * @return a UserRole found by its name or throws and @Code UserRoleNotFoundException if the UserRole does not
	 * exist in the database
	 */
	public UserRole findByName (String name) {
		return userRoleRepository.findByName(name)
				 .orElseThrow(() -> new UserRoleNotFoundException("Role not found: " + name));
	}
	
	/**
	 * Saves a UserRole to the database
	 * @param userRole
	 */
	public void save(UserRole userRole) {
		userRoleRepository.save(userRole);
	}
}
