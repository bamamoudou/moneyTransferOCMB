package com.paymybuddy.moneytranfer.modelsTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.TransactionDTO;

@ExtendWith(MockitoExtension.class)
public class TransactionDTOTest {
	private TransactionDTO transaction;

	@BeforeEach
	public void initTest() {
		transaction = new TransactionDTO();
	}

	@Test
	public void getterTest() {
		transaction.setAmount("100");
		transaction.setDescription("test description");
		transaction.setToUserEmail("to@user.fr");

		assertThat(transaction.getAmount()).isEqualTo("100");
		assertThat(transaction.getDescription()).isEqualTo("test description");
		assertThat(transaction.getToUserEmail()).isEqualTo("to@user.fr");
	}

	@Test
	public void setterTest() {
		transaction.setAmount("10");
		transaction.setDescription("test description");
		transaction.setToUserEmail("to@user.com");

		assertThat(transaction.getAmount()).isEqualTo("10");
		assertThat(transaction.getDescription()).isEqualTo("test description");
		assertThat(transaction.getToUserEmail()).isEqualTo("to@user.com");
	}

}
