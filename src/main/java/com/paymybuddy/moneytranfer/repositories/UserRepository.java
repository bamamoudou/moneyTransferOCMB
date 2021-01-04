package com.paymybuddy.moneytranfer.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytranfer.models.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

	User findUserByEmail(String email);
}
