package com.paymybuddy.moneytranfer.modelsTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.TransactionOrderDTO;

@ExtendWith(MockitoExtension.class)
public class TransactionOrderDTOTest {
	private TransactionOrderDTO transaction;

	@BeforeEach
	public void initTest() {
		transaction = new TransactionOrderDTO();
	}

	@Test
	public void getterTest() {
		transaction.setAmount("100");
		transaction.setDate("18/01/2021");
		transaction.setDescription("test description");
		transaction.setFee("0.5");
		transaction.setFromUserEmail("from@user.fr");
		transaction.setToUserEmail("to@user.fr");

		assertThat(transaction.getAmount()).isEqualTo("100");
		assertThat(transaction.getDate()).isEqualTo("18/01/2021");
		assertThat(transaction.getDescription()).isEqualTo("test description");
		assertThat(transaction.getFee()).isEqualTo("0.5");
		assertThat(transaction.getFromUserEmail()).isEqualTo("from@user.fr");
		assertThat(transaction.getToUserEmail()).isEqualTo("to@user.fr");
	}

	@Test
	public void setterTest() {
		transaction.setAmount("10");
		transaction.setDate("19/01/2021");
		transaction.setDescription("test description");
		transaction.setFee("0.9");
		transaction.setFromUserEmail("from@user.com");
		transaction.setToUserEmail("to@user.com");

		assertThat(transaction.getAmount()).isEqualTo("10");
		assertThat(transaction.getDate()).isEqualTo("19/01/2021");
		assertThat(transaction.getDescription()).isEqualTo("test description");
		assertThat(transaction.getFee()).isEqualTo("0.9");
		assertThat(transaction.getFromUserEmail()).isEqualTo("from@user.com");
		assertThat(transaction.getToUserEmail()).isEqualTo("to@user.com");
	}
}