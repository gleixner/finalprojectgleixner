package com.expeditors.training.course3demo.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CARD_PRODUCT_BUYS")
public class CardProductBuys {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CARD_ID", nullable=false)
	private Card card;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Product_ID", nullable=false)
	private Product product;
	
	@Column(name="DATE")
	private Date date;
	
	public CardProductBuys(){}
	
	public CardProductBuys(Card c, Product p , Date date) {
		card = c;
		product = p;
		this.date = date;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
