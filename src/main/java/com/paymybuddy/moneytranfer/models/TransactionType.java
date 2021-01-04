package com.paymybuddy.moneytranfer.models;

public class TransactionType {

	private int id;
   
	/**
	 * Transaction types: 1 - PayMyBuddy, 2 - CreditMyAccount, 3 - TransferToBankAccount
	 */
	private String type;

	public TransactionType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public TransactionType(String type) {
		super();
		this.type = type;
	}

	public TransactionType() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
