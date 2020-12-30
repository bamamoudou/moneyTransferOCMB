package com.paymybuddy.moneytranfer.modelsTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.paymybuddy.moneytranfer.models.AccountType;

@RunWith(MockitoJUnitRunner.class)
public class AccountTypeTest {

	private AccountType accountType;

	@BeforeEach
	public void initTest() {
		accountType = new AccountType(1, "regular");

	}

	@Test
	public void gettersTest() {
		assertThat(accountType.getId()).isEqualTo(1);
		assertThat(accountType.getAccountType()).isEqualTo("regular");
	}

	@Test
	public void settersTest() {
		accountType.setId(2);
		accountType.setAccountType("bank");

		assertThat(accountType.getId()).isEqualTo(2);
		assertThat(accountType.getAccountType()).isEqualTo("bank");
	}

}
