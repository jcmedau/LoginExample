package com.skypower.login.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Repository for UserRole Objects
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
	/**
	 * Returns a UserRole found by its name
	 * @param name
	 * @return UserRole
	 */
	@Query("SELECT r FROM UserRole r WHERE r.role = ?1")
	UserRole findByName (String name);
}
