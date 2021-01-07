package com.paymybuddy.moneytranfer.services;

import java.util.List;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.Transaction;
import com.paymybuddy.moneytranfer.models.User;

public interface TransactionService {

	public List<Transaction> findTransactionListByTransactionType(String transactionType);

	public List<Transaction> findTransactionListByAccount(Account account);

	public void createTransactionByTransferToFriend(User sendingUser, String receivingUserEmail, String description,
			String amount);

	public void createTransactionByTransferToBank(User sendingUser);

	public void createTransactionByAddMoney(User sendingUser, String amount);

	public boolean isInCurrencyFormat(String amount);

}
