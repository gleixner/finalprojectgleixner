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
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


@Entity
@Table(name="SHIPMENT_CONTAINER_ASSOCIATION")
public class ShipmentContainerAssociation {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SHIPMENT_ID", nullable=false)
	@XmlInverseReference(mappedBy="shipmentContainerAssociations")
	private Shipment shipment;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CONTAINER_ID", nullable=false)
	@XmlInverseReference(mappedBy="shipmentContainerAssociations")
	private Container container;
	
	@Column(name="SHIPMENT_VOLUME")
	private Double shipmentVolume;
	
	@Column(name="DATE")
	private Date date;
	
	public ShipmentContainerAssociation() {}
	
	public ShipmentContainerAssociation( Shipment s, Container c, Double v, Date date) {
		shipment = s;
		container = c;
		shipmentVolume = v;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getShipmentVolume() {
		return shipmentVolume;
	}

	public void setShipmentVolume(Double shipmentVolume) {
		this.shipmentVolume = shipmentVolume;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}
}
