package com.paymybuddy.moneytranfer.models;

import java.util.List;

public class BankAccount {

	private int id;

	private Account account;

	private String bankAccountNumber;

	private List<Transaction> transactions;

	public BankAccount(int id, Account account, String bankAccountNumber, List<Transaction> transactions) {
		super();
		this.id = id;
		this.account = account;
		this.bankAccountNumber = bankAccountNumber;
		this.transactions = transactions;
	}

	public BankAccount(Account account, String bankAccountNumber) {
		super();
		this.account = account;
		this.bankAccountNumber = bankAccountNumber;
	}

	public BankAccount() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
