package com.expeditors.training.course3demo.routing.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;

public class ContainerCases {
	
	public static List<Port> buildPortGraph(List<Container> containers ) {
		Map<String, Port> ports = new HashMap<>();
		
		for( Container container : containers ) {
			String location = container.getLocation();
			String destination = container.getDestination();
			
			Port originPort = ports.get(location);
			Port destPort = ports.get(destination);
			if( originPort == null ) {
				originPort = new Port(location);
				ports.put(location, originPort);
			}
			if( destPort == null ) {
				destPort = new Port(destination);
				ports.put(destination, destPort);
			}
			
			//Only set the route on the origin since routes
			//don't have to be 2 way.
			originPort.getRoutes().put(destPort, container.getRate() );
		}
		List<Port> result = new ArrayList<>(ports.values() );
		return result;
	}
	
	public static List<Container> caseA() {
		List<Container> result = new ArrayList<>();
		
		result.add(new Container("1", 12.0, "A", "B", Status.READY, 2.0));
		result.add(new Container("2", 12.0, "B", "C", Status.READY, 2.0));
		result.add(new Container("3", 12.0, "C", "Z", Status.READY, 2.0));
		result.add(new Container("4", 12.0, "A", "D", Status.READY, 5.0));
		result.add(new Container("5", 12.0, "D", "Z", Status.READY, 5.0));

		return result;
	}
	
	public static List<Container> caseB() {
		List<Container> result = new ArrayList<>();
		
		result.add(new Container("1", 12.0, "A", "B", Status.READY, 5.0));
		result.add(new Container("2", 12.0, "B", "C", Status.READY, 5.0));
		result.add(new Container("3", 12.0, "C", "Z", Status.READY, 5.0));
		result.add(new Container("4", 12.0, "A", "D", Status.READY, 2.0));
		result.add(new Container("5", 12.0, "D", "Z", Status.READY, 2.0));

		return result;
	}

}
