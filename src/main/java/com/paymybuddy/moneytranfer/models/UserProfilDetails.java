package com.paymybuddy.moneytranfer.models;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserProfilDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;

	private List<GrantedAuthority> authorityList;

	public UserProfilDetails(User user, Role role) {
		this.user = user;
		this.authorityList = Arrays.asList(new SimpleGrantedAuthority(role.getName()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorityList;
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

		return user.isActive();
	}

	@Override
	public boolean isAccountNonLocked() {

		return user.isActive();
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return user.isActive();
	}

	@Override
	public boolean isEnabled() {

		return user.isActive();
	}

	public String getDisplayName() {
		return user.getName();
	}

	public String getAccountBalance() {
		return user.getAccount().getBalance().toString();
	}

}
