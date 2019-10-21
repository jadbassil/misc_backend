package models;

import java.io.Serializable;

public class Person extends Location implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	public Person(String name, double longitude, double latitude) {
		super(longitude, latitude);
		super.isPerson = true;
		this.name = name;
		Location.addPerson(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
}
