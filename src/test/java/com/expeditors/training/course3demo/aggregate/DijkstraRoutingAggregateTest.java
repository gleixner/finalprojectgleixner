package com.expeditors.training.course3demo.aggregate;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.expeditors.training.course3demo.aggregates.DijkstraRoutingAggregate;
import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.model.Shipment;
import com.expeditors.training.course3demo.routing.Port;
import com.expeditors.training.course3demo.routing.Route;
import com.expeditors.training.course3demo.routing.Utilities;

public class DijkstraRoutingAggregateTest {

	
	@Test
	public void testSingleContainerOnEachRoute() {
		List<Container> containers = new ArrayList<>();
		
		containers.add(new Container("1", 12.0, "A", "B", Status.READY, 2.0));
		containers.add(new Container("2", 12.0, "B", "C", Status.READY, 2.0));
		containers.add(new Container("3", 12.0, "C", "Z", Status.READY, 2.0));
		containers.add(new Container("4", 12.0, "A", "D", Status.READY, 5.0));
		containers.add(new Container("5", 12.0, "D", "Z", Status.READY, 5.0));
		
		Shipment shipment = new Shipment("First", "A", "Z", 10 );
		DijkstraRoutingAggregate dij = new DijkstraRoutingAggregate(shipment, containers);
		
		List<Route> result = dij.getBestPath();
		
		
		assertEquals( 3, result.size() );
		Set<Container> containers1 = result.get(0).getContainers();
		assertEquals( 1, containers1.size());
		assertTrue( containers1.contains(containers.get(0)));
		
		Set<Container> containers2 = result.get(1).getContainers();
		assertEquals( 1, containers2.size());
		assertTrue( containers2.contains(containers.get(1)));
		
		Set<Container> containers3 = result.get(2).getContainers();
		assertEquals( 1, containers3.size());
		assertTrue( containers3.contains(containers.get(2)));
	}
	
	@Test
	public void testMultipleContainersOnRoute() {
		List<Container> containers = new ArrayList<>();
		
		containers.add(new Container("NotUsed", 12.0, "A", "B", Status.READY, 5.0));
		containers.add(new Container("solution1", 5.0, "A", "B", Status.READY, 1.0));
		containers.add(new Container("solution2", 5.0, "A", "B", Status.READY, 2.0));
		
		containers.add(new Container("2", 12.0, "B", "C", Status.READY, 2.0));
		containers.add(new Container("3", 12.0, "C", "Z", Status.READY, 2.0));
		containers.add(new Container("4", 12.0, "A", "D", Status.READY, 5.0));
		containers.add(new Container("5", 12.0, "D", "Z", Status.READY, 5.0));
		
		Shipment shipment = new Shipment("First", "A", "Z", 10 );
		DijkstraRoutingAggregate dij = new DijkstraRoutingAggregate(shipment, containers);
		
		List<Route> result = dij.getBestPath();
		
		assertEquals( 3, result.size() );
		
		Set<Container> containers1 = result.get(0).getContainers();
		System.out.println( containers1 );
		assertEquals( 2, containers1.size());
		assertTrue( containers1.contains(containers.get(1)));
		assertTrue( containers1.contains(containers.get(2)));
		
		Set<Container> containers2 = result.get(1).getContainers();
		assertEquals( 1, containers2.size());
		assertTrue( containers2.contains(containers.get(3)));
		
		Set<Container> containers3 = result.get(2).getContainers();
		assertEquals( 1, containers3.size());
		assertTrue( containers3.contains(containers.get(4)));
	}
	
	
	private static Collection<Port> end(List<Port> ports, String end) {
		Collection<Port> result = new ArrayList<>();
		result.add( find(ports, end));
		return result;
	}

	private static Port find(List<Port> ports, String target) {
		int i = 0;
		while(!ports.get(i).getName().equals(target) )
			i += 1;
		return ports.get(i);
	}
}
