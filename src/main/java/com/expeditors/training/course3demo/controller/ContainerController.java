package com.expeditors.training.course3demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.model.Shipment;
import com.expeditors.training.course3demo.service.ContainerService;



@Controller
@RequestMapping("/container")
public class ContainerController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ContainerService containerService;
	
	@RequestMapping("/show.html")
	public String showContainers(
				@RequestParam(value="container_id", defaultValue="0", required=false) Long id,
				Model m 
				) {
		
		List<Container> result;
		if( id == 0 ) {
			result = containerService.getAll();
		}
		else {
			result = new ArrayList<>();
			result.add( containerService.getById( id ) );
		}
		
		m.addAttribute("containers", result );
		return "showContainers";
	}
	
	@RequestMapping("/list.html")
	public String showContainers( Model m ) {
		List<Container> containers = containerService.getAll();
		m.addAttribute("containers", containers);
		return "showContainers";
	}
	
	@RequestMapping(value="/add.html", method=RequestMethod.GET)
	public String showAddContainers( Model m ) {
		m.addAttribute("container", new Container() );
		return "addContainer";
	}
	
	@RequestMapping(value="/add.html", method=RequestMethod.POST)
	public String addContainer( @ModelAttribute @Valid Container c,
								BindingResult bind) {
		String view = bind.hasErrors() ? "addContainer" : "showContainer";
		List<ObjectError> oe = bind.getAllErrors();
		for(ObjectError o : oe) {
			logger.info(o.toString());
		}

		return view;
	}
}
