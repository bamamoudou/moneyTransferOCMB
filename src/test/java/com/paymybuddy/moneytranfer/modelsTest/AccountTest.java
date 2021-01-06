package com.paymybuddy.moneytranfer.modelsTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.AccountType;
import com.paymybuddy.moneytranfer.models.BankAccount;
import com.paymybuddy.moneytranfer.models.Currency;
import com.paymybuddy.moneytranfer.models.Transaction;
import com.paymybuddy.moneytranfer.models.User;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

	private Account account;

	@Mock
	private static Currency currency;

	@Mock
	private static Currency currencySet;

	@Mock
	private static User user;

	@Mock
	private static User userSet;

	@Mock
	private static AccountType accountType;

	@Mock
	private static AccountType accountTypeSet;

	@Mock
	private static BankAccount bankAccount;

	@Mock
	private static BankAccount bankAccountSet;

	@Mock
	private static List<Transaction> transactions;

	@Mock
	private static List<Transaction> transactionsSet;

	@BeforeEach
	public void initTest() {
		//account = new Account(1, user, accountType, currency, 10.5, transactions, bankAccount);
		account = new Account();
	

	}

	@Test
	public void gettersTest() {
		account.setId(1);
		account.setAccountType(accountType);
		account.setBalance(10.5);
		account.setBankAccount(bankAccount);
		account.setUser(user);
		account.setCurrency(currency);
		account.setTransactions(transactions);
		
		assertThat(account.getId()).isEqualTo(1);
		assertThat(account.getAccountType()).isEqualTo(accountType);
		assertThat(account.getBalance()).isEqualTo(10.5);
		assertThat(account.getBankAccount()).isEqualTo(bankAccount);
		assertThat(account.getUser()).isEqualTo(user);
		assertThat(account.getCurrency()).isEqualTo(currency);
		assertThat(account.getTransactions()).isEqualTo(transactions);

	}

	@Test
	public void settersTest() {
		account.setId(2);
		account.setAccountType(accountTypeSet);
		account.setBalance(9.0);
		account.setBankAccount(bankAccountSet);
		account.setUser(userSet);
		account.setCurrency(currencySet);
		account.setTransactions(transactionsSet);

		assertThat(account.getId()).isEqualTo(2);
		assertThat(account.getAccountType()).isEqualTo(accountTypeSet);
		assertThat(account.getBalance()).isEqualTo(9.0);
		assertThat(account.getBankAccount()).isEqualTo(bankAccountSet);
		assertThat(account.getUser()).isEqualTo(userSet);
		assertThat(account.getCurrency()).isEqualTo(currencySet);
		assertThat(account.getTransactions()).isEqualTo(transactionsSet);
	}

}
