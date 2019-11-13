package app.models;

import java.io.Serializable;

public class Place extends Location implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int id;
	
	public Place(int id, String name, double latitude, double longitude) {
		super(latitude, longitude);
		this.setId(id);
		//super.isPerson = false;
		this.name = name;
		Location.addPlace(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
