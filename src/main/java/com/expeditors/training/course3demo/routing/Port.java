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
	Integer date;
	
	Strategy strategy = new RecursiveCostStrategy();
	
	//This value will be shared by a bunch of Port objects and 
	//they will use it to update each other on changes in capacity.
	//not thread safe so no shenannigans.
	//Here's why I have this.  I need two things to calculate the cost
	//of moving between nodes, destination and how much I'm noving.
	//The dijkstra class gives me the neighbor, but I still need the 
	//shipment size.  If I want to reuse the same graph between routing assignments
	//(not likely to happen soon) then I need to be able to change the shipment 
	//size for an entire graph of ports.
	private MutableDouble shipmentVolume;
	
	Map<Port,List<Container>> containersToPort = new HashMap<>();
	
	//This map contains the list of containers that should be booked
	//in order to move freight to a port
	Map<Port, Route> calculatedRoutes = new HashMap<>();
	
	@Override
	public double pathCostEstimate(Port goal) {
		// This method is not called in Dijkstra
		return 0;
	}
	
	@Override
	public double traverseCost(Port dest) {
		List<Container> containers = containersToPort.get(dest);
		Route bestRoute = strategy.getBestRoute(containers, shipmentVolume.doubleValue());
		calculatedRoutes.put( dest, bestRoute );
		return bestRoute.getCost();
	}

	@Override
	public Iterable<Port> neighbors() {
		return containersToPort.keySet();
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

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public MutableDouble getShipmentCapacity() {
		return shipmentVolume;
	}

	public void setShipmentCapacity(MutableDouble shipmentCapacity) {
		this.shipmentVolume = shipmentCapacity;
	}

	public void setContainersToPort(Map<Port, List<Container>> routes) {
		this.containersToPort = routes;
	}
	
	public Map<Port, List<Container>> getContainersToPort() {
		return containersToPort;
	}

	public Map<Port, Route> getCalculatedRoutes() {
		return calculatedRoutes;
	}

	public Port(String name, MutableDouble shipmentCapacity) {
		this.name = name;
		this.shipmentVolume = shipmentCapacity;
	}
	
	public Port(String name, Integer date, MutableDouble shipmentCapacity) {
		this.name = name;
		this.date = date;
		this.shipmentVolume = shipmentCapacity;
	}
	
	public void addContainerToPort( Port destPort, Container container ) {
		List<Container> containers = containersToPort.get( destPort );
		if( containers == null ) {
			containers = new ArrayList<>();
			containersToPort.put(destPort, containers);
		}
		containers.add( container );
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name);
		sb.append(", ");
		sb.append("Date: " + date);
		sb.append(", ");

		sb.append("neighbors{");
		for( Port port : neighbors() ) {
			sb.append(" " + port.name + port.getDate() + ", ");
		}
		sb.append("};");
		return sb.toString();
	}
	
	public Integer getDate() {
		return date;
	}

	public Route getCachedRoute( Port port ) {
		return calculatedRoutes.get(port);
	}
	
}
