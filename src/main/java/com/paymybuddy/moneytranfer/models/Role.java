package com.paymybuddy.moneytranfer.models;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "role")
@EntityListeners(AuditingEntityListener.class)
public class Role {

	@Id
	private int id;
	/**
	 * Role type: 1 Admin 2 User
	 */

	private String roleType;

	public Role(int id, String roleType) {
		super();
		this.id = id;
		this.roleType = roleType;
	}

	public Role(String roleType) {
		super();
		this.roleType = roleType;
	}

	public Role() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return roleType;
	}

	public void setName(String roleType) {
		this.roleType = roleType;
	}
}