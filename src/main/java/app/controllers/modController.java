package app.controllers;

import java.util.ArrayList;
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
	
	@SuppressWarnings("unchecked")
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
			Property property = mod.matrix.get(Location.getPlaces().indexOf(nearestToPerson)).get(nearestIndex);
			Property p1 = new Property();
			personData.put("name", (String) person.getName());
			personData.put("nearestPlaceIdToPerson", (Integer) person.getNearestPlace().getId());
			p1.setInstructions(((ArrayList<String>) person.getToNearestDirections().getInstructions().clone()));
			p1.getInstructions().addAll(property.getInstructions());
			p1.setDistance(property.getDistance() + person.getToNearestDirections().getDistance());
			p1.setDuration(property.getDuration() + person.getToNearestDirections().getDuration());
			p1.setFrom_latitude(property.getFrom_latitude());
			p1.setFrom_longitude(property.getFrom_longitude());
			p1.setTo_latitude(property.getTo_latitude());
			p1.setTo_longitude(property.getTo_longitude());
			p1.setMode(property.getMode());

			if(nearestToPerson.equals(Location.getPlaces().get(nearestIndex))) {
				p1.getPolyline().addAll(person.getToNearestDirections().getPolyline());;
				personData.put("routes", p1);
			} else {
				p1.setPolyline(property.getPolyline());
				p1.getPolyline().addAll(0, person.getToNearestDirections().getPolyline());
				personData.put("routes", p1);
			}
				
			result.put(person.getId(), personData);
		}
		return result;
	}
	
}
