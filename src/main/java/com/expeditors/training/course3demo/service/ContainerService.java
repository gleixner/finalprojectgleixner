package com.expeditors.training.course3demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;
import com.expeditors.training.course3demo.model.Product;


@Service
@Transactional(readOnly=true)
public class ContainerService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Container> list(int start, int max) {
		Query q = entityManager.createQuery("SELECT c from Container c");
		q.setFirstResult(start);
		q.setMaxResults(max);
		return (List<Container>) q.getResultList();
	}

	public Container getById(Long id) {
		Container result = entityManager.find(Container.class, id);
		return result;
	}
	
	@Transactional
	public Long save(Container c ) {
		if( c.getId() == null )
			entityManager.persist( c );
		else
			entityManager.merge( c );
		return c.getId();
	}
	
	@Transactional
	public void delete(long id) {
		Container c = getById(id);
		entityManager.remove(c);
	}
}
