package com.skypower.login.user;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.skypower.login.role.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Data;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Class responsible for the User entity
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Data
@Entity
@Table (name = "users")
public class User implements Serializable {
		
	private static final long serialVersionUID = 1L;

	@Id
	@Column (name = "user_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column (length = 50)
	private String firstName;
	
	@Column (length = 50)
	private String lastName;
	
	@Column (length = 64)
	private String password;
	
	@Column (length = 50, unique = true, nullable = false)
	private String email;
	
	private Boolean isEnabled;
	
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	
	
	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable (name = "users_roles", 
		joinColumns = @JoinColumn (name = "user_id"),
		inverseJoinColumns = @JoinColumn (name = "role_id"))
	private List<UserRole> roles;
	
	/*
	 * This array is used during user update to maintain the assigned roles to the User.
	 * It is not persisted into the database.
	 */
	
	@Transient
	private Boolean[] hasEachRole;
	
	public User() {
		this.roles = new ArrayList<UserRole>();
	}
	
	public User(String firstName, String lastName, String password, String email,
			boolean isEnabled, UserRole role, Date expiryDate) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.isEnabled = isEnabled;
		addRole(role);
		this.expiryDate = expiryDate;
	}
	
	/**
	 * @return true if the User has a certain UserRole; false otherwise
	 * @param The UserRole to be checked
	 */
	public boolean hasRole (UserRole role) {
		return roles.contains(role);
	}
	
	/**
	 * Add a UserRole to the User's list
	 * @param The UserRole to be added.
	 */
	public void addRole(UserRole role) {
		roles.add(role);
	}

	/**
	 * @return a String with all UserRoles separated by comma to be displayed in a interface page.
	 */
	public String getAllRoles() {
		return roles.toString().replaceAll("[\\[\\]]", "");
	}
}
