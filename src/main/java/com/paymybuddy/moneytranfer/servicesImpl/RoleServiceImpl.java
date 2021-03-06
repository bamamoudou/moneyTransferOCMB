package com.paymybuddy.moneytranfer.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.moneytranfer.models.Role;
import com.paymybuddy.moneytranfer.repositories.RoleRepository;
import com.paymybuddy.moneytranfer.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public Role findRoleTypeByRoleType(String roleType) {

		return roleRepository.findRoleTypeByRoleType(roleType);
	}
}