package com.expeditors.training.course3demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.expeditors.training.course3demo.enums.Status;

@Entity
@XmlRootElement(name="container")
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
	
	@Pattern(regexp="[A-Z]{3}")
	@Column
	String location;
	
	@Pattern(regexp="([A-Z]{3})?")
	@Column
	String destination;
	
	@Transient
	Double rate;
	
	
	//http://tomee.apache.org/examples-trunk/jpa-enumerated/README.html
	@Enumerated(value=EnumType.STRING)
	Status status;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="container", cascade=CascadeType.ALL)
	Set<ShipmentContainerAssociation> shipmentContainerAssociations;
	
	
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
	
	public Container(String name, 
			Double capacity,
			String location, 
			String destination, 
			Status status,
			Double rate) 
	{
		this.name = name;
		this.capacity = capacity;
		this.location = location;
		this.destination = destination;
		this.status = status;
		this.rate = rate;
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

	public Set<ShipmentContainerAssociation> getShipmentContainerAssociations() {
		return shipmentContainerAssociations;
	}

	public void setShipmentContainerAssociations(
			Set<ShipmentContainerAssociation> shipmentContainerAssociations) {
		this.shipmentContainerAssociations = shipmentContainerAssociations;
	}
	
	public double currentCapacity() {
		double sum = 0;
		if(shipmentContainerAssociations != null ) {
			for( ShipmentContainerAssociation sca : shipmentContainerAssociations ) {
				sum += sca.getShipmentVolume();
			}
		}
		return capacity - sum;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double cost) {
		this.rate = cost;
	}
}
