package com.paymybuddy.moneytranfer.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytranfer.models.Currency;
import com.paymybuddy.moneytranfer.repositories.CurrencyRepository;
import com.paymybuddy.moneytranfer.services.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	private CurrencyRepository currencyRepository;

	@Autowired
	public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	@Override
	public Currency findCurrencyByCurrencyLabel(String currencyLabel) {

		return currencyRepository.findCurrencyByLabel(currencyLabel);
	}
}