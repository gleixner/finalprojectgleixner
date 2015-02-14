package com.expeditors.training.course3demo.service;

import org.springframework.stereotype.Service;

import com.expeditors.training.course3demo.model.Product;

@Service
public class ProductService {
	public Product getProduct(int id) {
		return new Product("Latte", "Aromatic coffee with milk and cream.", 3.50); 
	}
}
