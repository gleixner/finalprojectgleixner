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
import com.expeditors.training.course3demo.routing.Route;
import com.expeditors.training.course3demo.routing.Utilities;
import com.expeditors.training.course3demo.service.ShipmentService;

public class DijkstraRoutingAggregate {

	Shipment shipment;
	List<Container> containers;

	public DijkstraRoutingAggregate( Shipment shipment, List<Container> containers ) {
		this.shipment = shipment;
		this.containers = containers;
	}
	
	//Returns and empty list if there is no route between nodes
	public List<Route> getBestPath() {
		List<Port> ports = Utilities.buildPortGraph(containers, shipment.getVolume());
		Port originPort = Utilities.findPort(ports, shipment.getOrigin() );
		Port destinationPort = Utilities.findPort(ports, shipment.getDestination());
		List<Route> result = new ArrayList<>();
		
		if( originPort != null ) {
			Collection<Port> goals = new ArrayList<>();
			goals.add(destinationPort);
			Dijkstra<Port> dij = new Dijkstra<Port>();
			List<Port> bestRoute = dij.findPath(ports, originPort, goals);
			
			for( int i = 0; i < bestRoute.size() - 1; ++i ) {
				Port port = bestRoute.get(i);
				Port nextPort = bestRoute.get(i+1);
				Route cachedRoute = port.getCachedRoute(nextPort);
				result.add( cachedRoute );
			}
		}
		return result;
	}
}
