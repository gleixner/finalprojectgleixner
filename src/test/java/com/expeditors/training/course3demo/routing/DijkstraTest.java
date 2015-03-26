package com.expeditors.training.course3demo.routing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.mutable.MutableDouble;
import org.junit.Test;

import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.routing.Dijkstra;
import com.expeditors.training.course3demo.routing.Port;
import com.expeditors.training.course3demo.routing.Utilities;

public class DijkstraTest {
	
	@Test
	public void testBuildPortGraph() {
		List<Container> containers = new ArrayList<>();
		
		containers.add(new Container("1", 12.0, "A", "B", Status.READY, 2.0));
		containers.add(new Container("2", 12.0, "B", "C", Status.READY, 2.0));
		containers.add(new Container("3", 12.0, "C", "Z", Status.READY, 2.0));
		
		List<Port> ports = Utilities.buildPortGraph(containers);
		for( Port port : ports ) {
			System.out.println(port);
		}
	}
	
	@Test
	public void testPortTraverseCost() {
		List<Container> containers = new ArrayList<>();
		
		containers.add(new Container("1", 12.0, "A", "B", Status.READY, 2.0));
		containers.add(new Container("2", 12.0, "B", "C", Status.READY, 2.0));
		containers.add(new Container("3", 12.0, "C", "Z", Status.READY, 2.0));
		
		List<Port> ports = Utilities.buildPortGraph(containers, 1);
		
		Port originPort = find( ports, "A" );
		Port destPort = find( ports, "B" );
		double cost = originPort.traverseCost( destPort );
		
		System.out.println(cost);
		
		assertEquals( cost, 2.0, 1e-15);
	}
	

	@Test
	public void testDijkstraRoutingCaseA() {
		List<Container> containers = new ArrayList<>();
		
		containers.add(new Container("1", 12.0, "A", "B", Status.READY, 2.0));
		containers.add(new Container("2", 12.0, "B", "C", Status.READY, 2.0));
		containers.add(new Container("3", 12.0, "C", "Z", Status.READY, 2.0));
		containers.add(new Container("4", 12.0, "A", "D", Status.READY, 5.0));
		containers.add(new Container("5", 12.0, "D", "Z", Status.READY, 5.0));
		
		Dijkstra<Port> dij = new Dijkstra<Port>();
		
		List<Port> ports = Utilities.buildPortGraph( containers, 1 );
		
		List<Port> portRoute = dij.findPath(ports, find(ports, "A"), end(ports, "Z"));
		
		for( Port port : portRoute ) {
			System.out.println(port.getName());
		}
		
		assertEquals( portRoute.size(), 4 );
		assertEquals( find( ports, "A"), find(portRoute, "A"));
		assertEquals( find( ports, "B"), find(portRoute, "B"));
		assertEquals( find( ports, "C"), find(portRoute, "C"));
		assertEquals( find( ports, "Z"), find(portRoute, "Z"));
	}
	
	@Test
	public void testDijkstraRoutingCaseB() {
		List<Container> containers = new ArrayList<>();
		
		containers.add(new Container("1", 12.0, "A", "B", Status.READY, 5.0));
		containers.add(new Container("2", 12.0, "B", "C", Status.READY, 5.0));
		containers.add(new Container("3", 12.0, "C", "Z", Status.READY, 5.0));
		containers.add(new Container("4", 12.0, "A", "D", Status.READY, 2.0));
		containers.add(new Container("5", 12.0, "D", "Z", Status.READY, 2.0));
		
		Dijkstra<Port> dij = new Dijkstra<Port>();
		
		List<Port> ports = Utilities.buildPortGraph( containers, 1 );
		
		List<Port> portRoute = dij.findPath(ports, find(ports, "A"), end(ports, "Z"));
		
		for( Port port : portRoute ) {
			System.out.println(port.getName());
		}
		
		assertEquals( portRoute.size(), 3 );
		assertEquals( find( ports, "A"), find(portRoute, "A"));
		assertEquals( find( ports, "D"), find(portRoute, "D"));
		assertEquals( find( ports, "Z"), find(portRoute, "Z"));
	}
	
	@Test
	public void testRoutingFiniteVolumeA() {
		List<Container> containers = new ArrayList<>();
		
		containers.add(new Container("1", 12.0, "A", "B", Status.READY, 5.0));
		containers.add(new Container("2", 12.0, "B", "C", Status.READY, 5.0));
		containers.add(new Container("3", 12.0, "C", "Z", Status.READY, 5.0));
		containers.add(new Container("4", 12.0, "A", "D", Status.READY, 2.0));
		containers.add(new Container("5", 2.0, "D", "Z", Status.READY, 2.0));
		
		Dijkstra<Port> dij = new Dijkstra<Port>();
		
		List<Port> ports = Utilities.buildPortGraph( containers, 10.0 );
		
		List<Port> portRoute = dij.findPath(ports, find(ports, "A"), end(ports, "Z"));
		
		for( Port port : portRoute ) {
			System.out.println(port.getName());
		}
		
		assertEquals( portRoute.size(), 4 );
		assertEquals( find( ports, "A"), find(portRoute, "A"));
		assertEquals( find( ports, "B"), find(portRoute, "B"));
		assertEquals( find( ports, "C"), find(portRoute, "C"));
		assertEquals( find( ports, "Z"), find(portRoute, "Z"));
	}
	
	@Test
	public void testRoutingFiniteVolumeB() {
		List<Container> containers = new ArrayList<>();
		
		containers.add(new Container("1", 12.0, "A", "B", Status.READY, 5.0));
		containers.add(new Container("2", 12.0, "B", "C", Status.READY, 5.0));
		containers.add(new Container("3", 12.0, "C", "Z", Status.READY, 5.0));
		containers.add(new Container("4", 12.0, "A", "D", Status.READY, 2.0));
		containers.add(new Container("5", 12.0, "D", "Z", Status.READY, 2.0));
		
		Dijkstra<Port> dij = new Dijkstra<Port>();
		
		List<Port> ports = Utilities.buildPortGraph( containers, 10.0 );
		
		List<Port> portRoute = dij.findPath(ports, find(ports, "A"), end(ports, "Z"));
		
		for( Port port : portRoute ) {
			System.out.println(port.getName());
		}
		
		assertEquals( portRoute.size(), 3 );
		assertEquals( find( ports, "A"), find(portRoute, "A"));
		assertEquals( find( ports, "D"), find(portRoute, "D"));
		assertEquals( find( ports, "Z"), find(portRoute, "Z"));
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
