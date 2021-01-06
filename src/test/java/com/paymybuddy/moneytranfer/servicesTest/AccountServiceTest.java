package com.paymybuddy.moneytranfer.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.repositories.AccountRepository;
import com.paymybuddy.moneytranfer.repositories.AccountTypeRepository;
import com.paymybuddy.moneytranfer.servicesImpl.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private AccountTypeRepository accountTypeRepository;

	@InjectMocks
	private static AccountServiceImpl accountServiceImpl;

	@Test
	public void findAccountByIdIfAccountExistAndReturned() {
		// Arrange
		Account account = new Account();
		User user = new User();
		account.setId(1);
		account.setUser(user);

		when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));

		// Act
		Account result = accountServiceImpl.findAccountById(account.getId());

		// Assert
		assertThat(result.getId()).isEqualTo(account.getId());

	}

	@Test
	public void tryToFindAccountByIdIfAccountDoesNotExistAndNullReturned() {
		// arrange

		// act
		Account result = accountServiceImpl.findAccountById(0);

		// assert
		assertThat(result).isNull();
	}

	@Test
	public void findAccountByUserEmailIfaccountExistsAndaccountReturned() {
		// arrange
		User user = new User();
		user.setEmail("ma@ma.fr");
		Account account = new Account();
		account.setUser(user);

		when(accountRepository.findAccountByUserEmail(account.getUser().getEmail())).thenReturn(account);

		// act
		Account result = accountServiceImpl.findAccountByUserEmail("ma@ma.fr");

		// assert
		assertThat(account).isEqualTo(result);
	}

	@Test
	public void tryToFindAccountByUserEmailIfAccountDoesNotExistAndnullReturned() {
		// arrange

		// act
		Account result = accountServiceImpl.findAccountByUserEmail("do@do");

		// assert
		assertThat(result).isNull();
	}

	@Test
	public void createAccountIfaccountValidAndaccountSaved() {
		// arrange
		User user = new User();
		Account account = new Account();
		account.setId(1);
		account.setUser(user);

		when(accountServiceImpl.createAccount(account)).thenReturn(account);

		// act
		Account result = accountServiceImpl.createAccount(account);

		// assert
		assertThat(result.getId()).isEqualTo(1);

		verify(accountRepository, times(1)).save(any(Account.class));
	}

	@Test
	public void updateAccountIfaccountValidAndaccountSaved() {
		// arrange
		User user = new User();
		Account account = new Account();
		account.setId(1);
		account.setUser(user);
		account.setBalance(new Double(0.0));
		user.setAccount(account);
		assertThat(new Double(0.0), Matchers.comparesEqualTo(account.getBalance()));

		account.setBalance(new Double(10.0));
		Optional<Account> optionalOfAccount = Optional.of(account);
		when(accountRepository.findById(1)).thenReturn(optionalOfAccount);

		// act
		accountServiceImpl.updateAccount(account);

		// assert
		assertThat(new Double(10.0), Matchers.comparesEqualTo(account.getBalance()));
		verify(accountRepository, times(1)).save(any(Account.class));
	}

}
