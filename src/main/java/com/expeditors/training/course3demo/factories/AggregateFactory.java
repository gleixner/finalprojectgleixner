package com.expeditors.training.course3demo.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expeditors.training.course3demo.aggregates.ShipmentRoutingAggregate;
import com.expeditors.training.course3demo.dao.ContainerRepository;
import com.expeditors.training.course3demo.model.Shipment;
import com.expeditors.training.course3demo.service.CardService;
import com.expeditors.training.course3demo.service.ContainerService;
import com.expeditors.training.course3demo.service.ProductService;
import com.expeditors.training.course3demo.service.SecurityService;
import com.expeditors.training.course3demo.service.ShipmentService;

@Service
public class AggregateFactory {
	
	@Autowired
	CardService cardService;
	
	@Autowired
	ContainerService containerService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	ShipmentService shipmentService;
	
	@Autowired
	ContainerRepository containerRepository;


	public ShipmentRoutingAggregate getShipmentRoutingAggregate(Shipment shipment) {
		return new ShipmentRoutingAggregate(shipmentService, containerRepository, shipment);
	}
	
}
