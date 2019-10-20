package models;

public class Place extends Location{
	
	String name;
	
	public Place(String name, double longitude, double latitude) {
		super(longitude, latitude);
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
