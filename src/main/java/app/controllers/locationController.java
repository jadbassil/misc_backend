package app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Location;
import models.MOD;
import models.Person;
import models.Place;

@RestController
@RequestMapping("/locations")
public class locationController {
	
	@RequestMapping("/persons")
	public ArrayList<Person> getPersons(){
		return Location.getPersons();
	}
	
	@RequestMapping(value="/places")
	public ArrayList<Place> getPlaces(){
		System.out.println("places");
		return Location.getPlaces();
	}
	
	@RequestMapping("/")
	public Map<String,ArrayList<Location>> getLocations(){
		Map<String, ArrayList<Location>> response = new HashMap<String, ArrayList<Location>>();
		ArrayList<Location> persons = new ArrayList<Location>();
		for (Person p : Location.getPersons()) {
			persons.add((Location) p);
		}
		ArrayList<Location> places = new ArrayList<Location>();
		for (Place p : Location.getPlaces()) {
			places.add((Location) p);
		}
		response.put("persons", persons);
		response.put("places", places);
		return response;
	}
	
}
