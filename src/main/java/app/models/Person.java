package app.models;

import java.io.Serializable;

public class Person extends Location implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int id;
	private Place nearestPlace;
	private Property toNearestDirections; 
	
	public Person(int id, String name, double latitude, double longitude) {
		super(latitude, longitude);
		//super.isPerson = true;
		this.name = name;
		this.setId(id);
		this.setNearestPlace();
		this.setToNearestDirections();
		Location.addPerson(this);
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
	
	public Place getNearestPlace() {
		return nearestPlace;
	}
	
	public void setNearestPlace() {
		double min = 99999999;
		Place nP = null;
		for (Place place : Location.getPlaces()) {
			double distance = Math.sqrt(
					Math.pow(place.getLatitude()-this.getLatitude(), 2)
					+Math.pow(place.getLongitude()-this.getLongitude(), 2));
			if(distance<min) {
				min = distance;
				nP = place;
			}
		}
		
		System.out.println("Person: " + this.getName() + "("+this.getLatitude() + "," + this.getLongitude()+ ") nearest: " + nP.getName());
		this.nearestPlace = nP;
	}

	public Property getToNearestDirections() {
		return toNearestDirections;
	}

	public void setToNearestDirections() {
		Property p = new Property(
				new Location(this.getLatitude(), this.getLongitude()),
				new Location(this.nearestPlace.getLatitude(), this.nearestPlace.getLongitude()), "transit");
		this.toNearestDirections = p;
		System.out.println("Person: " + this.getName() + "nearestProperty: " + p.getPolyline());
	}
	
}
