package com.expeditors.training.course3demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expeditors.training.course3demo.dao.ContainerRepository;
import com.expeditors.training.course3demo.dto.FindContainerCriteria;
import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.model.Product;


@Service
@Transactional(readOnly=true)
public class ContainerService {
	
	@PersistenceContext
	private EntityManager eM;
	
	@Autowired
	ContainerRepository containerRepository;
	
	public List<Container> list(int start, int max) {
		Query q = eM.createQuery("SELECT c from Container c");
		q.setFirstResult(start);
		q.setMaxResults(max);
		return (List<Container>) q.getResultList();
	}

	public Container getById(Long id) {
//		Container result = eM.find(Container.class, id);
		Container result = containerRepository.findOne(id);
		return result;
	}
	
	@Transactional
	public Long save(Container c ) {
		if( c.getId() == null )
			eM.persist( c );
		else
			eM.merge( c );
		return c.getId();
	}
	
	@Transactional
	public void delete(long id) {
		Container c = getById(id);
		eM.remove(c);
	}

//	private static final String q1 = "SELECT c FROM Container c WHERE LOWER(c.name) LIKE :name";
//	private static final String q2 = "SELECT c FROM Container c WHERE (c.location = :loc AND status = 'READY') OR"
//									+ "(c.destination = :loc AND status = 'ARRIVED')";
//	private static final String q3 = q1 + " AND ((c.location = :loc AND status = 'READY') OR"
//									+ "(c.destination = :loc AND status = 'ARRIVED'))";
	
	public List<Container> findContainers(FindContainerCriteria finder) {
		Query q;
		List<Container> result;
		boolean name = finder.getName() != null && !finder.getName().trim().isEmpty();
		boolean location = finder.getLocation() != null && !finder.getLocation().trim().isEmpty();

		if( name && location )
			result = containerRepository.findByNameAndCurrentLocation(finder.getName().toUpperCase(), finder.getLocation().toUpperCase());
		else if( name )
			result = containerRepository.findByNameContainingIgnoreCase(finder.getName() );
		else
			result = containerRepository.findByCurrentLocation(finder.getLocation().toUpperCase());
		return result;
		
//		if( name && location ) {
//			q = eM.createQuery(q3);
//			q.setParameter("name", "%"+finder.getName().toLowerCase()+"%" );
//			q.setParameter("loc", finder.getLocation() );
//		}
//		else if( name ) {
//			q = eM.createQuery(q1);
//			q.setParameter("name", "%"+finder.getName().toLowerCase()+"%" );
//		}
//		else {
//			q = eM.createQuery(q2);
//			q.setParameter("loc", finder.getLocation() );
//		}
//		return q.getResultList();
	}
}
