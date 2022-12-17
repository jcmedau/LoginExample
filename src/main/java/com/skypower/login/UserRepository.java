package com.skypower.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <ApplicationUser, Long> {
	
	@Query ("SELECT u FROM ApplicationUser u WHERE u.email = ?1")
	Optional<ApplicationUser> findByEmail (String email);

}
