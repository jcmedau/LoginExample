package com.skypower.login.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

	@Query("SELECT r FROM UserRole r WHERE r.roleName = ?1")
	Optional<UserRole> findByName (String name);
}
