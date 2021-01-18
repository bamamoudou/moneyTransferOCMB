package com.paymybuddy.moneytranfer.modelsTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.MathContext;
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

	MathContext mc = new MathContext(3);

	@BeforeEach
	public void initTest() {
		transaction = new Transaction();
	}

	@Test
	public void gettersTest() {
		transaction.setId(1);
		transaction.setAccount(account);
		transaction.setDescription("Transfer from bank");
		transaction.setAmount(new BigDecimal(100.0));
		transaction.setBankAccount(bankAccount);
		transaction.setCreatedAt(createdAt);
		transaction.setFee(new BigDecimal(0.5));
		transaction.setRecipientAccountId(1);
		transaction.setTransactionType(transactionType);
		transaction.setTransactionCurrencyId(currencyId);

		assertThat(transaction.getId()).isEqualTo(1);
		assertThat(transaction.getAccount()).isEqualTo(account);
		assertThat(transaction.getDescription()).isEqualTo("Transfer from bank");
		assertThat(transaction.getAmount()).isEqualTo(new BigDecimal(100.0).round(mc));
		assertThat(transaction.getBankAccount()).isEqualTo(bankAccount);
		assertThat(transaction.getCreatedAt()).isEqualTo(createdAt);
		assertThat(transaction.getFee()).isEqualTo(new BigDecimal(0.5).round(mc));
		assertThat(transaction.getRecipientAccountId()).isEqualTo(1);
		assertThat(transaction.getTransactionType()).isEqualTo(transactionType);
		assertThat(transaction.getTransactionCurrencyId()).isEqualTo(currencyId);
	}

	@Test
	public void settersTest() {
		transaction.setId(2);
		transaction.setAccount(accountSet);
		transaction.setDescription("Transfer to friend");
		transaction.setAmount(new BigDecimal(200.00));
		transaction.setBankAccount(bankAccountSet);
		transaction.setCreatedAt(createdAtSet);
		transaction.setFee(new BigDecimal(1.00));
		transaction.setRecipientAccountId(2);
		transaction.setTransactionType(transactionTypeSet);
		transaction.setTransactionCurrencyId(currencyIdSet);

		assertThat(transaction.getId()).isEqualTo(2);
		assertThat(transaction.getAccount()).isEqualTo(accountSet);
		assertThat(transaction.getDescription()).isEqualTo("Transfer to friend");
		assertThat(transaction.getAmount()).isEqualTo(new BigDecimal(200.00).round(mc));
		assertThat(transaction.getBankAccount()).isEqualTo(bankAccountSet);
		assertThat(transaction.getCreatedAt()).isEqualTo(createdAtSet);
		assertThat(transaction.getFee()).isEqualTo(new BigDecimal(1.00).round(mc));
		assertThat(transaction.getRecipientAccountId()).isEqualTo(2);
		assertThat(transaction.getTransactionType()).isEqualTo(transactionTypeSet);
		assertThat(transaction.getTransactionCurrencyId()).isEqualTo(currencyIdSet);
	}
}
