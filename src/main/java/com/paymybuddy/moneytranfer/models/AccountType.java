package com.paymybuddy.moneytranfer.models;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "account_type", catalog = "fund_transfer")
@EntityListeners(AuditingEntityListener.class)
public class AccountType {

	@Id
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
