package com.expeditors.training.course3demo.aggregates;

import java.util.ArrayList;
import java.util.List;

import com.expeditors.training.course3demo.dao.ContainerRepository;
import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.model.Shipment;
import com.expeditors.training.course3demo.service.ShipmentService;

public class DijkstraRoutingAggregate {

	private ShipmentService shipmentService;
	private ContainerRepository containerRepository;

	Shipment shipment;
	List<Container> containers;

	public DijkstraRoutingAggregate( ShipmentService shipmentService, ContainerRepository containerRepository,
			Shipment shipment) {
		this.shipmentService = shipmentService;
		this.containerRepository = containerRepository;
		this.shipment = shipment;
		containers = new ArrayList<>();
		containers.addAll(containerRepository.
				findByLocationAndDestinationAndStatus(shipment.getOrigin(), shipment.getDestination(), Status.READY) );
		if( !enoughCapacity() )
			containers.addAll( containerRepository.findByLocationAndStatusAndDestinationIsEmpty( shipment.getOrigin(), Status.READY ));
	}

	//Returns true if the total capacity on that lane is equal to or greater
	//than the total 
	boolean enoughCapacity() {
		double containerCapacity = calculateContainersCapacity(containers);
		return containerCapacity >= shipment.getVolume();
	}
	
	double calculateContainersCapacity(List<Container> containers) {
		double containerCapacity = 0;
		
		for( Container container : containers ) {
			containerCapacity += container.currentCapacity();
		}
		return containerCapacity;
	}
	
}
