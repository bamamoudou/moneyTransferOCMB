package com.paymybuddy.moneytranfer.models;

import java.util.Date;

public class Transaction {

	private int id;

	private TransactionType transactionType;

	private Account account;

	private int recipientAccountId;

	private Currency transactionCurrencyId;

	private BankAccount bankAccount;

	private Date createdAt;

	private Double amount;

	private String description;

	private Double fee;

	public Transaction(int id, TransactionType transactionType, Account account, int recipientAccountId,
			Currency transactionCurrencyId, BankAccount bankAccount, Date createdAt, Double amount, String description,
			Double fee) {
		super();
		this.id = id;
		this.transactionType = transactionType;
		this.account = account;
		this.recipientAccountId = recipientAccountId;
		this.transactionCurrencyId = transactionCurrencyId;
		this.bankAccount = bankAccount;
		this.createdAt = createdAt;
		this.amount = amount;
		this.description = description;
		this.fee = fee;
	}

	public Transaction() {
	};

	public Transaction(Account account, int recipientAccountId, Double amount) {
		this.account = account;
		this.recipientAccountId = recipientAccountId;
		this.amount = amount;
	}

	public Transaction(Account account, BankAccount bankAccount, Double amount) {
		this.account = account;
		this.bankAccount = bankAccount;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getRecipientAccountId() {
		return recipientAccountId;
	}

	public void setRecipientAccountId(int recipientAccountId) {
		this.recipientAccountId = recipientAccountId;
	}

	public Currency getTransactionCurrencyId() {
		return transactionCurrencyId;
	}

	public void setTransactionCurrencyId(Currency transactionCurrencyId) {
		this.transactionCurrencyId = transactionCurrencyId;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

}
