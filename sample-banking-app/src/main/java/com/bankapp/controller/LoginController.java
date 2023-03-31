package com.bankapp.controller;

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

import com.bankapp.model.Customer;
import com.bankapp.service.ClientService;

import jakarta.validation.Valid;

@Controller
//@RequestMapping("/public")
public class LoginController {

	@Autowired
	private	ClientService clientService;
	

	@RequestMapping(value="/", method=RequestMethod.GET)
	private String goToWelcomePage(ModelMap model) {
		model.put("name", getLoggedinUsername());
		return "welcome";
	}
	
	@RequestMapping(value="register", method=RequestMethod.GET)
	private String goToReqisterPage(ModelMap model) {
		model.put("client", new Customer());
		return "registerPage";
	}

	@RequestMapping(value="/addClient", method=RequestMethod.POST)
	public String addNewClient(ModelMap model, @Valid Customer client, BindingResult reult) {
		if (reult.hasErrors()) {
			return "registerPage";
		}
		clientService.saveNewClient(client);
		return "welcome";
	}
	
 
	private String getLoggedinUsername() {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
		
	}
	
 
}