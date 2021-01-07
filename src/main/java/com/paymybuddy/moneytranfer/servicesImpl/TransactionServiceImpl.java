package com.paymybuddy.moneytranfer.servicesImpl;

import java.util.List;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.Transaction;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.services.TransactionService;

public class TransactionServiceImpl implements TransactionService {

	@Override
	public List<Transaction> findTransactionListByTransactionType(String transactionType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findTransactionListByAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createTransactionByTransferToFriend(User sendingUser, String receivingUserEmail, String description,
			String amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTransactionByTransferToBank(User sendingUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTransactionByAddMoney(User sendingUser, String amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isInCurrencyFormat(String amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
