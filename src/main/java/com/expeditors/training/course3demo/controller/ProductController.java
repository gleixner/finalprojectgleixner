package com.expeditors.training.course3demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.expeditors.training.course3demo.model.Product;
import com.expeditors.training.course3demo.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired 
	ProductService productService;
			  
	@RequestMapping(method=RequestMethod.GET,value="show")
	public ModelAndView showProduct(@RequestParam(value="product_id", defaultValue="1", required=false) int id) {				
		ModelAndView mav = new ModelAndView();		
 		mav.setViewName("showProduct");
 		logger.debug("Setting name to Latte"); 		
 		mav.addObject("product", productService.getProduct(id));
		return mav;		
	}
	
	@RequestMapping( method=RequestMethod.GET, value="add" )
	public String showAddProduct(Model model) {
		model.addAttribute("product", new Product() );
		return "addProduct";
	}
	
//	@RewuestMapping(method=RequestMethod.POST, value ="add.html" )
//	public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult result, Model m ) {
//		m.addAttribute("product", product);
//	}
}
