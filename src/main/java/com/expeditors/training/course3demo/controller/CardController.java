package com.expeditors.training.course3demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.expeditors.training.course3demo.model.Card;


@Controller
@RequestMapping("/card")
public class CardController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value="add.html", method=RequestMethod.GET)
	public String showAddCard(Model m, @AuthenticationPrincipal User activeUser ) {
		logger.info("User adding card: " + activeUser.getUsername() );
		m.addAttribute("card", new Card() );
		return "addCard";
	}
}
