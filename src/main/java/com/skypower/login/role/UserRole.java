package com.skypower.login.role;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Class responsible for Users Roles
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "roles")
public class UserRole implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@Column (name = "role_name", length = 20, unique = true, nullable = false)
	private String roleName;
	
	/*
	 * The display property is a String representation of the Role in a human readable format
	 */
	@Column (length = 30)
	private String display;
	
	public UserRole (Role role) {
		this.roleName = role.name();
		this.display = role.getDisplay();
	}
		
	/**
	 * This method overrides the @Code getAuthority() method from the @Code GrantedAuthority Interface
	 */
	@Override
	public String getAuthority() {
		return roleName;
	}

	/**
	 *  
	 * @return a String representation of the UserRole
	 */
	@Override
	public String toString() {
		return roleName;
	}
}
