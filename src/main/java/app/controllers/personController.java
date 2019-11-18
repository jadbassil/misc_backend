package app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.models.Person;

@RestController
public class personController {
	
	@PostMapping("/updatePersonLocation")
	public Map<String, String> update(@RequestBody Map<String,Object> payload){
		System.out.println("update...");
		Map<String, String> res = new HashMap<String, String>();
		System.out.println(payload.get("latitude"));
		try {
			int id1 = (int) payload.get("id");
			double latitude1 = (double) payload.get("latitude");
			double longitude1 = (double) payload.get("longitude");
			Person p = Person.getPersons().get(id1);
			System.out.print(p.getName()+": "+p.getLatitude()+"; "+p.getLongitude());
			p.setLatitude(latitude1);
			p.setLongitude(longitude1);
			System.out.print(p.getName()+": "+p.getLatitude()+"; "+p.getLongitude());
			p.setNearestPlace();
			p.setToNearestDirections();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			res.put("success", "false");
			res.put("message", "wrong id");
			return res;
		}
		res.put("success",  "true");
		res.put("message", "person updated");
		return res;
		
	}
}
