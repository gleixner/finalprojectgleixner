package com.expeditors.training.course3demo.routing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.mutable.MutableDouble;

import com.expeditors.training.course3demo.model.Container;

public class Port implements Node<Port> {
	
	String name;
	
	RouteStrategy strategy = Utilities.getCostStrategy();
	
	//This value will be shared by a bunch of Port objects and 
	//they will use it to update each other on changes in capacity.
	//not thread safe so no shenannigans.
	private MutableDouble shipmentVolume;
	
	Map<Port,List<Container>> routes = new HashMap<>();
	
	//This map contains the list of containers that should be booked
	//in order to move freight to a port
	Map<Port, List<Container>> calculatedRoutes = new HashMap<>();
	
	
	
	@Override
	public double pathCostEstimate(Port goal) {
		// This method is not called in Dijkstra
		return 0;
	}

	@Override
	public double traverseCost(Port dest) {
		List<Container> containers = routes.get( dest );
		List<Container> assignedContainers = new ArrayList<>();
		Collections.sort(containers, strategy);
		double allocatedVolume = 0;
		double cost = 0;
		
		int i = 0;
		while(i < containers.size() && allocatedVolume < shipmentVolume.doubleValue() ) {
			assignedContainers.add( containers.get(i) );
			allocatedVolume += containers.get(i).currentCapacity();
			++i;
		}
		calculatedRoutes.put(dest, assignedContainers);
		//If shipmentVolume is +infinity, than this is a test mode and all containers
		//for this route are being requested
		if( shipmentVolume.doubleValue() != Double.POSITIVE_INFINITY 
				&& allocatedVolume < shipmentVolume.doubleValue() ) {
			cost = Double.POSITIVE_INFINITY;
		}
		else {
			for( Container container : assignedContainers ) {
				cost += container.getRate();
			}
		}
		return cost;
	}

	@Override
	public Iterable<Port> neighbors() {
		return routes.keySet();
	}
	
//	public double getCapacityForRoute(String destination) {
//		double sum = 0;
//		for()
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RouteStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(RouteStrategy strategy) {
		this.strategy = strategy;
	}

	public MutableDouble getShipmentCapacity() {
		return shipmentVolume;
	}

	public void setShipmentCapacity(MutableDouble shipmentCapacity) {
		this.shipmentVolume = shipmentCapacity;
	}

	public void setRoutes(Map<Port, List<Container>> routes) {
		this.routes = routes;
	}

	public Map<Port, List<Container>> getCalculatedRoutes() {
		return calculatedRoutes;
	}

	public void setCalculatedRoutes(Map<Port, List<Container>> calculatedRoutes) {
		this.calculatedRoutes = calculatedRoutes;
	}

	public Map<Port, List<Container>> getRoutes() {
		return routes;
	}

	public Port(String name, MutableDouble shipmentCapacity) {
		super();
		this.name = name;
		this.shipmentVolume = shipmentCapacity;
	}
	
	public void putContainerOnRoute( Port destPort, Container container ) {
		List<Container> containers = routes.get( destPort );
		if( containers == null ) {
			containers = new ArrayList<>();
			routes.put(destPort, containers);
		}
		containers.add( container );
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name " + name);
		sb.append("\n");
		sb.append("Strategy: " + strategy.getName());
		sb.append("\n");

		sb.append("neighbors:\n");
		for( Port port : neighbors() ) {
			sb.append("\t" + port.name + "\n");
		}
		return sb.toString();
	}
	
}
