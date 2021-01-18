package com.paymybuddy.moneytranfer.services;

import com.paymybuddy.moneytranfer.models.Currency;

public interface CurrencyService {
	
	public Currency findCurrencyByCurrencyLabel(String currencyLabel);
}