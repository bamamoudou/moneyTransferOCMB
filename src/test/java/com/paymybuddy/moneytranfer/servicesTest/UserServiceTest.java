package com.paymybuddy.moneytranfer.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.paymybuddy.moneytranfer.models.Role;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.models.UserProfilDetails;
import com.paymybuddy.moneytranfer.repositories.UserRepository;
import com.paymybuddy.moneytranfer.services.AccountService;
import com.paymybuddy.moneytranfer.services.CurrencyService;
import com.paymybuddy.moneytranfer.services.RoleService;
import com.paymybuddy.moneytranfer.servicesImpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleService roleService;

	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoderMock;

	@Mock
	private AccountService accountServiceMock;

	@Mock
	private CurrencyService currencyServiceMock;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Test
	public void findUserByEmailIfUserExistsAndUserReturned() {
		// arrange
		User user = new User();
		user.setEmail("user@test.fr");

		when(userRepository.findUserByEmail(user.getEmail())).thenReturn(user);

		// act
		User result = userServiceImpl.findUserByEmail("user@test.fr");

		// assert
		assertThat(result).isEqualTo(user);

	}

	@Test
	public void findUserByEmailIfUserDoesNotExistAndnullReturned() {
		// arrange

		// act
		User result = userServiceImpl.findUserByEmail("notexist@test.com");

		// assert
		assertThat(result).isNull();
	}

	@Test
	public void getUserFromAuthIfUserLoggedInAndUserReturned() {
		// arrange
		User user = new User();
		user.setEmail("user@test.fr");
		user.setPassword("test123");
		Role userRole = new Role("User");
		UserProfilDetails userProfilDetails = new UserProfilDetails(user, userRole);

		Authentication auth = mock(Authentication.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);

		when(((UserProfilDetails) (auth.getPrincipal()))).thenReturn(userProfilDetails);

		when(userRepository.findUserByEmail(user.getEmail())).thenReturn(user);

		// act
		User result = userServiceImpl.getUserFromAuth(auth);

		// assert
		assertThat(result).isNotNull();

	}

	@Test
	public void getUserFromAuthIfuserNotLoggedInAndnullReturned() {
		// arrange
		AnonymousAuthenticationToken authentication = mock(AnonymousAuthenticationToken.class);
		SecurityContext securityContext = mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);

		// act
		User result = userServiceImpl.getUserFromAuth(authentication);

		// assert
		assertThat(result).isNull();
	}

	@Test
	public void findUserByIdIfUserExistsAndUserReturned() {
		// arrange
		User user = new User();
		user.setId(1);

		when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

		// act
		User result = userServiceImpl.findUserById(1);

		// assert
		assertThat(result).isEqualTo(user);
	}

	@Test
	public void findUserByIdIfUserDoesNotExistAndNullReturned() {
		// arrange

		// act
		User result = userServiceImpl.findUserById(1);

		// assert
		assertThat(result).isNull();
	}

	@Test
	public void createUserByRegistrationAndUserIsValidAndUserReturned() {
		// arrange
		User user = new User();
		user.setEmail("user@test.fr");
		user.setPassword("test123");
		user.setName("user1");

		when(userRepository.save(any())).thenReturn(user);

		// act
		User result = userServiceImpl.createUserByRegistration(user);

		// assert
		assertThat(result.getEmail()).isEqualTo(user.getEmail());
		verify(userRepository, times(1)).save(any(User.class));
	}

}
