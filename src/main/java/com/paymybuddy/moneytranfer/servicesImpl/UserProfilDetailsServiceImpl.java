package com.paymybuddy.moneytranfer.servicesImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.models.UserProfilDetails;
import com.paymybuddy.moneytranfer.services.UserService;

public class UserProfilDetailsServiceImpl implements UserDetailsService {

	private UserService userService;

	@Autowired
	public UserProfilDetailsServiceImpl(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Replacing username with email here, since it's the unique identifier i'm
	 * using.
	 */

	@Override
	@Transactional
	public UserProfilDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

		User user = userService.findUserByEmail(userEmail);
		if (user == null) {
			throw new UsernameNotFoundException(userEmail);
		}
		UserProfilDetails userDetails = new UserProfilDetails(user, user.getRole());
		return userDetails;
	}

}
