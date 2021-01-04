package com.paymybuddy.moneytranfer.models;

import java.util.Date;
import java.util.List;

public class User {

	private int id;

	private Role role;

	private String name;

	private String email;

	private String password;

	private Date createdAt;

	private Date updatedAt;

	private boolean isActive;

	private List<Connection> connections;

	private Account account;

	public User(int id, Role role, String name, String email, String password, Date createdAt, Date updatedAt,
			boolean isActive, List<Connection> connections, Account account) {
		super();
		this.id = id;
		this.role = role;
		this.name = name;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isActive = isActive;
		this.connections = connections;
		this.account = account;
	}

	public User(Role role, String name, String email, String password) {
		super();
		this.role = role;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Connection> getConnections() {
		return connections;
	}

	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
