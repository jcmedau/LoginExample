package com.skypower.login.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Repository for the User Entity
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

	@Query ("SELECT u FROM User u WHERE u.email = ?1 AND u.isEnabled = true AND u.expiryDate >= CURRENT_DATE")
	Optional<User> findActiveUser (String email);
}
