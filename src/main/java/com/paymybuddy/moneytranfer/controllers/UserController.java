package com.paymybuddy.moneytranfer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.services.ConnectionService;
import com.paymybuddy.moneytranfer.services.UserService;

@RestController
public class UserController {
	private UserService userService;
	private ConnectionService connectionService;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserController(UserService userService, ConnectionService connectionService) {
		this.userService = userService;
		this.connectionService = connectionService;
	}

	@GetMapping("/register")
	public User register(@RequestParam String email) {
		return userService.findUserByEmail(email);
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerNewUser(@RequestBody User user) {

		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			return new ResponseEntity<String>("The email you entered is already taken.", HttpStatus.BAD_REQUEST);
			
		} else {

			userService.createUserByRegistration(user);

		}
		return new ResponseEntity<String>("Registration Ok", HttpStatus.OK);
	}

	@GetMapping("/user/addConnection")
	public User getAddConnection(@RequestAttribute User user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userService.getUserFromAuth(auth);
	}

	@PostMapping("/user/addConnection")
	public ResponseEntity<String> createAddNewConnection(User user, BindingResult result, @RequestParam String email) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userFromAuth = userService.getUserFromAuth(auth);
		if (userFromAuth != null) {
			if (userService.findUserByEmail(user.getEmail()) != null) {
				connectionService.createConnection(userFromAuth, user.getEmail());
			} else {
				result.rejectValue("email", "error-addConnection",
						"The email you entered isn't registered in our system.");
			}
		}

		return new ResponseEntity<String>("New connection added", HttpStatus.OK);
	}

}
