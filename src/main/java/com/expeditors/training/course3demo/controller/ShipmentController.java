package com.expeditors.training.course3demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.expeditors.training.course3demo.dto.ShipmentRoutesView;
import com.expeditors.training.course3demo.model.Product;
import com.expeditors.training.course3demo.model.Shipment;
import com.expeditors.training.course3demo.model.ShipmentContainerAssociation;
import com.expeditors.training.course3demo.service.ShipmentService;


@Controller
@RequestMapping("/shipment/")
public class ShipmentController {

	private static final Logger logger = LoggerFactory.getLogger( ShipmentController.class );

	@Autowired
	ShipmentService shipmentService;

	private static final int PAGE_SIZE = 6;


	@RequestMapping(value={"add.html", "edit.html"}, method=RequestMethod.GET)
	public String showAddShipment(@RequestParam(value = "id", required=false) Long id ,Model m) {
		Shipment s;
		if( id != null && id > 0 ) {
			s = shipmentService.getById(id);
			m.addAttribute("shipment", s);
		}
		else {
			m.addAttribute("shipment", new Shipment() );
		}
		return "addShipment";
	}


	@RequestMapping(value={"add.html", "edit.html"}, method=RequestMethod.POST)
	public String addShipment( @ModelAttribute @Valid Shipment s, BindingResult bind, Model m ) {
		String view;

		if( bind.hasErrors() ) {
			view = "addShipment";
		}
		else {
			view = "redirect:/shipment/show.html";
			Long id = shipmentService.save(s);
			m.addAttribute("id", id);
		}
		//		List<ObjectError> oe = bind.getAllErrors();
		//		for(ObjectError o : oe) {
		//			logger.info(o.toString());
		//		}

		return view;
	}

	@RequestMapping(value={"list.html", "show.html"}, params = {"!id"})
	public String listAllShipments(@RequestParam(value="page", required=false, defaultValue="0") Integer page,
			@RequestParam(value="id", defaultValue="0", required=false) Long id,
			Model m) {
		List<Shipment> shipments;
		String view = "showShipments";
		boolean more = true;

		int start = page*PAGE_SIZE;
		shipments = shipmentService.list(start, PAGE_SIZE + 1);

		if( shipments.size() < PAGE_SIZE + 1 ) {
			more = false;
		}
		else {
			shipments.remove( shipments.size() - 1 );
		}
		//set the editable boolean
		//		for(Shipment s : shipments )
		//			s.editable();

		m.addAttribute("shipments", shipments);
		m.addAttribute("page", page);
		m.addAttribute("more", more);

		return view;
	}

	@RequestMapping(value={"list_old.html", "show_old.html"}, params = {"id"})
	public String listAShipment( @RequestParam(value="id") Long id, Model m) {
		String view = "showShipments";
		if( id > 0 ) {
			List<Shipment> shipments = new ArrayList<>();
			shipments.add( shipmentService.getById( id ));
			m.addAttribute("shipments", shipments);
			if( shipments.size() == 1 )
				m.addAttribute("shipment", shipments.get(0) );
		}
		else
			view = "redirect:/shipment/list.html";
		return view;
	}
	
	@RequestMapping(value={"list.html", "show.html"}, params = {"id"})
	public String listAShipmentDijkstra( @RequestParam(value="id") Long id, Model m) {
		String view = "showShipmentsDijkstra";
		if( id > 0 ) {
			Shipment shipment = shipmentService.getById( id );
			ShipmentRoutesView dataView = shipmentService.getShipmentRoutesView( id );
			List<Shipment> shipments = new ArrayList<>();
			shipments.add( shipment );
			m.addAttribute("shipment", shipment);
			m.addAttribute("shipments", shipments);
			m.addAttribute("dataView", dataView );
		}
		else
			view = "redirect:/shipment/list.html";
		return view;
	}

	@RequestMapping(value="route.html")
	public String routeShipment(@RequestParam(value="id") Long id,
			@RequestParam(value="delete", required = false, defaultValue ="false") boolean delete,
			Model m ) {
		String view = "redirect:/shipment/show.html?id=" + id;
		if(delete)
			shipmentService.deleteRouting( id );
		else {
			shipmentService.routeShipment(id);
		}
		return view;
	}

	@RequestMapping(value="remove.html")
	public String removeShipment(@RequestParam(value="id", required=true) Long id) {
		shipmentService.delete(id);
		return "redirect:/shipment/list.html";
	}

	@RequestMapping(value="routeDijkstra.html")
	public String routeShipmentDijkstra(@RequestParam(value="id") Long id,
			@RequestParam(value="delete", required = false, defaultValue ="false") boolean delete,
			Model m ) {
//		String view = "redirect:/shipment/showDijkstra.html?id=" + id;
		String view = "redirect:/shipment/show.html?id=" + id;

		if(delete)
			shipmentService.deleteRouting( id );
		else {
			shipmentService.routeShipmentDijkstra(id);
		}
		return view;
	}
}
