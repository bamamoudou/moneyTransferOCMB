package com.paymybuddy.moneytranfer.modelsTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.BankAccount;
import com.paymybuddy.moneytranfer.models.Currency;
import com.paymybuddy.moneytranfer.models.Transaction;
import com.paymybuddy.moneytranfer.models.TransactionType;

@ExtendWith(MockitoExtension.class)
public class TransactionTest {

	private Transaction transaction;
	private Date createdAt;
	private Date createdAtSet;

	@Mock
	private static Currency currencyId;

	@Mock
	private static Currency currencyIdSet;

	@Mock
	private static BankAccount bankAccount;

	@Mock
	private static BankAccount bankAccountSet;

	@Mock
	private static Account account;

	@Mock
	private static Account accountSet;

	@Mock
	private static TransactionType transactionType;

	@Mock
	private static TransactionType transactionTypeSet;

	@BeforeEach
	public void initTest() {
		transaction = new Transaction(1, transactionType, account, 1, currencyId, bankAccount, createdAt, 100.0,
				"Transfer from bank", 0.75);

	}

	@Test
	public void gettersTest() {
		assertThat(transaction.getId()).isEqualTo(1);
		assertThat(transaction.getAccount()).isEqualTo(account);
		assertThat(transaction.getDescription()).isEqualTo("Transfer from bank");
		assertThat(transaction.getAmount()).isEqualTo(100.0);
		assertThat(transaction.getBankAccount()).isEqualTo(bankAccount);
		assertThat(transaction.getCreatedAt()).isEqualTo(createdAt);
		assertThat(transaction.getFee()).isEqualTo(0.75);
		assertThat(transaction.getRecipientAccountId()).isEqualTo(1);
		assertThat(transaction.getTransactionType()).isEqualTo(transactionType);
		assertThat(transaction.getTransactionCurrencyId()).isEqualTo(currencyId);
	}

	@Test
	public void settersTest() {
		transaction.setId(2);
		transaction.setAccount(accountSet);
		transaction.setDescription("Transfer to friend");
		transaction.setAmount(200.0);
		transaction.setBankAccount(bankAccountSet);
		transaction.setCreatedAt(createdAtSet);
		transaction.setFee(1.00);
		transaction.setRecipientAccountId(2);
		transaction.setTransactionType(transactionTypeSet);
		transaction.setTransactionCurrencyId(currencyIdSet);

		assertThat(transaction.getId()).isEqualTo(2);
		assertThat(transaction.getAccount()).isEqualTo(accountSet);
		assertThat(transaction.getDescription()).isEqualTo("Transfer to friend");
		assertThat(transaction.getAmount()).isEqualTo(200.0);
		assertThat(transaction.getBankAccount()).isEqualTo(bankAccountSet);
		assertThat(transaction.getCreatedAt()).isEqualTo(createdAtSet);
		assertThat(transaction.getFee()).isEqualTo(1.00);
		assertThat(transaction.getRecipientAccountId()).isEqualTo(2);
		assertThat(transaction.getTransactionType()).isEqualTo(transactionTypeSet);
		assertThat(transaction.getTransactionCurrencyId()).isEqualTo(currencyIdSet);
	}

}
