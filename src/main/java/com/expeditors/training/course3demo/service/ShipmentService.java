package com.expeditors.training.course3demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expeditors.training.course3demo.model.Shipment;


@Service
public class ShipmentService {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Shipment> list(int start, int max) {
		Query q = entityManager.createQuery("SELECT s from Shipment s");
		q.setFirstResult(start);
		q.setMaxResults(max);
		return (List<Shipment>) q.getResultList();
	}
	
	public Shipment getById(Long id) {
		return entityManager.find(Shipment.class, id);
	}
	
	public List<Shipment> getAll() {
		return new ArrayList<Shipment>();
	}
	
	/**
	 *Adds a shipment to the database
	 * @param s
	 * @return
	 */
	@Transactional
	public Long save( Shipment s ) {
		if( s.getId() == null )
			entityManager.persist( s );
		else
			entityManager.merge( s );
		return s.getId();
	}
}
