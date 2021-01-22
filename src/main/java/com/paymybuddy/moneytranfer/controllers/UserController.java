package com.paymybuddy.moneytranfer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/user/addConnection")
	public ResponseEntity<String> addNewConnection(@ModelAttribute User user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userFromAuth = userService.getUserFromAuth(auth);
		if (userFromAuth != null) {
			if (userService.findUserByEmail(user.getEmail()) != null) {
				connectionService.createConnection(userFromAuth, user.getEmail());
			} else {
				return new ResponseEntity<String>("The email you entered isn't registered in our system.",
						HttpStatus.BAD_REQUEST);
			}
		}

		return new ResponseEntity<String>("New connection added", HttpStatus.OK);
	}

	@GetMapping("/user/connections")
	public ResponseEntity<?> getMyConnection(User user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userFromAuth = userService.getUserFromAuth(auth);
		if (userFromAuth != null) {
			if (userService.findUserByEmail(user.getEmail()) != null) {
				connectionService.findConnectedUsersByUser(userFromAuth);
			}
		}
		List<User> connectedUsers = connectionService.findConnectedUsersByUser(userFromAuth);
		return new ResponseEntity<>(connectedUsers, HttpStatus.OK);
	}
}