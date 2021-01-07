package com.paymybuddy.moneytranfer.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.paymybuddy.moneytranfer.models.Role;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.models.UserProfilDetails;
import com.paymybuddy.moneytranfer.services.UserService;
import com.paymybuddy.moneytranfer.servicesImpl.UserProfilDetailsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserProfilDetailsServiceTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserProfilDetailsServiceImpl userProfilDetailsServiceImpl;

	@Test
	public void loadUserByUsernameIfUserExistsAndUserProfilDetailsReturned() {
		// arrange
		User user = new User();
		user.setEmail("testEmail@test.fr");
		Role roleType = new Role("User");
		user.setRole(roleType);

		when(userService.findUserByEmail(user.getEmail())).thenReturn(user);

		// act
		UserProfilDetails result = userProfilDetailsServiceImpl.loadUserByUsername(user.getEmail());

		// assert
		assertThat(result.getUsername()).isEqualTo(user.getEmail());
	}

	@Test
	public void loadUserByUsernameIfUserDoesNotExistAndthrowsUsernameNotFoundException() {
		// arrange
		String email = "test@test.fr";

		when(userService.findUserByEmail(email)).thenReturn(null);

		// act

		// assert
		assertThrows(UsernameNotFoundException.class, () -> userProfilDetailsServiceImpl.loadUserByUsername(email));
	}

}
