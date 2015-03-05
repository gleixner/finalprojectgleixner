package com.expeditors.training.course3demo.dto;

public class FindContainerCriteria {

	private String name;
	
	private String location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	//Return true if there is no value, or the strings are empty for the name and location
	public boolean isEmpty() {
		return (name == null || name.trim().isEmpty()) && (location == null || location.trim().isEmpty());
	}
	
}
