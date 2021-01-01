package com.paymybuddy.moneytranfer.models;

import java.util.List;

import com.sun.istack.NotNull;

//@Entity
//@Table(name = "account")
//@EntityListeners(AuditingEntityListener.class)
public class Account {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "fk_account_user_id")
	private User user;

//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "fk_account_type_id")
	private AccountType accountType;

//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "fk_account_currency_id")
	private Currency currency;

//	@NotNull
	private Double balance;

	// @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade =
	// CascadeType.ALL)
	private List<Transaction> transactions;

	// @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade =
	// CascadeType.ALL)
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

	public Account(User user, AccountType accountType, Currency currency, @NotNull Double balance) {
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
