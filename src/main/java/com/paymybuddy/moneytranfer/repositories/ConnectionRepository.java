package com.paymybuddy.moneytranfer.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytranfer.models.Connection;
import com.paymybuddy.moneytranfer.models.User;

@Repository
@Transactional
public interface ConnectionRepository extends JpaRepository<Connection, Integer> {

	List<Connection> findConnectionsByUser(User user);
}