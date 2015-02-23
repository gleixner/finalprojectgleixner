package com.expeditors.training.course3demo.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.expeditors.training.course3demo.model.Product;

@Service
public class ProductService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Product getProduct(Long id) {
		Product result = entityManager.find(Product.class, id);
		return result;
	}
}
