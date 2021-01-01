package com.paymybuddy.moneytranfer.models;

//@Entity
//@Table(name = "account_type", catalog = "fund_transfer")
//@EntityListeners(AuditingEntityListener.class)
public class AccountType {

	// @Id
	private int id;
	private String accountType;

	public AccountType(int id, String accountType) {
		super();
		this.id = id;
		this.accountType = accountType;
	}

	public AccountType(String accountType) {
		super();
		this.accountType = accountType;
	}

	public AccountType() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
