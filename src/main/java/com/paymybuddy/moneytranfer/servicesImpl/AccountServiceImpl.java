package com.paymybuddy.moneytranfer.servicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.AccountType;
import com.paymybuddy.moneytranfer.repositories.AccountRepository;
import com.paymybuddy.moneytranfer.repositories.AccountTypeRepository;
import com.paymybuddy.moneytranfer.services.AccountService;

public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;
	private AccountTypeRepository accountTypeRepository;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository, AccountTypeRepository accountTypeRepository) {
		this.accountRepository = accountRepository;
		this.accountTypeRepository = accountTypeRepository;
	}

	@Override
	public Account findAccountById(int id) {

		Optional<Account> accountOptional = accountRepository.findById(id);
		if (accountOptional.isPresent()) {
			Account account = accountOptional.get();
			return account;
		}
		return null;

	}

	@Override
	public Account findAccountByUserEmail(String email) {

		return accountRepository.findAccountByUserEmail(email);
	}

	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub

	}

	@Override
	public AccountType findAccountTypeByAccountType(String accountType) {
		// TODO Auto-generated method stub
		return null;
	}

}
