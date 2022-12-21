package com.skypower.login.user;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
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

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Class responsible for the User entity
 *
 * @author J. C. Medau
 * @version 1.0
 */

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
	
	public boolean hasRole (UserRole role) {
		return roles.contains(role);
	}
	
	public void addRole(UserRole role) {
		roles.add(role);
	}

	public String getAllRoles() {
		StringBuffer sb = new StringBuffer();
		for (UserRole userRole : roles) {
			sb.append(userRole.getRole() + " ");
		}
		return sb.toString().trim().replace(" " , ", ");
	}

	public String getEmail() {
		return email;
	}
	
	public Date getExpiryDate() {
		return expiryDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public Boolean[] getHasEachRole() {
		return hasEachRole;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public Long getUserId() {
		return userId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setHasEachRole(Boolean[] hasEachRole) {
		this.hasEachRole = hasEachRole;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
