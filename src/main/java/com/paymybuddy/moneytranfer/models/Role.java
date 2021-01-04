package com.paymybuddy.moneytranfer.models;

public class Role {

	private int id;
	/**
	 * Role name: 1 Admin 2 User
	 */

	private String name;

	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Role(String name) {
		super();
		this.name = name;
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
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
