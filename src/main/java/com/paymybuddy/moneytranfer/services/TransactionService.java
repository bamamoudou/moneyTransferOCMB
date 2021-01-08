package com.paymybuddy.moneytranfer.services;

import java.util.List;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.Transaction;
import com.paymybuddy.moneytranfer.models.User;

public interface TransactionService {

	public List<Transaction> findTransactionsByTransactionType(String transactionType);

	public List<Transaction> findTransactionsByAccount(Account account);

	public void createTransactionByPayMyBuddy(User sendingUser, String receivingUserEmail, String description,
			String amount);

	public void createTransactionByTransferToBankAccount(User sendingUser);

	public void createTransactionByCreditMyAccount(User sendingUser, String amount);

	public boolean isInCurrencyFormat(String amount);

}
