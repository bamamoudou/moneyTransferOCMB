package com.paymybuddy.moneytranfer.modelsTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.moneytranfer.models.Role;

@ExtendWith(MockitoExtension.class)
public class RoleTest {
	
	private Role role;
	
	@BeforeEach
	public void initTest() {
		role = new Role(1, "admin");
	}
	
	@Test
	public void gettersTest() {
		assertThat(role.getId()).isEqualTo(1);
		assertThat(role.getName()).isEqualTo("admin");
	}
	
	@Test
	public void settersTest() {
		role.setId(2);
		role.setName("user");
		
		assertThat(role.getId()).isEqualTo(2);
		assertThat(role.getName()).isEqualTo("user");
	}

}
