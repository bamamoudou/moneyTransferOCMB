package com.paymybuddy.moneytranfer.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.paymybuddy.moneytranfer.models.Transaction;
import com.paymybuddy.moneytranfer.models.TransactionDTO;
import com.paymybuddy.moneytranfer.models.User;
import com.paymybuddy.moneytranfer.services.AccountService;
import com.paymybuddy.moneytranfer.services.BankAccountService;
import com.paymybuddy.moneytranfer.services.ConnectionService;
import com.paymybuddy.moneytranfer.services.TransactionService;
import com.paymybuddy.moneytranfer.services.UserService;

@RestController
public class TransactionController {
	private UserService userService;
	private ConnectionService connectionService;
	private TransactionService transactionService;
	private AccountService accountService;
	private BankAccountService bankAccountService;

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	public TransactionController(UserService userService, ConnectionService connectionService,
			TransactionService transactionService, AccountService accountService, BankAccountService bankAccountService) {
		this.userService = userService;
		this.connectionService = connectionService;
		this.transactionService = transactionService;
		this.accountService = accountService;
		this.bankAccountService = bankAccountService;
	}

	@GetMapping("/user/transfer")
	public ResponseEntity<String> getTransfer() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userFromAuth = userService.getUserFromAuth(auth);
		if (userFromAuth != null) {
			List<User> connectedUsers = connectionService.findConnectedUsersByUser(userFromAuth);
			List<Transaction> transactions = transactionService.findTransactionsByAccount(userFromAuth.getAccount());
			List<TransactionDTO> transactionDTOList = new ArrayList<>();

			for (Transaction transaction : transactions) {
				if (transaction.getTransactionType().getType().equals("PayMyBuddy")) {
					TransactionDTO transactionDTO = new TransactionDTO();
					transactionDTO.setToUserEmail(
							accountService.findAccountById(transaction.getRecipientAccountId()).getUser().getEmail());
					transactionDTO.setAmount(transaction.getAmount().toString());
					transactionDTO.setDescription(transaction.getDescription());
					transactionDTOList.add(transactionDTO);
				} else if (transaction.getTransactionType().getType().equals("CreditMyAccount")) {
					createTransactionDTOBank(transactionDTOList, transaction);
				} else if (transaction.getTransactionType().getType().equals("TransferToBankAccount")) {
					createTransactionDTOBank(transactionDTOList, transaction);
				}
			}
			if (transactionDTOList.isEmpty()) {
				transactionDTOList.add(new TransactionDTO("Pay some buddies!", "0.0", ""));
			}

		}

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PostMapping("/user/transfer")
	public ResponseEntity<String> postNewTransfer(@RequestParam String email, @RequestParam String description,
			@RequestParam String amount) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userFromAuth = userService.getUserFromAuth(auth);
		LOGGER.debug("HTTP POST request received for postTransfer: {} {} {} {}", userFromAuth, email, description, amount);
		if (userFromAuth != null) {
			transactionService.createTransactionByPayMyBuddy(userFromAuth, email, description, amount);
			LOGGER.info("HTTP POST request received for postTransfer, SUCCESS");
		} else {
			LOGGER.error("HTTP Post request rejected for postTransfer, ERROR");
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	private void createTransactionDTOBank(List<TransactionDTO> transactionDTOList, Transaction transaction) {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setToUserEmail("");
		transactionDTO.setAmount(transaction.getAmount().toString());
		transactionDTO.setDescription(transaction.getDescription());
		transactionDTOList.add(transactionDTO);
	}

}
