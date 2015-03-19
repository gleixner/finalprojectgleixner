package com.expeditors.training.course3demo.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.model.Shipment;
import com.expeditors.training.course3demo.model.ShipmentContainerAssociation;

public class ShipmentRoutesView {

	List<Leg> assignedRoutes = new ArrayList<>();;
	
	public ShipmentRoutesView(Shipment shipment) {
		Map<String,Leg> legs = new HashMap<>();
		
		for(ShipmentContainerAssociation sca: shipment.getShipmentContainerAssociations() ) {
//			Container container = sca.getContainer();
			String origin = sca.getContainer().getLocation();
			String destination = sca.getContainer().getDestination();
			Leg leg = legs.get(origin);
			if( leg == null ) {
				leg = new Leg( origin, destination );
				legs.put(origin, leg);
			}
			leg.addSca(sca);
		}
		
		//Now order the legs first to last in the assignedRoutes list
		String origin = shipment.getOrigin();
		Leg leg = legs.get( origin );
		
		while( leg != null ) {
			assignedRoutes.add( leg );
			leg = legs.get( leg.getDestination() );
		}
	}
	
	public List<Leg> getAssignedRoutes() {
		return assignedRoutes;
	}
	
	
	public static class Leg {
		String origin;
		String destination;
		
		List<ShipmentContainerAssociation> assignedScas = new ArrayList<>();
		
		public Leg( String origin, String destination) {
			this.origin= origin;
			if(destination == null)
				this.destination = "FINAL";
			else
				this.destination = destination;
		}

		public String getOrigin() {
			return origin;
		}

		public String getDestination() {
			return destination;
		}

		public List<ShipmentContainerAssociation> getAssignedScas() {
			return assignedScas;
		}
		
		public void addSca( ShipmentContainerAssociation sca ) {
			assignedScas.add( sca );
		}
	}
}
