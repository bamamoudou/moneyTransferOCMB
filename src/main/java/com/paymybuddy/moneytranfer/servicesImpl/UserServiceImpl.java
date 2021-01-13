package com.paymybuddy.moneytranfer.servicesImpl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.models.UserProfilDetails;
import com.paymybuddy.moneytranfer.repositories.UserRepository;
import com.paymybuddy.moneytranfer.services.AccountService;
import com.paymybuddy.moneytranfer.services.CurrencyService;
import com.paymybuddy.moneytranfer.services.RoleService;
import com.paymybuddy.moneytranfer.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleService roleService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private AccountService accountService;
	private CurrencyService currencyService;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleService roleService,
			BCryptPasswordEncoder bCryptPasswordEncoder, AccountService accountService, CurrencyService currencyService) {
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.accountService = accountService;
		this.currencyService = currencyService;
	}

	@Override
	public User getUserFromAuth(Authentication auth) {
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserProfilDetails userDetails = (UserProfilDetails) auth.getPrincipal();
			User user = findUserByEmail(userDetails.getUsername());
			return user;
		}
		return null;
	}

	@Override
	public User findUserById(int id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			return user;
		}
		return null;
	}

	@Override
	public User findUserByEmail(String email) {

		return userRepository.findUserByEmail(email);
	}

	@Override
	public User createUserByRegistration(User user) {

		User registeredUser = new User();
		registeredUser.setEmail(user.getEmail());
		registeredUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		registeredUser.setName(user.getName());
		registeredUser.setRole(roleService.findRoleTypeByRoleType("User"));
		registeredUser.setActive(true);

		// Creating a new account for the user with 0.00 £ balance
		Account account = new Account(registeredUser, accountService.findAccountTypeByAccountType("User"),
				currencyService.findCurrencyByCurrencyLabel("Euro"), new BigDecimal(0.00));
		accountService.createAccount(account);
		registeredUser.setAccount(account);
		return userRepository.save(registeredUser);
	}

}
