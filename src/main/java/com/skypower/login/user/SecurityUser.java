package com.skypower.login.user;

import java.sql.Date;
import java.time.LocalDate;
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

@SuppressWarnings("serial")
public class SecurityUser implements UserDetails {
	
	private final User user;
	
	public SecurityUser (User user) {
		this.user = user;	
	}

	/**
	 * Returns the authorities granted to the user. Cannot return <code>null</code>.
	 * @return the authorities, sorted by natural key (never <code>null</code>)
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return user.getRoles();
	}

	/**
	 * Returns the password used to authenticate the user.
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * Returns the username used to authenticate the user. Cannot return
	 * <code>null</code>.
	 * @return the username (never <code>null</code>)
	 */
	@Override
	public String getUsername() {
		return user.getEmail();
	}

	/**
	 * Indicates whether the user's account has expired. An expired account cannot be
	 * authenticated.
	 * @return <code>true</code> if the user's account is valid (ie non-expired),
	 * <code>false</code> if no longer valid (ie expired)
	 */
	@Override
	public boolean isAccountNonExpired() {
		return !Date.valueOf(LocalDate.now()).after(user.getExpiryDate());
	}

	/**
	 * Indicates whether the user is locked or unlocked. A locked user cannot be
	 * authenticated.
	 * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
	 */	
	@Override
	public boolean isAccountNonLocked() {		
		return user.getIsEnabled();
	}

	/**
	 * Indicates whether the user is locked or unlocked. A locked user cannot be
	 * authenticated.
	 * 
	 * For now, the password does not expire, so the method returns the expire state of the user.
	 * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return isAccountNonExpired();
	}

	/**
	 * Indicates whether the user is enabled or disabled. A disabled user cannot be
	 * authenticated.
	 * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
	 */
	@Override
	public boolean isEnabled() {
		return user.getIsEnabled();
	}
}
