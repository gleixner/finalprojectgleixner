package com.expeditors.training.course3demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorLoginController {
	
	@RequestMapping("error")
	public String customError(HttpServletRequest request, HttpServletResponse response, Model m) {
		Integer status = (Integer) request.getAttribute( "javax.servlet.error.status_code");
		 m.addAttribute("code", status);
		 
		 return "customError";
	}

//	@RequestMapping("login")
//	public String customLogin() {
//		return "login";
//	}
	
}
