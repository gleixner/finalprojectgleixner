package com.expeditors.training.course3demo.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="USERACCOUNT")
public class UserAccount {

//	@Id
//	@Column(name="USER_ID")
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private Long id;
	
	@Id
	@Column(name="username", unique=true, nullable=false, length=30)
	@Pattern(regexp="[\\w]{3,30}")
	private String username;
	
	@Column
	@Size(min=3, max=50)
	private String password;
	
	@Column
	private boolean enabled;
	
	//Order of permissions is irrelevant
	@OneToMany(fetch=FetchType.EAGER, mappedBy="userAccount", cascade=CascadeType.ALL)
	private List<UserRole> userRoles;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
}
