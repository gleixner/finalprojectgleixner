package com.expeditors.training.course3demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expeditors.training.course3demo.model.UserAccount;
import com.expeditors.training.course3demo.service.SecurityService;

@Controller
@RequestMapping("/security/")
public class SecurityController {

	@Autowired
	SecurityService securityService;
	
	@RequestMapping(value="register.html", method=RequestMethod.GET)
	public String displayRegisterPage( Model m ) {
		m.addAttribute("user_account", new UserAccount() );
		return "registerAccount";
	}
	
	@RequestMapping(value="register.html", method=RequestMethod.POST)
	public String registerAccount(@ModelAttribute("user_account") @Valid UserAccount account, 
								  BindingResult result, 
								  Model m) {
		String view = "showAccounts";
		if(result.hasErrors() ) {
			view = "registerAccount";
		}
		else {
			securityService.add(account);
		}
		return view;
	}
}
