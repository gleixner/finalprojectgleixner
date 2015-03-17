package com.expeditors.training.course3demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import com.expeditors.training.course3demo.enums.Status;

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
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="OWNER")
	private UserAccount owner;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="shipment", cascade=CascadeType.ALL, orphanRemoval=true) //
	Set<ShipmentContainerAssociation> shipmentContainerAssociations;
	
//	@Transient
//	private boolean editable;
	
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

	public Set<ShipmentContainerAssociation> getShipmentContainerAssociations() {
//		if( shipmentContainerAssociations == null ) {
//			setShipmentContainerAssociations(new HashSet<ShipmentContainerAssociation>());
//		}
		return shipmentContainerAssociations;
	}

	public void setShipmentContainerAssociations(
			Set<ShipmentContainerAssociation> shipmentContainerAssociations) {
		this.shipmentContainerAssociations = shipmentContainerAssociations;
	}

	public UserAccount getOwner() {
		return owner;
	}

	public void setOwner(UserAccount owner) {
		this.owner = owner;
	}
	
	/**
	 * This value must be calculated every time the method is called in case
	 * a container status changed unexpectedly.
	 * The editable boolean exists for jsp's.
	 * @return
	 */
	public boolean getEditable() {
		boolean result = true;
		for(ShipmentContainerAssociation sca: shipmentContainerAssociations ) {
			if( sca.getContainer().getStatus() != Status.READY ) {
				result = false;
				break;
			}
		}
//		editable = result;
		return result;
	}
	
//	public boolean getEditable() {
//		return editable();
//	}
	
	public double getAssignedCapacity() {
		double sum = 0;
		for(ShipmentContainerAssociation sca : shipmentContainerAssociations) {
			sum += sca.getShipmentVolume();
		}
		return sum;
	}
}
