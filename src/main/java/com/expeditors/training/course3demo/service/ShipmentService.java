package com.expeditors.training.course3demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.expeditors.training.course3demo.model.Shipment;


@Service
public class ShipmentService {

	Map<String, Shipment> db = new HashMap<>();
	
	{
		Shipment s1 = new Shipment("steve", "JFK", "SAL", 5 );
		Shipment s2 = new Shipment("sal", "JFK", "SAL", 5 );
		Shipment s3 = new Shipment("jim", "JFK", "SAL", 5 );
		db.put( s1.getName(), s1 );
		db.put( s2.getName(), s2 );
		db.put( s3.getName(), s3 );
	}
	
	public List<Shipment> getAllShipments() {
		return new ArrayList<>( db.values() );
	}
	
	/**
	 * Attempts to add the given shipment to the database.
	 * If the database already contains a shipment with the
	 * given name, returns false and does not add the shipment
	 * to the database.  Otherwise, adds shipment to the 
	 * database and returns true.
	 * @param s
	 * @return
	 */
	public boolean addShipment( Shipment s ) {
		boolean result = db.containsKey( s.getName() );
		
		if( !result ) {
			db.put( s.getName(),  s );
		}
		return !result;
	}
	
}
