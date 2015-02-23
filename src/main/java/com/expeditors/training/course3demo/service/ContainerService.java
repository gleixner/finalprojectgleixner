package com.expeditors.training.course3demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.model.Product;


@Service
public class ContainerService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Container> getAll() {
		List<Container> result = new ArrayList<Container>();
		
		result.add( new Container( "Bob", 1.0, "jfk", "ams", Status.ARRIVED) );
		result.add( new Container( "Sue", 2.0, "jfk", "ams", Status.ARRIVED) );
		result.add( new Container( "Jim", 3.0, "jfk", "ams", Status.ARRIVED) );

		return result;
	}

	public Container getById(Long id) {
		Container result = entityManager.find(Container.class, id);
		return result;
	}
	
}
