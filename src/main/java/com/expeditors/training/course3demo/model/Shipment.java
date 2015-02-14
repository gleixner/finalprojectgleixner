package com.expeditors.training.course3demo.model;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Shipment {

	@Size(min=3, max=40)
	String name;
	
	@Pattern(regexp="[A-Z0-9]{3}")
	String origin;
	
	@Pattern(regexp="[A-Z0-9]{3}")
	String destination;
	
	@Min(0)
	@NotNull
	BigDecimal volume;
	
	public Shipment(){}
	
	public Shipment( String name, String origin, String destination, BigDecimal volume ) {
		this.name = name;
		this.origin = origin;
		this.destination = destination;
		this.volume = volume;
	}

	public Shipment( String name, String origin, String destination, double volume ) {
		this.name = name;
		this.origin = origin;
		this.destination = destination;
		this.volume = new BigDecimal(volume);
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

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	
	
	
}
