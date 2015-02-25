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

import com.expeditors.training.course3demo.model.Product;
import com.expeditors.training.course3demo.model.Shipment;
import com.expeditors.training.course3demo.service.ShipmentService;


@Controller
@RequestMapping("/shipment")
public class ShipmentController {

	private static final Logger logger = LoggerFactory.getLogger( ShipmentController.class );
	
	@Autowired
	ShipmentService shipmentService;
	
	private static final int PAGE_SIZE = 3;
	
	@RequestMapping("/show.html")
	public String showShipments(
				@RequestParam(value="id", defaultValue="0", required=false) Long id,
				Model m 
				) {
		
		List<Shipment> result;
		if( id == 0 ) {
			result = shipmentService.getAll();
		}
		else {
			result = new ArrayList<>();
			result.add( shipmentService.getById( id ) );
		}
		
		m.addAttribute("shipments", result );
		return "showShipments";
	}
	
	@RequestMapping(value="/add.html", method=RequestMethod.GET)
	public String showAddShipment(Model m) {
		m.addAttribute("shipment", new Shipment() );
		return "addShipment";
	}
	
	
	@RequestMapping(value="/add.html", method=RequestMethod.POST)
	public String addShipment( @ModelAttribute @Valid Shipment s, BindingResult bind, Model m ) {
		String view;
		
		if( bind.hasErrors() ) {
			view = "addShipment";
		}
		else {
			shipmentService.save( s );
			view = "redirect:/shipment/show.html?name=" + s.getName();
		}
//		List<ObjectError> oe = bind.getAllErrors();
//		for(ObjectError o : oe) {
//			logger.info(o.toString());
//		}
		
		return view;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="list.html")
	public String listAllShipments(@RequestParam(value="page", required=false, defaultValue="0") Integer pagenum, Model m) {
		List<Shipment> shipments;
		
		int start = pagenum*PAGE_SIZE;
		shipments = shipmentService.list(start, PAGE_SIZE);
		
		m.addAttribute("shipments", shipments);
		m.addAttribute("page", pagenum);
		return "listShipments";
	}
	
}
