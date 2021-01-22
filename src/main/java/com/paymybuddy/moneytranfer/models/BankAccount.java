package com.paymybuddy.moneytranfer.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sun.istack.NotNull;

@Entity
@Table(name = "bank_account")
@EntityListeners(AuditingEntityListener.class)
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_account_id")
	private Account account;

	@NotNull
	private String bankAccountNumber;

	@OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Transaction> transactions;

	public BankAccount(int id, Account account, String bankAccountNumber, List<Transaction> transactions) {
		super();
		this.id = id;
		this.account = account;
		this.bankAccountNumber = bankAccountNumber;
		this.transactions = transactions;
	}

	public BankAccount(Account account, @NotNull String bankAccountNumber) {
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