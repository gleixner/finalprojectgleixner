package com.expeditors.training.course3demo.aggregates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.expeditors.training.course3demo.dao.ContainerRepository;
import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.model.Shipment;
import com.expeditors.training.course3demo.routing.Dijkstra;
import com.expeditors.training.course3demo.routing.Port;
import com.expeditors.training.course3demo.routing.Utilities;
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
//		containers.addAll(containerRepository.
//				findByLocationAndDestinationAndStatus(shipment.getOrigin(), shipment.getDestination(), Status.READY) );
		containers.addAll( containerRepository.findAll() );
	}
	
	//Returns and empty list if there is no best routing
	public List<List<Container>> getBestRouting() {
		List<Port> ports = Utilities.buildPortGraph(containers, shipment.getVolume());
		Port originPort = Utilities.findPort(ports, shipment.getOrigin() );
		Port destinationPort = Utilities.findPort(ports, shipment.getDestination());
		List<List<Container>> result = new ArrayList<>();
		
		if( originPort != null ) {
			Collection<Port> goals = new ArrayList<>();
			goals.add(destinationPort);
			Dijkstra<Port> dij = new Dijkstra<Port>();
			List<Port> bestRoute = dij.findPath(ports, originPort, goals);
			
			for( int i = 0; i < bestRoute.size() - 1; ++i ) {
				Port port = bestRoute.get(i);
				Port nextPort = bestRoute.get(i+1);
				List<Container> cachedRoute = port.getCachedRoute(nextPort);
				result.add( cachedRoute );
			}
		}
		return result;
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
