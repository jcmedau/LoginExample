package com.skypower.login.role;

import com.skypower.login.exception.UserRoleNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

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

	public Long count() {
		return userRoleRepository.count();
	}

	public List<UserRole> findAll() {
		return userRoleRepository.findAll(Sort.by(Direction.ASC, "roleName"));
	}

	public UserRole findByName (String name) {
		return userRoleRepository.findByName(name)
				 .orElseThrow(() -> new UserRoleNotFoundException("Role not found: " + name));
	}

	public void save(UserRole userRole) {
		userRoleRepository.save(userRole);
	}
}
