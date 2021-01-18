package com.paymybuddy.moneytranfer.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytranfer.models.Currency;

@Repository
@Transactional
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

	Currency findCurrencyByLabel(String label);
}