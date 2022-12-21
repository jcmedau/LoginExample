package com.skypower.login.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
		return userRoleRepository.findAll(Sort.by(Direction.ASC, "role"));
	}
	
	/**
	 * 
	 * @param name
	 * @return a UseRole found by its name
	 */
	public UserRole findByName (String name) {
		return userRoleRepository.findByName(name);
	}
	
	/**
	 * Saves a UserRole to the database
	 * @param userRole
	 */
	public void save(UserRole userRole) {
		userRoleRepository.save(userRole);
	}
}
