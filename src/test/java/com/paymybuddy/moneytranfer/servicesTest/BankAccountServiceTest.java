package com.paymybuddy.moneytranfer.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
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

}
