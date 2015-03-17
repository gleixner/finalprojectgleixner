package com.expeditors.training.course3demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expeditors.training.course3demo.model.Product;
import com.expeditors.training.course3demo.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired 
	ProductService productService;
	
	private static final int PAGE_SIZE = 3;
			  
	@RequestMapping(method=RequestMethod.GET,value="/show.html")
	public String showProduct(@RequestParam(value="id", defaultValue="6", required=false) long id, Model m) {				
 		Product product = productService.getProduct(id);
 		logger.debug("Setting name to Latte"); 		
 		m.addAttribute("product", product);
		return "showProduct";		
	}
	
	@RequestMapping( method=RequestMethod.GET, value="/edit.html" )
	public String showEditProduct(@RequestParam(value="id", required=false) Long id, Model model) {
		Product p;
		if(id != null ) {
			p = productService.getProduct(id);
		}
		else {
			p = new Product();
		}
		
		model.addAttribute("product", p );
		return "editProduct";
	}
	
	@RequestMapping(method=RequestMethod.POST, value ="/edit.html" )
	public String editProduct(@ModelAttribute("product") @Valid Product product, BindingResult bind, Model m) {
		String view;
		if(bind.hasErrors() ) {
			view = "editProduct";
		}
		else {
			view = "redirect:/product/show.html";
			Long id = productService.save(product);
			m.addAttribute("id", id);
		}
		return view;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="list.html")
	public String listAllProducts(@RequestParam(value="page", required=false, defaultValue="0") Integer pagenum, Model m) {
		List<Product> products;
		
		int start = pagenum*PAGE_SIZE;
		products = productService.list(start, PAGE_SIZE);
		
		m.addAttribute("products", products);
		m.addAttribute("page", pagenum);
		return "listProducts";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="remove.html")
	public String removeProduct(@RequestParam(value="id", required=true) Long id) {
		productService.delete(id);
		return "redirect:/product/list.html";
	}
	
	@RequestMapping(value="buy.html", method=RequestMethod.GET)
	public String buyProduct(
			@RequestParam(value="id", required=true) Long productId,
			HttpSession session,
			Model m) {
		session.setAttribute("product", productId);
		return "redirect:/card/find.html";
	}
	
}
