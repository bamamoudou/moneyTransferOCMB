package com.paymybuddy.moneytranfer.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.BankAccount;
import com.paymybuddy.moneytranfer.models.Role;
import com.paymybuddy.moneytranfer.models.Transaction;
import com.paymybuddy.moneytranfer.models.TransactionType;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.repositories.TransactionRepository;
import com.paymybuddy.moneytranfer.repositories.TransactionTypeRepository;
import com.paymybuddy.moneytranfer.services.AccountService;
import com.paymybuddy.moneytranfer.services.BankAccountService;
import com.paymybuddy.moneytranfer.services.CurrencyService;
import com.paymybuddy.moneytranfer.servicesImpl.TransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
	@Mock
	private TransactionRepository transactionRepository;
	@Mock
	private TransactionTypeRepository transactionTypeRepository;
	@Mock
	private AccountService accountService;
	@Mock
	private CurrencyService currencyService;
	@Mock
	private BankAccountService bankAccountService;
	@InjectMocks
	private TransactionServiceImpl transactionServiceImpl;
	Role role;
	User user1;
	User user2;
	Account account1;
	Account account2;

	@BeforeEach
	public void initTest() {
		transactionServiceImpl = new TransactionServiceImpl(transactionRepository, transactionTypeRepository,
				accountService, currencyService, bankAccountService);
		role = new Role("User");
		user1 = new User(role, "user_1", "user1@test.fr", "test123");
		user2 = new User(role, "user_2", "user2@test.fr", "test123");

		account1 = new Account();
		account1.setId(1);
		account1.setUser(user1);
		account1.setBalance(new BigDecimal(0.0));
		user1.setAccount(account1);

		account2 = new Account();
		account2.setId(2);
		account2.setUser(user2);
		account2.setBalance(new BigDecimal(0.0));
		user1.setAccount(account2);
	}

	@Test
	public void findTransactionsByAccountIfAccountExistsAndTransactionListReturned() {
		// arrange
		Transaction transaction = new Transaction(account1, account2.getId(), new BigDecimal(10.0));

		when(transactionRepository.findTransactionListByAccount(account1)).thenReturn(Arrays.asList(transaction));
		// act
		List<Transaction> result = transactionServiceImpl.findTransactionsByAccount(account1);
		// assert
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void findTransactionsByTransactionTypeIfTransactionTypeExistsAndTransactionListReturned() {
		// arrange
		Transaction transaction = new Transaction(account1, account2.getId(), new BigDecimal(10.0));
		TransactionType transactionType = new TransactionType("PayMyBuddy");
		transaction.setTransactionType(transactionType);

		when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction));
		// act
		List<Transaction> result = transactionServiceImpl.findTransactionsByTransactionType("PayMyBuddy");
		// assert
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void findTransactionsByTransactionTypeIfTransactionTypeDoesNotExistAndEmptyTransactionsReturned() {
		// arrange
		account1.setBalance(new BigDecimal(10.0));
		Transaction transaction = new Transaction(account1, account2.getId(), new BigDecimal(10.0));
		TransactionType transactionType = new TransactionType("PayMyBuddy");
		transaction.setTransactionType(transactionType);

		when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction));
		// act
		List<Transaction> result = transactionServiceImpl.findTransactionsByTransactionType("");
		// assert
		assertThat(result.size()).isEqualTo(0);
	}

	@Test
	public void createTransactionByPayMyBuddyIfTransactionValidAndTransactionSaved() {
		// arrange
		account1.setBalance(new BigDecimal(10.0));
		String amountToTransfer = "5.0";
		String description = "Pay my buddy transaction";
		MathContext mc = new MathContext(3);

		when(accountService.findAccountByUserEmail(user1.getEmail())).thenReturn(account1);
		when(accountService.findAccountByUserEmail(user2.getEmail())).thenReturn(account2);
		// act
		transactionServiceImpl.createTransactionByPayMyBuddy(user1, user2.getEmail(), description, amountToTransfer);
		// assert
		verify(transactionRepository, times(1)).save(any(Transaction.class));
		assertThat(account1.getBalance()).isEqualTo(new BigDecimal(4.97).round(mc));
	}

	@Test
	public void createTransactionByPayMyBuddyIfTransactionInvalidAndTransactionNotSaved() {
		// arrange
		account1.setBalance(new BigDecimal(10.0));
		String amountToTransfer = "50.0";
		String description = "Test transaction";

		when(accountService.findAccountByUserEmail(user1.getEmail())).thenReturn(account1);
		// act
		transactionServiceImpl.createTransactionByPayMyBuddy(user1, user2.getEmail(), description, amountToTransfer);
		// assert
		verify(transactionRepository, times(0)).save(any(Transaction.class));
	}

	@Test
	public void createTransactionByCreditMyAccountIfTransactionValidAndTransactionSaved() {
		// arrange
		BankAccount bankAccount = new BankAccount(account1, "testBankAccountNumber");
		String amountToTransfer = "5";
		MathContext mc = new MathContext(4);

		when(accountService.findAccountByUserEmail(user1.getEmail())).thenReturn(account1);
		when(bankAccountService.findBankAccountByAccount(account1)).thenReturn(bankAccount);

		// act
		transactionServiceImpl.createTransactionByCreditMyAccount(user1, amountToTransfer);

		// assert
		verify(transactionRepository, times(1)).save(any(Transaction.class));
		assertThat(account1.getBalance()).isEqualTo(new BigDecimal(5.00).round(mc));
	}

	@Test
	public void createTransactionByCreditMyAccountIfTransactionInvalidAndTransactionNotSaved() {
		// arrange
		String amountToTransfer = "10.0";
		String description = "Test transaction";

		when(accountService.findAccountByUserEmail(user1.getEmail())).thenReturn(account1);
		// act
		transactionServiceImpl.createTransactionByPayMyBuddy(user1, user2.getEmail(), description, amountToTransfer);
		// assert
		verify(transactionRepository, times(0)).save(any(Transaction.class));
	}

}
