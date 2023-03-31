package com.bankapp.service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankapp.model.Account;
import com.bankapp.model.Customer;
import com.bankapp.repository.CustomerRepository;

@Service
public class ClientService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private CustomerRepository customerRepository;

	public void saveNewClient(Customer client) {

		String password = client.getPassword();
		client.setPassword(encoder.encode(password));
		client.setRole("USER");
		client.setCreateDate(new Date(System.currentTimeMillis()));
		
		Set<Account> accounts=new HashSet<>();
		Account account=new Account();
		account.setAccountType("Standart");
		account.setCreateDate(new Date(System.currentTimeMillis()));
		account.setCustomer(client);
		accounts.add(account);
		client.setAccounts(accounts);
		
		customerRepository.save(client);
	}

	public List<Customer> getAllUsers() {
		return customerRepository.findAll();
	}
}
