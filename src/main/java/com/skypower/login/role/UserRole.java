package com.skypower.login.role;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Class responsible for Users Roles
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Entity
@Table(name = "roles")
public class UserRole implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@Column (name = "role_name", length = 20, unique = true, nullable = false)
	private String role;
	
	/**
	 * No arguments constructor
	 */
	public UserRole() {
		
	}

	/**
	 * Constructor
	 * @param String representing the Role
	 */
	public UserRole (String role) {
		this.role = role;
	}	
	
	/**
	 * This method overrides the {@code getAuthority()} method from the
	 * {@code GRantedAuthority} Interface
	 */
	@Override
	public String getAuthority() {
		return role;
	}

	/**
	 * @return the UserRole id
	 */
	public Long getId() {
		return roleId;
	}

	/**
	 * @return the Role name
	 */
	public String getRole() {
		return role;
	}
	
	/**
	 * Set the UserRole id
	 * @param id
	 */
	public void setId(Long id) {
		this.roleId = id;
	}

	/**
	 * Set the UserRole name
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Return a String representation of the UserRole
	 */
	@Override
	public String toString() {
		return role;
	}
}
