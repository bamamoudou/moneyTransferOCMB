package com.paymybuddy.moneytranfer.services;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.AccountType;

public interface AccountService {

	public Account findAccountById(int id);

	public Account findAccountByUserEmail(String email);

	public Account createAccount(Account account);

	public void updateAccount(Account account);

	public AccountType findAccountTypeByAccountType(String accountType);
}