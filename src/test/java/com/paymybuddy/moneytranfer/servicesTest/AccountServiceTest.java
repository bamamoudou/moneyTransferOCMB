package com.paymybuddy.moneytranfer.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

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

}
