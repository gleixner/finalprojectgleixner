package com.expeditors.training.course3demo.model;
 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
 
@Entity
@Table(name="CARD")
public class Card {
    @Id
    @Column (name="CARD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @NotEmpty
    @Size (min=16, max=16)
    @Column (name="CARD_NUMBER", columnDefinition = "char")
    private String number;
 
    @NotEmpty
    @Column (name="OWNER_NAME")
    private String owner;
 
    @Column
    private double amount;
    
    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="address")
    private Address address;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy="card", cascade=CascadeType.ALL)
    @OrderColumn(name="index")
    List<PhoneNumber> phoneNumbers;
 
    public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getNumber() {
        return number;
    }
 
    public void setNumber(String number) {
        this.number = number;
    }
 
    public String getOwner() {
        return owner;
    }
 
    public void setOwner(String owner) {
        this.owner = owner;
    }
 
    public double getAmount() {
        return amount;
    }
 
    public void setAmount(double amount) {
        this.amount = amount;
    }

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
}