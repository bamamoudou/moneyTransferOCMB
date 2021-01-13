package com.paymybuddy.moneytranfer.controllersTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

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

import com.paymybuddy.moneytranfer.models.Account;
import com.paymybuddy.moneytranfer.models.BankAccount;
import com.paymybuddy.moneytranfer.models.Connection;
import com.paymybuddy.moneytranfer.models.Role;
import com.paymybuddy.moneytranfer.models.Transaction;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.services.AccountService;
import com.paymybuddy.moneytranfer.services.BankAccountService;
import com.paymybuddy.moneytranfer.services.ConnectionService;
import com.paymybuddy.moneytranfer.services.TransactionService;
import com.paymybuddy.moneytranfer.services.UserService;

@AutoConfigureMockMvc
@SpringBootTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringSecurityAuthTestConfig.class)
@RunWith(SpringRunner.class)
public class TransactionControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webContext;

	@MockBean
	private UserService userService;

	@MockBean
	private ConnectionService connectionService;

	@MockBean
	private TransactionService transactionService;

	@MockBean
	private AccountService accountService;

	@MockBean
	private BankAccountService bankAccountService;

	private User user;
	private Account userAccount;
	private Authentication auth;
	private SecurityContext securityContext;

	@BeforeEach
	public void setUp() {
		user = new User();
		user.setEmail("user@test.fr");
		user.setPassword("test123");
		user.setName("user");
		user.setRole(new Role("User"));

		userAccount = new Account();
		userAccount.setUser(user);
		userAccount.setId(1);
		userAccount.setBalance(new BigDecimal(100.0));

		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();

		auth = mock(Authentication.class);
		securityContext = mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);
	}

	@Test
	public void getTransferIfUserIsLoggedInNoTransactionsAndStatusIsSuccessful() throws Exception {
		when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(auth);
		when(userService.getUserFromAuth(auth)).thenReturn(user);
		when(connectionService.findConnectedUsersByUser(user)).thenReturn(new ArrayList<>());
		when(transactionService.findTransactionsByAccount(userAccount)).thenReturn(new ArrayList<>());

		mockMvc.perform(get("/user/transfer")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void getTransferIfUserIsLoggedInPayMyBuddyTransactionAndStatusIsSuccessful() throws Exception {
		User user2 = new User();
		user2.setEmail("user@test.fr");

		Account user2Account = new Account();
		user2Account.setUser(user2);
		user2Account.setId(2);
		user2Account.setBalance(new BigDecimal(100.0));

		Connection connection = new Connection(user, user2.getId());

		Transaction payMyBuddyTransaction = new Transaction();
		payMyBuddyTransaction.setAccount(userAccount);
		payMyBuddyTransaction.setRecipientAccountId(2);
		payMyBuddyTransaction.setAmount(new BigDecimal(5.0));

		when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(auth);
		when(userService.getUserFromAuth(auth)).thenReturn(user);
		when(connectionService.findConnectedUsersByUser(user)).thenReturn(Arrays.asList(user2));
		when(transactionService.findTransactionsByAccount(userAccount)).thenReturn(Arrays.asList(payMyBuddyTransaction));

		mockMvc.perform(get("/user/transfer")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void getTransferIfUserIsLoggedIncreditAccountTransactionAndStatusIsSuccessful() throws Exception {
		BankAccount bankAccount = new BankAccount(userAccount, "testBankAccountNumber");
		bankAccount.setId(1);

		Transaction creditAccountTransaction = new Transaction();
		creditAccountTransaction.setAccount(userAccount);
		creditAccountTransaction.setBankAccount(bankAccount);
		creditAccountTransaction.setAmount(new BigDecimal(100.0));

		when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(auth);
		when(userService.getUserFromAuth(auth)).thenReturn(user);
		when(connectionService.findConnectedUsersByUser(user)).thenReturn(new ArrayList<>());
		when(transactionService.findTransactionsByAccount(userAccount))
				.thenReturn(Arrays.asList(creditAccountTransaction));

		mockMvc.perform(get("/user/transfer")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void getTransferIfUserIsLoggedInTransferToBankAccountTransactionAndStatusIsSuccessful() throws Exception {
		BankAccount bankAccount = new BankAccount(userAccount, "testBankAccountNumber");
		bankAccount.setId(1);

		Transaction transferToBankTransaction = new Transaction();
		transferToBankTransaction.setAccount(userAccount);
		transferToBankTransaction.setBankAccount(bankAccount);
		transferToBankTransaction.setAmount(new BigDecimal(100.0));

		when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(auth);
		when(userService.getUserFromAuth(auth)).thenReturn(user);
		when(connectionService.findConnectedUsersByUser(user)).thenReturn(new ArrayList<>());
		when(transactionService.findTransactionsByAccount(userAccount))
				.thenReturn(Arrays.asList(transferToBankTransaction));

		mockMvc.perform(get("/user/transfer")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void postTransferIfUserIsLoggedInAndStatusIsRedirectionToTransfer() throws Exception {
		String description = "Test Description";
		String amount = "100.00";

		when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(auth);
		when(userService.getUserFromAuth(auth)).thenReturn(user);

		mockMvc.perform(post("/user/transfer").queryParam("email", user.getEmail()).queryParam("description", description)
				.queryParam("amount", amount)).andExpect(status().isOk());
	}

	@Test
	public void postTransferIfUserIsNotLoggedInAndStatusIsClientError() throws Exception {
		mockMvc.perform(post("/user/transfer").requestAttr("user", user)).andExpect(status().is4xxClientError());
	}

	@Test
	public void getTransactionsLogIfAdminIsLoggedInAndStatusISuccessful() throws Exception {
		User adminUser = new User();
		adminUser.setEmail("admin@test.fr");
		adminUser.setPassword("test123");
		adminUser.setName("admin");
		adminUser.setRole(new Role("Admin"));

		when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(auth);
		when(userService.getUserFromAuth(auth)).thenReturn(adminUser);

		mockMvc.perform(get("/admin/transactions")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void profileIfUserIsLoggedInAndStatusISuccessful() throws Exception {
		user.setAccount(userAccount);
		when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(auth);
		when(userService.getUserFromAuth(auth)).thenReturn(user);

		mockMvc.perform(get("/user/profile")).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void postAddBankAccountIfUserIsLoggedInAndStatusIsSuccess() throws Exception {
		user.setAccount(userAccount);
		BankAccount bankAccount = new BankAccount();
		bankAccount.setBankAccountNumber("testBankAccountNumber");

		when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(auth);
		when(userService.getUserFromAuth(auth)).thenReturn(user);
		when(bankAccountService.createBankAccount(user, "testBankAccountNumber")).thenReturn(bankAccount);

		mockMvc.perform(post("/user/addBankAccount").queryParam("bankAccountNumber", "testBankAccountNumber"))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void postCreditAccountIfUserIsLoggedInAndStatusIsSuccess() throws Exception {
		user.setAccount(userAccount);
		String amount = "100.00";

		when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(auth);
		when(userService.getUserFromAuth(auth)).thenReturn(user);

		mockMvc.perform(post("/user/creditAccount").queryParam("amount", amount)).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void postTransferToBankAccountIfUserIsLoggedInAndStatusIsSuccess() throws Exception {
		user.setAccount(userAccount);

		when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(auth);
		when(userService.getUserFromAuth(auth)).thenReturn(user);

		mockMvc.perform(post("/user/transferToBankAccount")).andExpect(status().is2xxSuccessful());
	}

}
