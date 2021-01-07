package com.paymybuddy.moneytranfer.servicesImpl;

import java.util.ArrayList;
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

		List<User> connectedUsers = new ArrayList<>();
		if (userService.findUserById(buddy.getId()) != null) {
			for (Connection connection : findConnectionsByUser(buddy)) {
				connectedUsers.add(userService.findUserById(connection.getConnectedUserId()));
			}
		}
		return connectedUsers;
	}

	@Override
	public void createConnection(User buddy, String connectedUserEmail) {

		if ((!(buddy.getEmail().equals(connectedUserEmail)))
				&& (userService.findUserByEmail(connectedUserEmail) != null)) {
			List<Connection> connections = connectionRepository.findConnectionsByUser(buddy);
			User connectedUser = userService.findUserByEmail(connectedUserEmail);
			if (connections.isEmpty() || connections.stream().noneMatch(
					connection -> userService.findUserById(connection.getConnectedUserId()).equals(connectedUser))) {
				Connection newConnection = new Connection(buddy, connectedUser.getId());
				connectionRepository.save(newConnection);
			}
		}

	}

}
