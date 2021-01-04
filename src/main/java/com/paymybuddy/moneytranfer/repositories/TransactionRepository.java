package com.paymybuddy.moneytranfer.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.Transaction;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	List<Transaction> findTransactionListByAccount(Account account);
}
