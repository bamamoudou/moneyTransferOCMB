package com.paymybuddy.moneytranfer.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.Currency;
import com.paymybuddy.moneytranfer.repositories.CurrencyRepository;
import com.paymybuddy.moneytranfer.servicesImpl.CurrencyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

	@Mock
	private CurrencyRepository currencyRepository;

	@InjectMocks
	private CurrencyServiceImpl currencyServiceImpl;

	@Test
	public void findCurrencyByCurrencyLabelIfCurrencyExistsAndCurrencyReturned() {
		// arrange
		Currency currency = new Currency("£", "Euro");

		when(currencyRepository.findCurrencyByLabel(currency.getLabel())).thenReturn(currency);

		// act
		Currency result = currencyServiceImpl.findCurrencyByCurrencyLabel("£");

		// assert
		assertThat(result.getDescription()).isEqualTo("Euro");
	}

	@Test
	public void findCurrencyByCurrencyLabelIfCurrencyDoesNotExistAndNullReturned() {
		// arrange

		// act
		Currency result = currencyServiceImpl.findCurrencyByCurrencyLabel("");

		// assert
		assertThat(result).isNull();
	}
}
