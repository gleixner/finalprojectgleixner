package com.expeditors.training.course3demo.model;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.expeditors.training.course3demo.enums.Status;

public class Container {

//	@NotEmpty
	@Size(min=3, max=40)
	String name;
	
	@Min(0)
	@NotNull
	BigDecimal capacity;
	
	@Pattern(regexp="[A-Z0-9]{3}")
	String location;
	
	@Pattern(regexp="([A-Z0-9]{3})?")
	String destination;
	
	Status status;
	
	public Container(){}
	
	public Container(String name, 
					BigDecimal capacity,
					String location, 
					String destination, 
					Status status ) 
	{
			this.name = name;
			this.capacity = capacity;
			this.location = location;
			this.destination = destination;
			this.status = status;
	}
	
	public Container(String name, 
			double capacity,
			String location, 
			String destination, 
			Status status ) 
{
	this.name = name;
	this.capacity = new BigDecimal( capacity );
	this.location = location;
	this.destination = destination;
	this.status = status;
}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getCapacity() {
		return capacity;
	}
	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
