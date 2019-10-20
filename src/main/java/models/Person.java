package models;

public class Person extends Location {
	
	private String name;
	
	public Person(String name, double longitude, double latitude) {
		super(longitude, latitude);
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
