package com.paymybuddy.moneytranfer.servicesTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
		// assertEquals(1, result.size());
	}

}
