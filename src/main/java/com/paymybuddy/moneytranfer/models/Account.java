package com.paymybuddy.moneytranfer.models;

import java.util.List;

public class Account {
	private int id;

	private User user;

	private AccountType accountType;

	private Currency currency;

	private Double balance;

	private List<Transaction> transactions;

	private BankAccount bankAccount;

	public Account(int id, User user, AccountType accountType, Currency currency, Double balance,
			List<Transaction> transactions, BankAccount bankAccount) {
		super();
		this.id = id;
		this.user = user;
		this.accountType = accountType;
		this.currency = currency;
		this.balance = balance;
		this.transactions = transactions;
		this.bankAccount = bankAccount;
	}

	public Account(User user, AccountType accountType, Currency currency, Double balance) {
		super();
		this.user = user;
		this.accountType = accountType;
		this.currency = currency;
		this.balance = balance;
	}

	public Account() {

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

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

}
