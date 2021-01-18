package com.paymybuddy.moneytranfer.services;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.BankAccount;
import com.paymybuddy.moneytranfer.models.User;

public interface BankAccountService {

	public BankAccount findBankAccountByAccount(Account account);

	public BankAccount createBankAccount(User user, String bankAccountNumber);
}