package com.expeditors.training.course3demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expeditors.training.course3demo.model.Product;

@Service
@Transactional(readOnly=true)
public class ProductService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Product getProduct(Long id) {
		Product result = entityManager.find(Product.class, id);
		return result;
	}
	
	@Transactional
	public Long save(Product product) {
		if(product.getId() == null )
			entityManager.persist(product);
		else
			entityManager.merge(product);
		return product.getId();
	}
	
	public List<Product> list(int start, int max) {
		Query q = entityManager.createQuery("SELECT p from Product p");
		q.setFirstResult(start);
		q.setMaxResults(max);
		return (List<Product>) q.getResultList();
	}
	
	@Transactional
	public void delete(long id) {
		Product p = getProduct(id);
		entityManager.remove(p);
	}
}
