package com.skypower.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
	@Query("SELECT r FROM UserRole r WHERE r.role = ?1")
	UserRole findByName (String name);

}
