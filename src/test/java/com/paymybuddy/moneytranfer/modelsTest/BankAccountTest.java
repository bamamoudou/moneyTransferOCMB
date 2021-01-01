package com.paymybuddy.moneytranfer.modelsTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.BankAccount;
import com.paymybuddy.moneytranfer.models.Transaction;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountTest {
	private BankAccount bankAccount;

	@Mock
	private static Account account;

	@Mock
	private static Account accountSet;

	@Mock
	private static List<Transaction> transactions;

	@Mock
	private static List<Transaction> transactionsSet;

	@BeforeEach
	public void initTest() {
		bankAccount = new BankAccount(1, account, "FBN1", transactions);

	}

	@Test
	public void gettersTest() {
		assertThat(bankAccount.getId()).isEqualTo(1);
		assertThat(bankAccount.getAccount()).isEqualTo(account);
		assertThat(bankAccount.getBankAccountNumber()).isEqualTo("FBN1");
		assertThat(bankAccount.getTransactions()).isEqualTo(transactions);
	}

	@Test
	public void settersTest() {
		bankAccount.setId(2);
		bankAccount.setBankAccountNumber("bank number");
		bankAccount.setAccount(accountSet);
		bankAccount.setTransactions(transactionsSet);

		assertThat(bankAccount.getId()).isEqualTo(2);
		assertThat(bankAccount.getBankAccountNumber()).isEqualTo("bank number");
		assertThat(bankAccount.getAccount()).isEqualTo(accountSet);
		assertThat(bankAccount.getTransactions()).isEqualTo(transactionsSet);
	}

}
