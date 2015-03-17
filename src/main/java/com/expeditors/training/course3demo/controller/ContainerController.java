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

import com.expeditors.training.course3demo.dto.FindContainerCriteria;
import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.model.Product;
import com.expeditors.training.course3demo.model.Shipment;
import com.expeditors.training.course3demo.service.ContainerService;



@Controller
@RequestMapping("/container")
public class ContainerController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ContainerService containerService;
	
	private static final int PAGE_SIZE = 100;
	
	@RequestMapping("/show.html")
	public String showContainers(
				@RequestParam(value="id", defaultValue="0", required=false) Long id,
				Model m 
				) {
		
		String view = "showContainers";
		List<Container> result;
		
		if( id == 0 ) {
//			result = containerService.list(0, 10);
			view = "redirect:/container/list.html";
		}
		else {
			result = new ArrayList<>();
			result.add( containerService.getById( id ) );
			m.addAttribute("containers", result );
		}
		
		return view;
	}
	
	@RequestMapping(value="/list.html", method=RequestMethod.GET )
	public String showContainers(@RequestParam(value="page", required=false, defaultValue="0") int page, Model m ) {
		List<Container> containers;
		String view = "showContainers";
		boolean more = true;
		
		int start = page*PAGE_SIZE;
		//get 1 more result than you need to check if there are more elements than what
		//will fit on the current page
		containers = containerService.list(start, PAGE_SIZE + 1);
		
		if( containers.size() < PAGE_SIZE + 1 )
			more = false;
		else 
			containers.remove( containers.size() -1 );
		
		m.addAttribute("containers", containers);
		m.addAttribute("page", page);
		m.addAttribute("more", more);
		m.addAttribute("criteria", new FindContainerCriteria() ); //for searching
		return view;
	}
	
	@RequestMapping(value="/list.html", method=RequestMethod.POST )
	public String findContainerResults(@ModelAttribute("criteria") FindContainerCriteria criteria, Model m) {
		String view = "showContainers";

		if( criteria.isEmpty() ) 
			view = "redirect:/container/list.html";
		else
			m.addAttribute("containers", containerService.findContainers(criteria));

		return view;
	}
	
	@RequestMapping(value={ "/edit.html", "/add.html" }, method=RequestMethod.GET)
	public String showEditContainer(@RequestParam(value="id", required=false) Long id, Model m) {
		Container c;
		if(id != null ) 
			c = containerService.getById(id);
		else
			c = new Container();
		
		m.addAttribute("container", c);
		return "editContainer";
	}
	
	@RequestMapping(value ={ "/edit.html", "/add.html" }, method=RequestMethod.POST )
	public String editContainer(@ModelAttribute("container") @Valid Container container, BindingResult bind, Model m) {
		String view;
		if(bind.hasErrors() ) {
			view = "editContainer";
		}
		else {
			view = "redirect:/container/show.html";
			Long id = containerService.save(container);
			m.addAttribute("id", id);
		}
		return view;
	}
	
	@RequestMapping(value="remove.html", method=RequestMethod.GET )
	public String removeContainer(@RequestParam(value="id", required=true) Long id) {
		containerService.delete(id);
		return "redirect:/container/list.html";
	}
}
