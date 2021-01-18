package com.paymybuddy.moneytranfer.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytranfer.models.TransactionType;

@Repository
@Transactional
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {

	TransactionType findTransactionTypeByTransactionType(String transactionType);
}