package com.expeditors.training.course3demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.expeditors.training.course3demo.enums.Status;

@Entity
public class Container {

	@Id
	@Column(name="CONTAINER_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	//	@NotEmpty
	@Size(min=3, max=40)
	@Column
	String name;
	
	@Min(0)
	@NotNull
	@Column
//	BigDecimal capacity;
	Double capacity;
	
	@Pattern(regexp="[A-Z0-9]{3}")
	@Column
	String location;
	
	@Pattern(regexp="([A-Z0-9]{3})?")
	@Column
	String destination;
	
	
	//http://tomee.apache.org/examples-trunk/jpa-enumerated/README.html
	@Enumerated(value=EnumType.STRING)
	Status status;
	
	public Container(){}
	
	public Container(String name, 
					Double capacity,
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
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Container(String name, 
			double capacity,
			String location, 
			String destination, 
			Status status ) 
{
	this.name = name;
	this.capacity = new Double( capacity );
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
	public Double getCapacity() {
		return capacity;
	}
	public void setCapacity(Double capacity) {
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
	
//	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
