package models;

import java.io.Serializable;

public class Place extends Location implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	
	public Place(String name, double longitude, double latitude) {
		super(longitude, latitude);
		super.isPerson = false;
		this.name = name;
		Location.addPlace(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
