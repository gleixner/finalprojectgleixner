package com.expeditors.training.course3demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
//@RequestMapping("/")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
//	@RequestMapping("home")
//	public String home( Model model ) {
//		logger.debug("Accessing home page");
//		return "home";
//	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "redirect:/product/list.html";
	}
	
}
