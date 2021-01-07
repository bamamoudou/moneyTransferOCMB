package com.paymybuddy.moneytranfer.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.paymybuddy.moneytranfer.models.Connection;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.repositories.ConnectionRepository;
import com.paymybuddy.moneytranfer.services.ConnectionService;
import com.paymybuddy.moneytranfer.services.UserService;

public class ConnectionServiceImpl implements ConnectionService {

	private ConnectionRepository connectionRepository;
	private UserService userService;

	@Autowired
	public ConnectionServiceImpl(ConnectionRepository connectionRepository, UserService userService) {
		this.connectionRepository = connectionRepository;
		this.userService = userService;
	}

	@Override
	public List<Connection> findConnectionsByUser(User user) {

		return connectionRepository.findConnectionsByUser(user);
	}

	@Override
	public List<User> findConnectedUsersByUser(User buddy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createConnection(User buddy, String connectedUserEmail) {
		// TODO Auto-generated method stub

	}

}
