package com.paymybuddy.moneytranfer.modelsTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.paymybuddy.moneytranfer.models.Currency;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyTest {

	private Currency currency;

	@BeforeEach
	public void initTest() {
		currency = new Currency(1, "£", "Euro");
	}

	@Test
	public void gettersTest() {
		assertThat(currency.getId()).isEqualTo(1);
		assertThat(currency.getLabel()).isEqualTo("£");
		assertThat(currency.getDescription()).isEqualTo("Euro");
	}

	@Test
	public void settersTest() {
		currency.setId(2);
		currency.setLabel("$");
		currency.setDescription("Dollar US");

		assertThat(currency.getId()).isEqualTo(2);
		assertThat(currency.getLabel()).isEqualTo("$");
		assertThat(currency.getDescription()).isEqualTo("Dollar US");
	}
}