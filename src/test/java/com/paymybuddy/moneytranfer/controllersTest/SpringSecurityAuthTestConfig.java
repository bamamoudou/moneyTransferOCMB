package com.paymybuddy.moneytranfer.controllersTest;

import java.util.Arrays;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.paymybuddy.moneytranfer.models.Role;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.models.UserProfilDetails;

@TestConfiguration
public class SpringSecurityAuthTestConfig {

	@Bean
	@Primary
	public UserDetailsService userDetailsService() {
		Role userRole = new Role("User");
		User user = new User(userRole, "user@test.fr", "test123", "user");
		user.setActive(true);
		UserProfilDetails MyUser = new UserProfilDetails(user, userRole);

		Role adminRole = new Role("Admin");
		User adminUser = new User(adminRole, "admin@test.com", "test123", "admin");
		adminUser.setActive(true);
		UserProfilDetails adminMyUser = new UserProfilDetails(adminUser, adminRole);

		return new InMemoryUserDetailsManager(Arrays.asList(MyUser, adminMyUser));
	}
}