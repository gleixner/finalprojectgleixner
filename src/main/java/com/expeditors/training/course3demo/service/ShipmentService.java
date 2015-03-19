package com.expeditors.training.course3demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expeditors.training.course3demo.aggregates.DijkstraRoutingAggregate;
import com.expeditors.training.course3demo.aggregates.ShipmentRoutingAggregate;
import com.expeditors.training.course3demo.dto.ShipmentRoutesView;
import com.expeditors.training.course3demo.factories.AggregateFactory;
import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.model.Shipment;
import com.expeditors.training.course3demo.model.ShipmentContainerAssociation;
import com.expeditors.training.course3demo.model.UserAccount;
import com.expeditors.training.course3demo.routing.RouteStrategy;
import com.expeditors.training.course3demo.routing.Utilities;


@Service
@Transactional(readOnly=true)
public class ShipmentService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	AggregateFactory aggregateFactory;
	
	@Autowired
	SecurityService securityService;
	
	private Query getSecureQuery( String searchTerms) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		UserAccount userAccount = securityService.getByName(userName);
		String str1 = "SELECT s from Shipment s WHERE s.owner = :userName";
		if( searchTerms != null && !searchTerms.equals(""))
			str1 += " AND ";
		Query q = entityManager.createQuery(str1 + searchTerms.trim());
		q.setParameter("userName", userAccount);
		return q;
	}
	
	//check security
	public List<Shipment> list(int start, int max) {
		Query q = getSecureQuery("");
		q.setFirstResult(start);
		q.setMaxResults(max);
		return (List<Shipment>) q.getResultList();
	}
	
	//check security
	/**
	 * Check for a shipment with the given id in a security conscious way.  Only 
	 * shipments that have an owner matching the current authorized user are returned.
	 * If no container that matches the id and the user is found, null is returned.
	 * @param id
	 * @return
	 */
	public Shipment getById(Long id) {
		Query q = getSecureQuery( "SHIPMENT_id = :id" );
		q.setParameter("id", id);
		List<Shipment> resultList = q.getResultList();
		Shipment result = null;
		if( resultList.size() == 1 )
			result = resultList.get(0);
		return result;
	}
	
	public List<Shipment> getAll() {
		return new ArrayList<Shipment>();
	}
	
	/**
	 *Adds a shipment to the database
	 *Checks security
	 *Any routing the shipment has will be deleted
	 * @param s
	 * @return
	 */
	@Transactional
	public Long save( Shipment s ) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		UserAccount userAccount = securityService.getByName(userName);
		
		if( s.getId() != null ) {
			//an unsecured query.  Check user before overwriting
			Shipment stored = entityManager.find(Shipment.class, s.getId() );
			if( stored == null || !userName.equals( stored.getOwner().getUsername() )) {
				throw new IllegalArgumentException("Operation not allowed");
			}
			else if ( !stored.getEditable() ) {
				throw new IllegalArgumentException("Operation not allowed");
			}
			s.setOwner( userAccount );
			s.setShipmentContainerAssociations(new HashSet<ShipmentContainerAssociation>());
			entityManager.merge( s );
		}
		else {
			if( isDuplicate( s ) ) 
				throw new IllegalArgumentException("Duplicate Shipment");
			s.setOwner( userAccount );
			entityManager.persist( s );
		}
		return s.getId();
	}
	
	private boolean isDuplicate(Shipment s) {
		Query q = entityManager.createQuery("SELECT s from Shipment s WHERE s.name = :name");
		q.setParameter("name", s.getName());
		List<Shipment> results = q.getResultList();
		return results.size() > 0;
	}

	@Transactional
	public Shipment routeShipment(Long id ) {
//		save( shipment );
		Shipment shipment = getById(id);
		
		//If this shipment is not editable, return the shipment unmodified
		//This is a code smell since it can fail to route, but not tell the user.
		if( !shipment.getEditable() ) return shipment;
		deleteRouting( id );
		
		ShipmentRoutingAggregate agg = aggregateFactory.getShipmentRoutingAggregate(shipment);
		List<Container> route = agg.getBestRouting();
		
		double shipmentSize = shipment.getVolume();
		for( Container container : route ) {
			double availableCapacity = container.currentCapacity();
			double assignedVolume = shipmentSize > availableCapacity ? availableCapacity : shipmentSize;
			ShipmentContainerAssociation sca = new ShipmentContainerAssociation(
					shipment, container, assignedVolume, new Date() );
			shipmentSize -= assignedVolume;
			shipment.getShipmentContainerAssociations().add(sca);
			if( !container.getDestination().equals(shipment.getDestination() ))
				container.setDestination( shipment.getDestination() );
		}
		return shipment;		
	}
	
	@Transactional
	public Shipment routeShipmentDijkstra(Long id ) {
		Shipment shipment = getById(id);
		
		//If this shipment is not editable, return the shipment unmodified
		//This is a code smell since it can fail to route, but not tell the user.
		if( !shipment.getEditable() ) return shipment;
		deleteRouting( id );
		
		DijkstraRoutingAggregate agg = aggregateFactory.getDijkstraRoutingAggregate(shipment);
		List<List<Container>> route = agg.getBestRouting();
		
		//From here to the end of the outer for loop should be in it's own tiny aggregate
		RouteStrategy fillStrategy = Utilities.getFillStrategy();
		for( List<Container> containers : route ) {
			double shipmentSize = shipment.getVolume();
			//sort this list, lowest capacity first in order to fill the containers
			Collections.sort(containers, fillStrategy);
			for( Container container : containers ) {
				double availableCapacity = container.currentCapacity();
				double assignedVolume = shipmentSize > availableCapacity ? availableCapacity : shipmentSize;
				ShipmentContainerAssociation sca = new ShipmentContainerAssociation ( 
						shipment, container, assignedVolume, new Date() );
				shipmentSize -= assignedVolume;
				shipment.getShipmentContainerAssociations().add(sca);
			}
		}
		//End what should be an aggregate
		return shipment;		
	}

	//This method is secure because getById is secured
	@Transactional
	public void deleteRouting(Long id) {
		Shipment shipment = getById(id);
		shipment.getShipmentContainerAssociations().clear();
		entityManager.flush();
	}
	
	//secure because it uses getById to find the shipment entity which
	//will not return the id if it doesn't match the current username.
	@Transactional
	public void delete( Long id ) {
		Shipment s = getById(id);
		if( s == null || !s.getEditable() ) {
			throw new IllegalArgumentException("Operation not permitted");
		}
		entityManager.remove(s);
	}

	@Transactional
	public ShipmentRoutesView getShipmentRoutesView(Long id) {
		Shipment shipment = getById(id);
		ShipmentRoutesView dataView = new ShipmentRoutesView(shipment);
		return dataView;
	}
}
