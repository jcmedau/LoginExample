package com.skypower.login.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Implementation of the UserDetails Interface based on User Class
 *
 * @author J. C. Medau
 * @version 1.0
 */

public class SecurityUser implements UserDetails {
	
	private User user;
	
	public SecurityUser (User user) {
		this.user = user;	
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return user.getRoles();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.getIsEnabled();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getIsEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.getIsEnabled();
	}

	@Override
	public boolean isEnabled() {
		return user.getIsEnabled();
	}

	@Override
	public String toString() {
		return "SecurityUser [user=" + user + ", getAuthorities()=" + getAuthorities() + ", getPassword()="
				+ getPassword() + ", getUsername()=" + getUsername() + ", isAccountNonExpired()="
				+ isAccountNonExpired() + ", isAccountNonLocked()=" + isAccountNonLocked()
				+ ", isCredentialsNonExpired()=" + isCredentialsNonExpired() + ", isEnabled()=" + isEnabled() + "]";
	}
}
