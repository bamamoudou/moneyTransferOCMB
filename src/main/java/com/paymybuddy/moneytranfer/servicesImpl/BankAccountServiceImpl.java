package com.paymybuddy.moneytranfer.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.BankAccount;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.repositories.BankAccountRepository;
import com.paymybuddy.moneytranfer.services.BankAccountService;

public class BankAccountServiceImpl implements BankAccountService {

	private BankAccountRepository bankAccountRepository;

	@Autowired
	public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
		this.bankAccountRepository = bankAccountRepository;
	}

	@Override
	public BankAccount findBankAccountByAccount(Account account) {

		return bankAccountRepository.findBankAccountByAccount(account);
	}

	@Override
	public BankAccount createBankAccount(User user, String bankAccountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
