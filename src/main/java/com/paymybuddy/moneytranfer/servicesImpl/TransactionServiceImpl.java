package com.paymybuddy.moneytranfer.servicesImpl;

import java.math.BigDecimal;
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
	private BigDecimal transactionFee = new BigDecimal(0.005);

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
		BigDecimal transactionAmount = new BigDecimal(amount);
		if (transactionValidator("PayMyBuddy", sendingUser.getEmail(), transactionAmount)) {
			Account sendingAccount = accountService.findAccountByUserEmail(sendingUser.getEmail());
			BigDecimal sendingAccountBalanceBefore = sendingAccount.getBalance();
			Account receivingAccount = accountService.findAccountByUserEmail(receivingUserEmail);
			BigDecimal receivingAccountBalanceBefore = receivingAccount.getBalance();

			Transaction newTransaction = new Transaction(sendingAccount, receivingAccount.getId(), transactionAmount);
			newTransaction
					.setTransactionType(transactionTypeRepository.findTransactionTypeByTransactionType("PayMyBuddy"));
			newTransaction.setTransactionCurrencyId(currencyService.findCurrencyByCurrencyLabel("USD"));
			newTransaction.setDescription(description);
			newTransaction.setFee(transactionAmount.multiply(transactionFee));
			transactionRepository.save(newTransaction);

			BigDecimal amountToSubtractFromSender = calculateTransactionAmountForSender(transactionAmount);
			sendingAccount.setBalance(sendingAccountBalanceBefore.subtract(amountToSubtractFromSender));
			accountService.updateAccount(sendingAccount);
			receivingAccount.setBalance(receivingAccountBalanceBefore.add(transactionAmount));
			accountService.updateAccount(receivingAccount);
		}

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

	public boolean transactionValidator(String transactionType, String sendingUserEmail, BigDecimal transactionAmount) {
		boolean validation = false;
		BigDecimal sendersBalance = accountService.findAccountByUserEmail(sendingUserEmail).getBalance();
		BigDecimal sendersBalanceMinusTransactionAmount = sendersBalance
				.subtract(calculateTransactionAmountForSender(transactionAmount));

		if (transactionType == "CreditMyAccount" && transactionAmount.intValue() > 0) {
			validation = true;
		} else if (transactionType == "TransferToBankAccount" && sendersBalance.intValue() > 0) {
			validation = true;
		} else if (transactionType == "PayMyBuddy" && sendersBalanceMinusTransactionAmount.intValue() >= 0) {
			validation = true;
		}
		return validation;
	}

	public BigDecimal calculateTransactionAmountForSender(BigDecimal transactionAmount) {
		BigDecimal feeFactor = (new BigDecimal("1").add(transactionFee));
		return transactionAmount.multiply(feeFactor).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

}
