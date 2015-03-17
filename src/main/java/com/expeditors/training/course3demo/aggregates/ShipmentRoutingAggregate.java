package com.expeditors.training.course3demo.aggregates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.expeditors.training.course3demo.dao.ContainerRepository;
import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.model.Shipment;
import com.expeditors.training.course3demo.model.ShipmentContainerAssociation;
import com.expeditors.training.course3demo.service.ShipmentService;

public class ShipmentRoutingAggregate {

	private ShipmentService shipmentService;
	private ContainerRepository containerRepository;
	
	Shipment shipment;
	List<Container> containers;
	
	public ShipmentRoutingAggregate( ShipmentService shipmentService, ContainerRepository containerRepository,
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
	
	public List<Container> getBestRouting() {
		List<List<Container>> routings = findContainerAssignments();
		List<Container> solution;
		if( routings.size() == 0)
			solution = new ArrayList<Container>();
		else
			solution = routings.get(0);
		return solution;
	}
	
	/**
	 * returns a list containing a list of possible container routings.  This list is sorted
	 * such that the "best" solution is the first element in the list.  This method will return
	 * any empty list if there are no solutions.  It will never return a null.
	 * 
	 * The best solution is determined by the fewest containers used and if that solution requires
	 * a container that hasn't been routed yet.
	 * @return
	 */
	public List<List<Container>> findContainerAssignments() {
		List<List<Container>> assignmentSolutions = new ArrayList<>();
		
		if( enoughCapacity() ) {
			assignmentSolutions = findShipmentContainerSolutions();
			Collections.sort( assignmentSolutions, new OrderShipmentSolutions() );
		}
		
		return assignmentSolutions;
	}
	
	//Returns true if the total capacity on that lane is equal to or greater
	//than the total 
	boolean enoughCapacity() {
		double containerCapacity = calculateContainersCapacity(containers);
		return containerCapacity >= shipment.getVolume();
	}

	private List<List<Container>> findShipmentContainerSolutions() {
		List<List<Container>> result = new ArrayList<>();
		List<Container> solutionList = new ArrayList<>();
		
		findShipmentContainerSolutionsHelper(result, solutionList, shipment.getAssignedCapacity() );
		
		return result;
	}

	private void findShipmentContainerSolutionsHelper(
			List<List<Container>> result, List<Container> workingList, double assignedCapacity) {
		if( assignedCapacity >= shipment.getVolume() ) {
			result.add(new ArrayList<Container>(workingList) );
		}
		else {
			for( Container container : containers ) {
				//skip the containers we've already assigned to the potential solution.
				if( workingList.contains( container ) ) continue;
				
				workingList.add(container);
				assignedCapacity += container.currentCapacity();
				findShipmentContainerSolutionsHelper(result, workingList, assignedCapacity);
				assignedCapacity -= container.currentCapacity();
				workingList.remove(container);
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

	/**
	 * A comparator to sort the possible solutions such that the best is first.
	 * The best solution contains no unrouted containers and is the fewest number
	 * of containers.
	 * @author chq-jamesgl
	 *
	 */
	private class OrderShipmentSolutions implements Comparator<List<Container>> {

		@Override
		public int compare(List<Container> o1, List<Container> o2) {
			int result = scoreSolution(o1) - scoreSolution(o2);
			return result;
		}

		//A low score indicates a more favorable solution because it uses fewer containers
		//and does not use containers that haven't been booked yet.
		private int scoreSolution(List<Container> o1) {
			int score = 0;
			//Add 1 point to a solution for every container it contains
			score += o1.size();
			//If there are any containers in the solution that require you to set a destination 
			//for the container, add 100 to the score
			for(Container container : o1 ) {
				if( container.getDestination() == null || container.getDestination().isEmpty() ) {
					score += 100;
					break;
				}
			}
			return score;
		}
		
	}
}
