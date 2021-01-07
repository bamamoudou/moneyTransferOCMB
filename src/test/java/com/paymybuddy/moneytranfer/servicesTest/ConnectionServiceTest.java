package com.paymybuddy.moneytranfer.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.Connection;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.repositories.ConnectionRepository;
import com.paymybuddy.moneytranfer.servicesImpl.ConnectionServiceImpl;
import com.paymybuddy.moneytranfer.servicesImpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ConnectionServiceTest {

	@Mock
	private ConnectionRepository connectionRepository;

	@Mock
	private UserServiceImpl userServiceImpl;

	@InjectMocks
	private ConnectionServiceImpl connectionServiceImpl;

	@Test
	public void findConnectionsByUserAndUserExistsAndConnectionsReturned() {
		// arrange
		User user1 = new User();
		User user2 = new User();
		Connection connection = new Connection(user1, user2.getId());
		connection.setId(1);

		when(connectionRepository.findConnectionsByUser(user1)).thenReturn(Arrays.asList(connection));

		// act
		List<Connection> result = connectionServiceImpl.findConnectionsByUser(user1);

		// assert
		assertThat(result.size()).isEqualTo(1);

	}

	@Test
	public void findConnectedUsersByUserIfUserExistsAndUserListReturned() {
		// arrange
		User buddy1 = new User();
		buddy1.setId(1);
		User buddy2 = new User();
		buddy2.setId(2);
		Connection connection = new Connection(buddy1, buddy2.getId());
		connection.setId(1);

		when(connectionRepository.findConnectionsByUser(buddy1)).thenReturn(Arrays.asList(connection));
		when(userServiceImpl.findUserById(buddy1.getId())).thenReturn(buddy1);

		// act
		List<User> result = connectionServiceImpl.findConnectedUsersByUser(buddy1);

		// assert
		assertThat(result.size()).isEqualTo(1);
	}

	@Test
	public void createConnectionIfUserExistsAndConnectionSaved() {
		// arrange
		User user1 = new User();
		user1.setEmail("user1@test.fr");
		User user2 = new User();
		user2.setEmail("user2@test.fr");

		when(connectionRepository.findConnectionsByUser(user1)).thenReturn(new ArrayList<>());
		when(userServiceImpl.findUserByEmail(user2.getEmail())).thenReturn(user2);

		// act
		connectionServiceImpl.createConnection(user1, user2.getEmail());

		// assert
		verify(connectionRepository, times(1)).save(any(Connection.class));
	}

	@Test
	public void createConnectionIfUserDoesNotExistAndConnectionNotSaved() {
		// arrange
		User user1 = new User();
		user1.setEmail("user1@test.fr");
		String user2Email = "user2@test.fr";

		when(userServiceImpl.findUserByEmail(user2Email)).thenReturn(null);

		// act
		connectionServiceImpl.createConnection(user1, user2Email);

		// assert
		verify(connectionRepository, times(0)).save(any(Connection.class));
	}

	@Test
	public void createConnectionIfUserEmailsMatchAndConnectionNotSaved() {
		// arrange
		User user1 = new User();
		user1.setEmail("user1@test.fr");
		String user2Email = "user1@test.fr";

		// act
		connectionServiceImpl.createConnection(user1, user2Email);

		// assert
		verify(connectionRepository, times(0)).save(any(Connection.class));
	}

}
