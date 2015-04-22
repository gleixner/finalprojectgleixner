package com.expeditors.training.course3demo.routing;

//Copyright (C) 2002-2010 StackFrame, LLC http://www.stackframe.com/
//This software is provided under the GNU General Public License, version 2.

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.mutable.MutableDouble;

import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.model.Shipment;
import com.google.common.collect.Ordering;


/**
 * Utilities that belong nowhere else.
 *
 * @author Gene McCulley
 */
public class Utilities {

	// Inhibit construction as this class only provides static functions.
	private Utilities() {}

	/**
	 * An {@link com.google.common.collect.Ordering} which orders by value of {@link java.util.Map} entries.
	 *
	 * @param <K> the key type
	 * @param <V> the value type (must implement {@link java.lang.Comparable})
	 * @return an {@link com.google.common.collect.Ordering} which uses the natural ordering of entry values
	 */
	static <K, V extends Comparable<V>> Ordering<Map.Entry<K, V>> orderByEntryValue() {
		return new Ordering<Map.Entry<K, V>>() {

			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}

		};
	}

	//sort by lowest current capacity
	public static RouteStrategy getFillStrategy() {
		return new RouteStrategy() {
			@Override
			public int compare(Container o1, Container o2) {
				double result = o1.currentCapacity() - o2.currentCapacity();
				return new Double(result).intValue();
			}
			@Override
			public String getName() {
				return "FillStrategy";
			}
		};
	}

	//sort by lowest rate
	public static RouteStrategy getCostStrategy() {
		return new RouteStrategy() {
			@Override
			public int compare(Container o1, Container o2) {
				double result = o1.getRate() - o2.getRate();
				return new Double(result).intValue();
			}
			@Override
			public String getName() {
				return "CostStrategy";
			}
		};
	}
	
	public static List<Port> buildPortGraph(List<Container> containers) {
		return buildPortGraph( containers, Double.POSITIVE_INFINITY);
	}
	
	//Build the port graph, with the cost saving strategy
	public static List<Port> buildPortGraph(List<Container> containers, double shipmentVolume ) {
		Map<String, Port> ports = new HashMap<>();
		MutableDouble volume = new MutableDouble(shipmentVolume);
		
		for( Container container : containers ) {
			String origin = container.getLocation();
			String destination = container.getDestination();
			Port originPort = ports.get(origin);
			Port destPort = ports.get(destination);
			if( originPort == null ) {
				originPort = new Port( origin, volume );
				ports.put( origin, originPort );
			}
			if( destPort == null ) {
				destPort = new Port( destination, volume );
				ports.put(destination, destPort);
			}
			
			originPort.addContainerToPort( destPort, container);
		}
		List<Port> result = new ArrayList<>(ports.values());
		return result;
	}
	
	public static List<Port> buildPortTimeGraph(List<Container> containers, double shipmentVolume) {
		Map<String,Map<Integer,Port>> ports = new HashMap<>();
		MutableDouble volume = new MutableDouble(shipmentVolume);
		
		for( Container container : containers ) {
			String origin = container.getLocation();
			String destination = container.getDestination();
			Integer departureDate = container.getDepartureDate();
			Integer arrivalDate = container.getArrivalDate();
			
			Map<Integer,Port> originPorts = ports.get( origin );
			if( originPorts == null ) {
				originPorts = new HashMap<Integer,Port>();
				ports.put( origin, originPorts );
			}
			
			Map<Integer,Port> destinationPorts = ports.get( destination );
			if( destinationPorts == null ) {
				destinationPorts = new HashMap<Integer,Port>();
				ports.put( destination, destinationPorts );
			}
			
			Port departurePort = originPorts.get( departureDate );
			if( departurePort == null ) {
				departurePort = new Port( origin, departureDate, volume );
				originPorts.put( departureDate, departurePort );
			}
			
			Port arrivalPort = destinationPorts.get( arrivalDate );
			if( arrivalPort == null ) {
				arrivalPort = new Port( destination, arrivalDate, volume );
				destinationPorts.put( arrivalDate, arrivalPort );
			}
			departurePort.addContainerToPort(arrivalPort, container);
		}
		
		List<Port> result = new ArrayList<>();
		for(String portName : ports.keySet() ) {
			result.addAll( ports.get( portName ).values() );
		}
		return result;
	}
	
	//Return null if the port can't be found
	public static Port findPort(List<Port> ports, String target) {
		int i = 0;
		while(i < ports.size() && !ports.get(i).getName().equals(target) )
			i += 1;
		Port result;
		if( i >= ports.size() )
			result = null;
		else
			result = ports.get(i);
		return result;
	}
	
	public static Port findPort( List<Port> ports, Shipment shipment ) {
		int i = 0;
		while( i <ports.size() 
				&& !ports.get(i).getName().equals( shipment.getName() ) 
				&& !ports.get(i).getDate().equals( shipment.getDepartureDate())) {
			i += 1;
		}
		Port result;
		if( i >= ports.size() )
			result = null;
		else
			result = ports.get(i);
		return result;
	}
}
