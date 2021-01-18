package com.paymybuddy.moneytranfer.controllersTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.paymybuddy.moneytranfer.models.Role;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.services.ConnectionService;
import com.paymybuddy.moneytranfer.services.UserService;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webContext;

	@MockBean
	private UserService userService;

	@MockBean
	private ConnectionService connectionService;

	private User user;
	private Authentication auth;
	private SecurityContext securityContext;

	@BeforeEach
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@BeforeEach
	public void setUp() {
		user = new User();
		user.setEmail("user@test.fr");
		user.setPassword("test123");
		user.setName("user");
		user.setRole(new Role("User"));

		auth = mock(Authentication.class);
		securityContext = mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);
	}

	@Test
	public void registerIfUrlValidAndStatusIsSuccessful() throws Exception {
		mockMvc.perform(get("/register").queryParam("email", user.getEmail())).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void registerNewUserIfValidUserInputAndStatusIsBadRequest() throws Exception {
		when(userService.findUserByEmail(user.getEmail())).thenReturn(null);
		mockMvc.perform(post("/register")).andExpect(status().isBadRequest());
	}

	@Test
	public void registerNewUserIfUserAlreadyExistsAndDisplaysErrorMessage() throws Exception {
		when(userService.findUserByEmail(user.getEmail())).thenReturn(user);

		mockMvc.perform(post("/register")).andExpect(status().isBadRequest());
	}

	@Test
	public void getAddConnectionIfUserIsLoggedInAndStatusIsSuccessful() throws Exception {
		when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(auth);
		when(userService.getUserFromAuth(auth)).thenReturn(user);

		mockMvc.perform(get("/user/connections").requestAttr("user", user)).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void createAddConnectionIfUserIsLoggedInAndStatusIsSuccess() throws Exception {
		User user2 = new User();
		user2.setEmail("user@test.com");
		user2.setPassword("test123");
		user2.setName("user");
		user2.setRole(new Role("User"));

		when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(auth);
		when(userService.getUserFromAuth(auth)).thenReturn(user);
		when(userService.findUserByEmail(user2.getEmail())).thenReturn(user2);

		mockMvc.perform(post("/user/addConnection").queryParam("email", user2.getEmail())).andExpect(status().isOk());
	}
}