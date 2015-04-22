package com.expeditors.training.course3demo.routing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.expeditors.training.course3demo.model.Container;

public class Route implements Comparable<Route> {
	
	private Map<Container, Double> containerAssignments = new HashMap<>();
	
	/**
	 * Returns the cost for the best selection of containers between the two nodes.
	 * Returns Double.POSITIVE_INFINITY if there is no legal selection of containers
	 * @return
	 */
	double getCost() {
		double sum = 0;
		for(Container container : containerAssignments.keySet() ) {
			sum += container.getRate() * getAssignedCapacityForContainer(container);
		}
		if( containerAssignments.keySet().size() == 0 )
			sum = Double.POSITIVE_INFINITY;
		return sum;
	}
	
	public Set<Container> getContainers() {
		return containerAssignments.keySet();
	}
	
	void setAssignedCapacityForContainer(Container container, Double capacity){
		containerAssignments.put(container, capacity);
	}
	
	public double getAssignedCapacityForContainer(Container container ){
		return containerAssignments.get(container);
	}
	
	void removeContainer(Container container ) {
		containerAssignments.remove(container);
	}

	Set<Container> getContainerSet() {
		return containerAssignments.keySet();
	}
	
	boolean contains( Container container ) {
		return containerAssignments.containsKey( container );
	}
	
	Route copyRoute() {
		Route route = new Route();
		Map<Container,Double> containerAssignmentsCopy = new HashMap<>();
		containerAssignmentsCopy.putAll(containerAssignments);
		route.containerAssignments = containerAssignmentsCopy;
		return route;
	}
	
	public int compareTo( Route route ) {
		double result = getCost() - route.getCost();
		return (int) Math.round(result);
	}
	
	public String toString() {
		return "Cost: " + getCost() + " Containers: " + containerAssignments;
	}
}
