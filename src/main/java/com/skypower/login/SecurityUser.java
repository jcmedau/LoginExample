package com.skypower.login;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails {
	
	private ApplicationUser user;
	
	public SecurityUser (ApplicationUser user) {
		this.user = user;	
		System.out.println("novo user " + user.getRoles());
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
