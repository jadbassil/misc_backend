package app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Location;
import models.MOD;
import models.Person;
import models.Place;
import models.Property;
import utils.Utils;

@RestController
public class modController {
	
	
	@RequestMapping("/nearestPlace")
	public Place getNearest() {
		MOD mod = (MOD) Utils.ReadObjectFromFile(Utils.filepath);
		return mod.nearestLocation();
	}
	
	@RequestMapping("/routesToNearest")
	public Map<String, Property> getRoutesToNearest(){
		MOD mod = (MOD) Utils.ReadObjectFromFile(Utils.filepath);
		Place nearest = mod.nearestLocation();
		Map<String, Property> result = new HashMap<String, Property>();
		int nearestIndex = mod.locations.indexOf(nearest);
		for(int i=0;i<Location.getPersons().size();i++) {
			Person p = (Person) mod.getLocations().get(i);
			result.put(p.getName(), mod.matrix.get(i).get(nearestIndex));
		}
		return result;
	}
	
}
