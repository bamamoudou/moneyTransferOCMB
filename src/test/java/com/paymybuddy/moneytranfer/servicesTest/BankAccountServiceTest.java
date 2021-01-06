package com.paymybuddy.moneytranfer.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.BankAccount;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.repositories.BankAccountRepository;
import com.paymybuddy.moneytranfer.servicesImpl.BankAccountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {

	@Mock
	private BankAccountRepository bankAccountRepository;

	@InjectMocks
	private BankAccountServiceImpl bankAccountServiceImpl;

	@Test
	public void findBankAccountByAccountIfBankAccountExistsAndbankAccountReturned() {
		// arrange
		User user = new User();
		Account account = new Account();
		account.setUser(user);
		BankAccount bankAccount = new BankAccount(account, "abcdfTest");

		when(bankAccountRepository.findBankAccountByAccount(account)).thenReturn(bankAccount);

		// act
		BankAccount result = bankAccountServiceImpl.findBankAccountByAccount(account);

		// assert
		assertThat(result.getBankAccountNumber()).isEqualTo(bankAccount.getBankAccountNumber());

	}

	@Test
	public void tryToFindBankAccountByAccountIfBankAccountDoesNotExistAndnullReturned() {
		// arrange
		User user = new User();
		Account account = new Account();
		account.setUser(user);

		// act
		BankAccount result = bankAccountServiceImpl.findBankAccountByAccount(account);

		// assert
		assertThat(result).isNull();

	}

	@Test
	public void createBankAccountIfbankAccountExistAndBankAccountReturned() {
		// arrange
		User user = new User();
		Account account = new Account();

		account.setUser(user);
		user.setAccount(account);

		when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(new BankAccount());

		// act
		BankAccount result = bankAccountServiceImpl.createBankAccount(user, "bankaccountnumber");

		// assert
		assertThat(result.getBankAccountNumber()).isEqualTo("bankaccountnumber");
		verify(bankAccountRepository, times(1)).save(any(BankAccount.class));

	}

	@Test
	public void tryToCreateBankAccountIfBankAccountDoesNotExistsAndnullReturned() {
		// arrange
		User user = new User();
		Account account = new Account();
		account.setUser(user);
		user.setAccount(account);
		BankAccount bankAccount = new BankAccount(account, "noaccount");
		account.setBankAccount(bankAccount);

		// act
		BankAccount result = bankAccountServiceImpl.createBankAccount(user, "noaccount");

		// assert
		assertThat(result).isNull();
		verify(bankAccountRepository, times(0)).save(any(BankAccount.class));
	}

}
