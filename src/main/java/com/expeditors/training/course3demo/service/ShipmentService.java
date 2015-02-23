package com.expeditors.training.course3demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.expeditors.training.course3demo.model.Shipment;


@Service
public class ShipmentService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Shipment getById(Long id) {
		return entityManager.find(Shipment.class, id);
	}
	
	public List<Shipment> getAll() {
		return new ArrayList<Shipment>();
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
//		boolean result = db.containsKey( s.getName() );
//		
//		if( !result ) {
//			db.put( s.getName(),  s );
//		}
		boolean result = true;
		return !result;
	}
	
}
