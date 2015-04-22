package com.expeditors.training.course3demo.routing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;

public class TestRecursiveCostStrategy {
	
	@Test
	public void testRoutingContainers3A() {
		List<Container> containers = new ArrayList<>();

		containers.add(new Container("1", 10.0, "A", "B", Status.READY, 5.0));
		containers.add(new Container("2", 5.0, "A", "B", Status.READY, 1.0));
		containers.add(new Container("3", 5.0, "A", "B", Status.READY, 2.0));
		
		Strategy strat = new RecursiveCostStrategy();
		
		Route bestRoute = strat.getBestRoute( containers, 10 );
		
		assertEquals( 15.0, bestRoute.getCost(), 1e-15 );
		assertEquals( 2, bestRoute.getContainerSet().size() );
		assertTrue( bestRoute.contains( containers.get(1)));
		assertTrue( bestRoute.contains( containers.get(2)));	
	}
	
	@Test
	public void testRoutingContainers3B() {
		List<Container> containers = new ArrayList<>();

		containers.add(new Container("1", 10.0, "A", "B", Status.READY, 2.0));
		containers.add(new Container("2", 5.0, "A", "B", Status.READY, 1.0));
		containers.add(new Container("3", 5.0, "A", "B", Status.READY, 3.0));
		
		Strategy strat = new RecursiveCostStrategy();
		
		Route bestRoute = strat.getBestRoute( containers, 10 );
		
		assertEquals( bestRoute.getCost(), 15.0, 1e-15 );
		assertEquals( bestRoute.getContainerSet().size(), 2 );
		assertTrue( bestRoute.contains( containers.get(0)));
		assertTrue( bestRoute.contains( containers.get(1)));
		assertEquals( bestRoute.getAssignedCapacityForContainer(containers.get(0)), 5.0, 1e-15 );
		assertEquals( bestRoute.getAssignedCapacityForContainer(containers.get(1)), 5.0, 1e-15 );
	}
	
	@Test
	public void testRoutingContainersNoRoute() {
		List<Container> containers = new ArrayList<>();

		containers.add(new Container("1", 3.0, "A", "B", Status.READY, 2.0));
		containers.add(new Container("2", 3.0, "A", "B", Status.READY, 1.0));
		containers.add(new Container("3", 3.0, "A", "B", Status.READY, 3.0));
		
		Strategy strat = new RecursiveCostStrategy();
		
		Route bestRoute = strat.getBestRoute( containers, 10 );
		
		assertEquals( bestRoute.getCost(), Double.POSITIVE_INFINITY, 1e-15 );
		assertEquals( bestRoute.getContainerSet().size(), 0 );
	}
	
	//Make sure the port object is using the Strategy class correctly
	@Test
	public void testPortStrategyUseSuccess() {
		List<Container> containers = new ArrayList<>();

		containers.add(new Container("1", 10.0, "A", "B", Status.READY, 5.0));
		containers.add(new Container("2", 5.0, "A", "B", Status.READY, 1.0));
		containers.add(new Container("3", 5.0, "A", "B", Status.READY, 2.0));
		
		List<Port> ports = Utilities.buildPortGraph(containers, 10.0);
		
		Port originPort = Utilities.findPort(ports, "A");
		Port destinationPort = Utilities.findPort(ports, "B");
		
		double calculatedCost = originPort.traverseCost(destinationPort);
		
		assertEquals( 15, calculatedCost, 1e-15);
		Route resultRoute = originPort.getCachedRoute( destinationPort );
		assertTrue( resultRoute.contains( containers.get(1) ));
		assertTrue( resultRoute.contains( containers.get(2) ));
		assertEquals( resultRoute.getAssignedCapacityForContainer(containers.get(1)), 5.0, 1e-15 );
		assertEquals( resultRoute.getAssignedCapacityForContainer(containers.get(2)), 5.0, 1e-15 );
		
	}
	
	@Test 
	public void testSingleContainer() {
		List<Container> containers = new ArrayList<>();

		containers.add(new Container("1", 10.0, "A", "B", Status.READY, 5.0));
		
		Strategy strat = new RecursiveCostStrategy();
		
		Route bestRoute = strat.getBestRoute( containers, 10 );
		
		assertEquals( 50.0, bestRoute.getCost(), 1e-15 );
		assertEquals( 1, bestRoute.getContainerSet().size() );
		assertTrue( bestRoute.contains( containers.get(0)));
	}
}
