package com.skypower.login;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRole implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@Column (length = 20, unique = true, nullable = false)
	private String role;

	@Override
	public String getAuthority() {
		return role;
	}

	public Long getId() {
		return roleId;
	}

	public String getRole() {
		return role;
	}
	
	public void setId(Long id) {
		this.roleId = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return role;
	}
}
