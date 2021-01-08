package com.paymybuddy.moneytranfer.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.Transaction;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.repositories.TransactionRepository;
import com.paymybuddy.moneytranfer.repositories.TransactionTypeRepository;
import com.paymybuddy.moneytranfer.services.AccountService;
import com.paymybuddy.moneytranfer.services.BankAccountService;
import com.paymybuddy.moneytranfer.services.CurrencyService;
import com.paymybuddy.moneytranfer.services.TransactionService;

public class TransactionServiceImpl implements TransactionService {
	private TransactionRepository transactionRepository;
	private TransactionTypeRepository transactionTypeRepository;
	private AccountService accountService;
	private CurrencyService currencyService;
	private BankAccountService bankAccountService;
	/**
	 * 0.5% transaction fee to be collected from sending user for every normal
	 * transaction (from friend to friend)
	 */
	private Double transactionFee = new Double(0.005);

	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository,
			TransactionTypeRepository transactionTypeRepository, AccountService accountService,
			CurrencyService currencyService, BankAccountService bankAccountService) {
		this.transactionRepository = transactionRepository;
		this.transactionTypeRepository = transactionTypeRepository;
		this.accountService = accountService;
		this.currencyService = currencyService;
		this.bankAccountService = bankAccountService;
	}

	@Override
	public List<Transaction> findTransactionsByTransactionType(String transactionType) {
		List<Transaction> transactions = transactionRepository.findAll().stream()
				.filter(t -> t.getTransactionType().getType().equals(transactionType)).collect(Collectors.toList());
		return transactions;
	}

	@Override
	public List<Transaction> findTransactionsByAccount(Account account) {
		return transactionRepository.findTransactionListByAccount(account);
	}

	@Override
	public void createTransactionByPayMyBuddy(User sendingUser, String receivingUserEmail, String description,
			String amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTransactionByTransferToBankAccount(User sendingUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTransactionByCreditMyAccount(User sendingUser, String amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isInCurrencyFormat(String amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
