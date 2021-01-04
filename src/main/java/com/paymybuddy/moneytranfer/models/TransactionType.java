package com.paymybuddy.moneytranfer.models;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "transaction_type")
@EntityListeners(AuditingEntityListener.class)
public class TransactionType {

	@Id
	private int id;

	/**
	 * Transaction types: 1 - PayMyBuddy, 2 - CreditMyAccount, 3 -
	 * TransferToBankAccount
	 */
	private String transactionType;

	public TransactionType(int id, String transactionType) {
		super();
		this.id = id;
		this.transactionType = transactionType;
	}

	public TransactionType(String transactionType) {
		super();
		this.transactionType = transactionType;
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
		return transactionType;
	}

	public void setType(String transactionType) {
		this.transactionType = transactionType;
	}

}
