package com.expeditors.training.course3demo.routing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.mutable.MutableDouble;
import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Before;





import org.junit.Test;

import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;



public class EasyMockTest extends EasyMockSupport {
	
	public RecursiveCostStrategy strategy = createNiceMock( RecursiveCostStrategy.class );
	
//	@Before
//	public void setUp() {
//		Route returnRoute = new Route();
//		Container container = new Container("1", 10.0, "A", "B", Status.READY, 5.0);
//		List<Container> containers = new ArrayList<>();
//		containers.add( container );
//		returnRoute.setAssignedCapacityForContainer(container, 10.0);
//		EasyMock.expect( strategy.getBestRoute(containers, 10.0)).andReturn( returnRoute );
//		EasyMock.replay( strategy );
//	}
	
	@Test
	public void getScore() {
		Route returnRoute = new Route();
		Container container = new Container("1", 10.0, "A", "B", Status.READY, 5.0);
		List<Container> containers = new ArrayList<>();
		containers.add( container );
		returnRoute.setAssignedCapacityForContainer(container, 10.0);
		EasyMock.expect( strategy.getBestRoute(containers, 10.0)).andReturn( returnRoute );
		EasyMock.replay( strategy );
		
		
		//create container
//		Container container = new Container("1", 10.0, "A", "B", Status.READY, 5.0);
		//create port
		Port port1 = new Port("AAA", new MutableDouble(10.0));
		//create second port
		Port port2 = new Port("BBB", new MutableDouble(10.0));
		//assign port and container to second port
		port2.getContainersToPort().put(port1, containers);
		
		//invoketraverCost on second port with first port as argument.
		
//		double result = port2.traverseCost(port1);
		Route result = strategy.getBestRoute(containers, 10.0);
//		assertEquals( 50.0, result, 1e-15 );
		verifyAll();
	}

}
