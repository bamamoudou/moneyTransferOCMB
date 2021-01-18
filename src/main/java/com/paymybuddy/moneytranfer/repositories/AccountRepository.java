package com.paymybuddy.moneytranfer.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytranfer.models.Account;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findAccountByUserEmail(String email);
}