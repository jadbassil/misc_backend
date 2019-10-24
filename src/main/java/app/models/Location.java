package app.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Location implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double longitude;
	private double latitude;
	private static ArrayList<Place> places = new ArrayList<Place>();
	private static ArrayList<Person> persons = new ArrayList<Person>();
	public boolean isPerson;
	
	public Location(double latitude, double longitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public static void addPerson(Person person) {
		Location.persons.add(person);
	}
	
	public static void addPlace(Place place) {
		Location.places.add(place);
	}

	public static ArrayList<Place> getPlaces() {
		return places;
	}

	public static ArrayList<Person> getPersons() {
		return persons;
	}
	
}
