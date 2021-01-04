package com.paymybuddy.moneytranfer.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "connection")
@EntityListeners(AuditingEntityListener.class)
public class Connection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_user_id")
	private User user;

	@Column(name = "connected_user_id")
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
