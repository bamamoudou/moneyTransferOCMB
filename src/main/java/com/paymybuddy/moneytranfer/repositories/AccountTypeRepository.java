package com.paymybuddy.moneytranfer.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytranfer.models.AccountType;

@Repository
@Transactional
public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {

	AccountType findAccountTypeByAccountType(String accountType);
}
