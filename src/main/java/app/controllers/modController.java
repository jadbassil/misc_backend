package app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.models.Location;
import app.models.MOD;
import app.models.Person;
import app.models.Place;
import app.models.Property;
import utils.Utils;

@RestController
public class modController {
	
	
	@RequestMapping("/nearestPlace")
	public Place getNearest() {
		MOD mod = (MOD) Utils.ReadObjectFromFile(Utils.filepath);
		return mod.nearestLocation();
	}
	
	@RequestMapping("/routesToNearest")
	public Map<Integer, Object> getRoutesToNearest(){
		MOD mod = (MOD) Utils.ReadObjectFromFile(Utils.filepath);
		Map<Integer, Object> result = new HashMap<Integer, Object>();
		int nearestIndex = Location.getPlaces().indexOf(mod.nearestLocation());
//		for(int i=0;i<Location.getPersons().size();i++) {
//			Person p = (Person) mod.getLocations().get(i);
//			result.put(p.getName(), mod.matrix.get(i).get(nearestIndex));
//		}
		for (Person person : Location.getPersons()) {
			Map<String, Object> personData = new HashMap<String, Object>();
			Place nearestToPerson = person.getNearestPlace();
			//System.out.println(nearestToPerson.getName());
			Property property = mod.matrix.get(Location.getPlaces().indexOf(nearestToPerson)).get(nearestIndex);
			property.setInstructions(person.getToNearestDirections().getInstructions() + property.getInstructions());
			property.setDistance(property.getDistance() + person.getToNearestDirections().getDistance());
			property.getRoutes().addAll(0, person.getToNearestDirections().getRoutes());
			personData.put("name", (String) person.getName());
			personData.put("nearestPlaceIdToPerson", (Integer) person.getNearestPlace().getId());
			personData.put("routes", property);
			
			result.put(person.getId(), personData);
			
		}
		return result;
	}
	
}
