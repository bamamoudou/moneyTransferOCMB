package com.paymybuddy.moneytranfer.servicesTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.Role;
import com.paymybuddy.moneytranfer.repositories.RoleRepository;
import com.paymybuddy.moneytranfer.servicesImpl.RoleServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {
	
	@Mock
	private RoleRepository roleRepository;

	@InjectMocks
	private RoleServiceImpl roleServiceImpl;

	@Test
	public void findRoleTypeByRoleTypeIfroleTypeExistsAndroleTypeReturned() {
		// arrange
		Role role = new Role("Admin");

		when(roleRepository.findRoleTypeByRoleType(role.getName())).thenReturn(role);

		// act
		Role result = roleServiceImpl.findRoleTypeByRoleType(role.getName());

		// assert
		assertEquals(role.getName(), result.getName());
	}
	
	@Test
	public void findRoleTypeByRoleTypeIfroleTypeDoesNotExistAndnullReturned() {
      //arrange

      //act
      Role result = roleServiceImpl.findRoleTypeByRoleType("User");

      //assert
      assertNull(result);
  }

}
