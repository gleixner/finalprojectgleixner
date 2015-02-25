package com.expeditors.training.course3demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Shipment {

	@Id
	@Column(name="SHIPMENT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column
	@Size(min=3, max=40)
	String name;
	
	@Column
	@Pattern(regexp="[A-Z0-9]{3}")
	String origin;
	
	@Column
	@Pattern(regexp="[A-Z0-9]{3}")
	String destination;
	
	@Column
	@Min(0)
	@NotNull
	Double volume;
	
	public Shipment(){}
	
	public Shipment( String name, String origin, String destination, Double volume ) {
		this.name = name;
		this.origin = origin;
		this.destination = destination;
		this.volume = volume;
	}

	public Shipment( String name, String origin, String destination, double volume ) {
		this.name = name;
		this.origin = origin;
		this.destination = destination;
		this.volume = new Double(volume);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}
	
	
	
}
