package com.paymybuddy.moneytranfer.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.BankAccount;

@Repository
@Transactional
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

	BankAccount findBankAccountByAccount(Account account);
}