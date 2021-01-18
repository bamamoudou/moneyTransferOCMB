package com.paymybuddy.moneytranfer.modelsTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.Connection;
import com.paymybuddy.moneytranfer.models.User;

@ExtendWith(MockitoExtension.class)
public class ConnectionTest {

	private Connection connection;
	@Mock
	private static User user;
	@Mock
	private static User userSet;

	@BeforeEach
	public void initTest() {
		connection = new Connection(1, user, 1);
	}

	@Test
	public void gettersTest() {
		assertThat(connection.getId()).isEqualTo(1);
		assertThat(connection.getUser()).isEqualTo(user);
		assertThat(connection.getConnectedUserId()).isEqualTo(1);
	}

	@Test
	public void settersTest() {
		connection.setId(2);
		connection.setUser(userSet);
		connection.setConnectedUserId(2);

		assertThat(connection.getId()).isEqualTo(2);
		assertThat(connection.getUser()).isEqualTo(userSet);
		assertThat(connection.getConnectedUserId()).isEqualTo(2);
	}
}
