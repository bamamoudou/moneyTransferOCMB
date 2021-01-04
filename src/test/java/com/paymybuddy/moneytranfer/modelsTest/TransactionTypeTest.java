package com.paymybuddy.moneytranfer.modelsTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.TransactionType;

@ExtendWith(MockitoExtension.class)
public class TransactionTypeTest {

	private TransactionType transactionType;

	@BeforeEach
	public void initTest() {
		transactionType = new TransactionType(1, "Pay my buddy");

	}

	@Test
	public void gettersTest() {
		assertThat(transactionType.getId()).isEqualTo(1);
		assertThat(transactionType.getType()).isEqualTo("Pay my buddy");
	}
	
	@Test
	public void settersTest() {
		transactionType.setId(2);
		transactionType.setType("Credit my account");
		
		assertThat(transactionType.getId()).isEqualTo(2);
		assertThat(transactionType.getType()).isEqualTo("Credit my account");
	}

}
