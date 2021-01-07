package com.paymybuddy.moneytranfer.services;

import org.springframework.security.core.Authentication;

import com.paymybuddy.moneytranfer.models.User;

public interface UserService {

	public User getUserFromAuth(Authentication auth);

	public User findUserById(int id);

	public User findUserByEmail(String email);

	public User createUserByRegistration(User user);

}
