package models;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		Person p1 = new Person("a",1.0,1.0);
		Person p2 = new Person("b",1.0,1.0);
		Place pl1 = new Place("a",1.0,1.0);
		Place pl2 = new Place("b",1.0,1.0);
		ArrayList<Person> persons = Location.getPersons();
		for(Person p: persons) {
			System.out.println(p.getName() + " " + p.getLongitude()+" "+p.getLatitude());
		}
	}

}
