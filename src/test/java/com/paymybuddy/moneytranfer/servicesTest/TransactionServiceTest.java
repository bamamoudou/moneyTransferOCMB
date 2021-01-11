package com.paymybuddy.moneytranfer.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
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
	User sendingUser;
	User receivingUserEmail;
	Account sendingAccount;
	Account receivingAccount;

	@BeforeEach
	public void initTest() {
		transactionServiceImpl = new TransactionServiceImpl(transactionRepository, transactionTypeRepository,
				accountService, currencyService, bankAccountService);
		role = new Role("User");
		sendingUser = new User(role, "user_1", "user1@test.fr", "test123");
		receivingUserEmail = new User(role, "user_2", "user2@test.fr", "test123");

		sendingAccount = new Account();
		sendingAccount.setId(1);
		sendingAccount.setUser(sendingUser);
		sendingAccount.setBalance(new BigDecimal(0.0));
		sendingUser.setAccount(sendingAccount);

		receivingAccount = new Account();
		receivingAccount.setId(2);
		receivingAccount.setUser(receivingUserEmail);
		receivingAccount.setBalance(new BigDecimal(0.0));
		sendingUser.setAccount(receivingAccount);
	}

	@Test
	public void findTransactionsByAccountIfAccountExistsAndTransactionListReturned() {
		// arrange
		Transaction transaction = new Transaction(sendingAccount, receivingAccount.getId(), new BigDecimal(10.0));

		when(transactionRepository.findTransactionListByAccount(sendingAccount)).thenReturn(Arrays.asList(transaction));
		// act
		List<Transaction> result = transactionServiceImpl.findTransactionsByAccount(sendingAccount);
		// assert
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void findTransactionsByTransactionTypeIfTransactionTypeExistsAndTransactionListReturned() {
		// arrange
		Transaction transaction = new Transaction(sendingAccount, receivingAccount.getId(), new BigDecimal(10.0));
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
		sendingAccount.setBalance(new BigDecimal(10.0));
		Transaction transaction = new Transaction(sendingAccount, receivingAccount.getId(), new BigDecimal(10.0));
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
		sendingAccount.setBalance(new BigDecimal(10.0));
		String amountToTransfer = "5.0";
		String description = "Pay my buddy transaction";
		MathContext mc = new MathContext(3);

		when(accountService.findAccountByUserEmail(sendingUser.getEmail())).thenReturn(sendingAccount);
		when(accountService.findAccountByUserEmail(receivingUserEmail.getEmail())).thenReturn(receivingAccount);
		// act
		transactionServiceImpl.createTransactionByPayMyBuddy(sendingUser, receivingUserEmail.getEmail(), description,
				amountToTransfer);
		// assert
		verify(transactionRepository, times(1)).save(any(Transaction.class));
		assertThat(sendingAccount.getBalance()).isEqualTo(new BigDecimal(4.97).round(mc));
	}

	@Test
	public void createTransactionByPayMyBuddyIfTransactionInvalidAndTransactionNotSaved() {
		// arrange
		sendingAccount.setBalance(new BigDecimal(10.0));
		String amountToTransfer = "50.0";
		String description = "Test transaction";

		when(accountService.findAccountByUserEmail(sendingUser.getEmail())).thenReturn(sendingAccount);
		// act
		transactionServiceImpl.createTransactionByPayMyBuddy(sendingUser, receivingUserEmail.getEmail(), description,
				amountToTransfer);
		// assert
		verify(transactionRepository, times(0)).save(any(Transaction.class));
	}

	@Test
	public void createTransactionByCreditMyAccountIfTransactionValidAndTransactionSaved() {
		// arrange
		BankAccount bankAccount = new BankAccount(sendingAccount, "testBankAccountNumber");
		String amountToTransfer = "5";
		MathContext mc = new MathContext(4);

		when(accountService.findAccountByUserEmail(sendingUser.getEmail())).thenReturn(sendingAccount);
		when(bankAccountService.findBankAccountByAccount(sendingAccount)).thenReturn(bankAccount);

		// act
		transactionServiceImpl.createTransactionByCreditMyAccount(sendingUser, amountToTransfer);

		// assert
		verify(transactionRepository, times(1)).save(any(Transaction.class));
		assertThat(sendingAccount.getBalance()).isEqualTo(new BigDecimal(5.00).round(mc));
	}

	@Test
	public void createTransactionByCreditMyAccountIfTransactionInvalidAndTransactionNotSaved() {
		// arrange
		String amountToTransfer = "10.0";
		String description = "Test transaction";

		when(accountService.findAccountByUserEmail(sendingUser.getEmail())).thenReturn(sendingAccount);
		// act
		transactionServiceImpl.createTransactionByPayMyBuddy(sendingUser, receivingUserEmail.getEmail(), description,
				amountToTransfer);
		// assert
		verify(transactionRepository, times(0)).save(any(Transaction.class));
	}

	@Test
	public void createTransactionByTransferToBankAccountIfTransactionValidAndTransactionSaved() {
		// arrange
		sendingAccount.setBalance(new BigDecimal(10.0));
		BankAccount bankAccount = new BankAccount(sendingAccount, "testBankAccountNumber");
		MathContext mc = new MathContext(4);

		when(accountService.findAccountByUserEmail(sendingUser.getEmail())).thenReturn(sendingAccount);
		when(bankAccountService.findBankAccountByAccount(sendingAccount)).thenReturn(bankAccount);
		// act
		transactionServiceImpl.createTransactionByTransferToBankAccount(sendingUser);
		// assert
		verify(transactionRepository, times(1)).save(any(Transaction.class));
		assertEquals(new BigDecimal(0.0).round(mc), sendingAccount.getBalance());
	}

	@Test
	public void createTransactionByTransferToBankAccountIfNoLinkedBankAccountAndTransactionNotSaved() {
		// arrange
		when(accountService.findAccountByUserEmail(sendingUser.getEmail())).thenReturn(sendingAccount);
		// act
		transactionServiceImpl.createTransactionByTransferToBankAccount(sendingUser);
		// assert
		verify(transactionRepository, times(0)).save(any(Transaction.class));
	}

	@Test
	public void createTransactionByTransferToBankAccountIfZeroBalanceAndTransactionNotSaved() {
		// arrange
		BankAccount bankAccount = new BankAccount(sendingAccount, "testBankAccountNumber");

		when(accountService.findAccountByUserEmail(sendingUser.getEmail())).thenReturn(sendingAccount);
		when(bankAccountService.findBankAccountByAccount(sendingAccount)).thenReturn(bankAccount);
		// act
		transactionServiceImpl.createTransactionByTransferToBankAccount(sendingUser);
		// assert
		verify(transactionRepository, times(0)).save(any(Transaction.class));
	}

}
