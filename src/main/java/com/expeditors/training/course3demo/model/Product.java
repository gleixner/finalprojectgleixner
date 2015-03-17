package com.expeditors.training.course3demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;


@Entity
@Table(name="PRODUCT")
public class Product {
	
	@Id
	@Column(name="PRODUCT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Pattern(regexp="[a-zA-Z]+")
	@Column
	String name;

	@Column
	String description;
	
	@Column
	double price;
	
//	@ManyToMany(fetch=FetchType.LAZY, mappedBy="products")
//	Set<Card> cards;
	
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="product", cascade=CascadeType.ALL)
//	Set<CardProductBuys> boughtProducts;
	
	public Product(){}
	
	public Product(String name, String description, double price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Set<CardProductBuys> getBoughtProducts() {
//		return boughtProducts;
//	}
//
//	public void setBoughtProducts(Set<CardProductBuys> boughtProducts) {
//		this.boughtProducts = boughtProducts;
//	}
}
