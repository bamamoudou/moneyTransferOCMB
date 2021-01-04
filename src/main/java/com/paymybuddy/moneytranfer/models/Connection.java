package com.paymybuddy.moneytranfer.models;

public class Connection {

	private int id;

	private User user;

	private int connectedUserId;

	public Connection(int id, User user, int connectedUserId) {
		super();
		this.id = id;
		this.user = user;
		this.connectedUserId = connectedUserId;
	}

	public Connection(User user, int connectedUserId) {
		super();
		this.user = user;
		this.connectedUserId = connectedUserId;
	}

	public Connection() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getConnectedUserId() {
		return connectedUserId;
	}

	public void setConnectedUserId(int connectedUserId) {
		this.connectedUserId = connectedUserId;
	}

}
