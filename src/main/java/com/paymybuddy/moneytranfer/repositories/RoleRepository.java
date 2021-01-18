package com.paymybuddy.moneytranfer.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.moneytranfer.models.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findRoleTypeByRoleType(String roleType);
}