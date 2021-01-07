package com.paymybuddy.moneytranfer.services;

import java.util.List;

import com.paymybuddy.moneytranfer.models.Connection;
import com.paymybuddy.moneytranfer.models.User;

public interface ConnectionService {

	public List<Connection> findConnectionsByUser(User user);

	public List<User> findConnectedUsersByUser(User buddy);

	public void createConnection(User buddy, String connectedUserEmail);

}
