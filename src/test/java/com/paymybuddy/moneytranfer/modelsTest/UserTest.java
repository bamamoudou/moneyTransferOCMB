package com.paymybuddy.moneytranfer.modelsTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.Connection;
import com.paymybuddy.moneytranfer.models.Role;
import com.paymybuddy.moneytranfer.models.User;

@ExtendWith(MockitoExtension.class)
public class UserTest {

	private User user;

	@Mock
	private static Account account;

	@Mock
	private static Account accountSet;

	@Mock
	private static List<Connection> connections;

	@Mock
	private static List<Connection> connectionsSet;

	@Mock
	private Role role;

	@Mock
	private Role roleSet;

	private Date createdAt;

	private Date createdAtSet;

	private Date updatedAt;

	private Date updatedAtSet;

	@BeforeEach
	public void initTest() {
		user = new User(1, role, "Doro", "doro@doro.fr", "mdp", true, connections, account);

	}

	@Test
	public void gettersTest() {
		assertThat(user.getId()).isEqualTo(1);
		assertThat(user.getRole()).isEqualTo(role);
		assertThat(user.getName()).isEqualTo("Doro");
		assertThat(user.getEmail()).isEqualTo("doro@doro.fr");
		assertThat(user.getPassword()).isEqualTo("mdp");
		assertThat(user.isActive()).isEqualTo(true);
		assertThat(user.getConnections()).isEqualTo(connections);
		assertThat(user.getAccount()).isEqualTo(account);

	}

	@Test
	public void settersTest() {
		user.setId(2);
		user.setRole(roleSet);
		user.setName("Mamoudou");
		user.setEmail("ma@ma.fr");
		user.setPassword("pwd");
		user.setActive(false);
		user.setConnections(connectionsSet);
		user.setAccount(accountSet);

		assertThat(user.getId()).isEqualTo(2);
		assertThat(user.getRole()).isEqualTo(roleSet);
		assertThat(user.getName()).isEqualTo("Mamoudou");
		assertThat(user.getEmail()).isEqualTo("ma@ma.fr");
		assertThat(user.getPassword()).isEqualTo("pwd");
		assertThat(user.isActive()).isEqualTo(false);
		assertThat(user.getConnections()).isEqualTo(connectionsSet);
		assertThat(user.getAccount()).isEqualTo(accountSet);

	}

}
