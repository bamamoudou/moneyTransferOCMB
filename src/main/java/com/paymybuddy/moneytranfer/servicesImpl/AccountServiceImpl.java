package com.paymybuddy.moneytranfer.servicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.AccountType;
import com.paymybuddy.moneytranfer.repositories.AccountRepository;
import com.paymybuddy.moneytranfer.repositories.AccountTypeRepository;
import com.paymybuddy.moneytranfer.services.AccountService;

@Service
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

		return accountRepository.save(account);
	}

	/**
	 * Limited functionality exists for updating an account's balance due to
	 * transaction activity.
	 */
	@Override
	public void updateAccount(Account account) {
		Account updatedAccount = findAccountById(account.getId());
		updatedAccount.setBalance(account.getBalance());
		accountRepository.save(updatedAccount);

	}

	@Override
	public AccountType findAccountTypeByAccountType(String accountType) {

		return accountTypeRepository.findAccountTypeByAccountType(accountType);
	}
}