package com.expeditors.training.course3demo.routing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.expeditors.training.course3demo.aggregates.ShipmentRoutingAggregate.OrderShipmentSolutions;
import com.expeditors.training.course3demo.model.Container;

public class RecursiveCostStrategy implements Strategy {
	
	List<Container> containers;
	double shipmentSize;

//	public RecursiveCostStrategy( List<Container> containers, double shipmentSize) {
//		this.containers = containers;
//		this.shipmentSize = shipmentSize;
//	}
	
	@Override
	//Return an empty route if there is no best route
	public Route getBestRoute(List<Container> containers, double shipmentSize ) {
		this.containers = containers;
		this.shipmentSize = shipmentSize;
		List<Route> routings = findRoutes();
		Route solution;
		if( routings.size() == 0)
			solution = new Route();
		else
			solution = routings.get(0);
		return solution;
	}
	
	public List<Route> findRoutes() {
		List<Route> assignmentSolutions = new ArrayList<>();
		
		if( enoughCapacity() ) {
			assignmentSolutions = findShipmentContainerSolutions();
			Collections.sort( assignmentSolutions );
		}
		
		return assignmentSolutions;
	}
	
	//Returns true if the total capacity on that lane is equal to or greater
	//than the shipment size
	boolean enoughCapacity() {
		double containerCapacity = calculateContainersCapacity(containers);
		return containerCapacity >= shipmentSize;
	}

	private List<Route> findShipmentContainerSolutions() {
		List<Route> result = new ArrayList<>();
		Route solutionList = new Route();
		
		findShipmentContainerSolutionsHelper(result, solutionList, 0 );
		
		return result;
	}

	private void findShipmentContainerSolutionsHelper(
			List<Route> result, Route workingRoute, double assignedCapacity) {
		if( assignedCapacity >= shipmentSize ) {
			result.add( workingRoute.copyRoute() );
		}
		else {
			for( Container container : containers ) {
				//skip the containers we've already assigned to the potential solution.
				if( workingRoute.contains( container ) ) continue;
				
				double availableCapacity = container.currentCapacity();
				double volumeRemaining = shipmentSize - assignedCapacity;
				double capacityAssignedToContainer = volumeRemaining > availableCapacity ? availableCapacity : volumeRemaining;
				
				workingRoute.setAssignedCapacityForContainer(container, capacityAssignedToContainer);
				assignedCapacity += capacityAssignedToContainer;
				findShipmentContainerSolutionsHelper(result, workingRoute, assignedCapacity);
				assignedCapacity -= capacityAssignedToContainer;
				workingRoute.removeContainer(container);
			}
		}
	}
	
	double calculateContainersCapacity(List<Container> containers) {
		double containerCapacity = 0;
		
		for( Container container : containers ) {
			containerCapacity += container.currentCapacity();
		}
		return containerCapacity;
	}

	@Override
	public String getName() {
		return "RecursiveCostStrategy";
	}
}
