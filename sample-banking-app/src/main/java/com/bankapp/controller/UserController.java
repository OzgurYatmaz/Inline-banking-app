package com.bankapp.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bankapp.model.Customer;
import com.bankapp.model.Transaction;
import com.bankapp.service.ClientService;
import com.bankapp.service.MoneyTransferService;
import com.bankapp.utils.DBUtils;

import jakarta.validation.Valid;

@Controller
//@RequestMapping("/public")
public class UserController {

	@Autowired
	private	ClientService clientService;
	
	@Autowired
	private MoneyTransferService transferService;
	
	@RequestMapping(value="showAllUsers", method=RequestMethod.GET)
	private String ShowAllUsers(ModelMap model) {
		List<Customer> customers=clientService.getAllUsers();
		List<Customer> otherUsers = customers.stream().filter(c->!c.getName().equalsIgnoreCase(getLoggedinUsername())).collect(Collectors.toList());
		model.put("clients", otherUsers);
		model.put("name", getLoggedinUsername());
		List<Customer> currentUser = customers.stream().filter(c->c.getName().equalsIgnoreCase(getLoggedinUsername())).collect(Collectors.toList());
		Customer customer = currentUser.get(0);
		model.put("balance", customer.getMoney());
		model.put("senderId", customer.getId());
		return "clientList";
	}
	
	@RequestMapping(value="/sendMoneyPage", method=RequestMethod.GET)
	public String sendMoneyPage(@RequestParam int id,@RequestParam int senderId, @RequestParam String name, ModelMap model) {
		Transaction t=new Transaction();
		t.setReceiverId(id);
		t.setSenderId(senderId);
		model.put("transaction", t);
		model.put("name", name);
		return "sendMoney";
	}
	
	@RequestMapping(value="/sendMoney", method=RequestMethod.POST)
	public String sendMoneym(Transaction t) {
		try {
			transferService.sendMoney(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:showAllUsers";
	}
	

 
	

	private String getLoggedinUsername() {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
		
	}
	
	
 
}