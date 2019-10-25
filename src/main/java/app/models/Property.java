package app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class Property implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double from_longitude;
	private double from_latitude;
	private double to_longitude;
	private double to_latitude;
	private String mode;
	private int distance;
	private int duration;
	private ArrayList<Property> routes = new ArrayList<Property>();
	private String instructions;
	private String polyline;
	
	public Property() {}
	
	public Property(Location from, Location to, String mode) {
		this.from_longitude = from.getLongitude();
		this.from_latitude = from.getLatitude();
		this.to_longitude = to.getLongitude();
		this.to_latitude = to.getLatitude();
		this.mode = mode;
		this.polyline = null;
		this.buildProperty();
	}
	
	//Get properties from DirectionsAPI
	private void buildProperty() {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		String url = "https://maps.googleapis.com/maps/api/directions/json?key={key}&origin={origin}&destination={destination}&mode={mode}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("key","AIzaSyB7DBgqiXtpDy4zV_9rlL-3EKf1r1Gql4c");
		params.put("origin", String.valueOf(this.from_latitude)+','+String.valueOf(this.from_longitude));
		params.put("destination", String.valueOf(this.to_latitude)+','+String.valueOf(this.to_longitude));
		params.put("mode", this.mode);
		try {
			HttpEntity<String> response = restTemplate.exchange(
					url,
					HttpMethod.GET, entity, String.class,params);
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.getBody());
			JSONArray routes = (JSONArray) jsonObject.get("routes");
			JSONObject routesData = (JSONObject) routes.get(0);
			JSONArray legs = (JSONArray) routesData.get("legs");
			JSONObject legsData = (JSONObject) legs.get(0);
			JSONObject duration = (JSONObject) legsData.get("duration");
			JSONObject distance = (JSONObject) legsData.get("distance");
			String instructions = "";
			JSONArray steps = (JSONArray) legsData.get("steps");
			// can add more steps details
			for (int i = 0; i < steps.size(); i++) {
				JSONObject step = (JSONObject) steps.get(i);
				instructions += step.get("html_instructions") + "\n";
				Property p = new Property();
				JSONObject start_location = (JSONObject) step.get("start_location");
				JSONObject end_location = (JSONObject) step.get("start_location");
				JSONObject stepDuration = (JSONObject) step.get("duration");
				JSONObject stepDistance = (JSONObject) step.get("distance");
				JSONObject polyline = (JSONObject) step.get("polyline");
				p.setFrom_latitude((double) start_location.get("lat"));
				p.setFrom_longitude((double) start_location.get("lng"));
				p.setTo_latitude((double) end_location.get("lat"));
				p.setTo_longitude((double) end_location.get("lng"));
				p.setDuration(Integer.parseInt(String.valueOf(stepDuration.get("value"))));
				p.setDistance(Integer.parseInt(String.valueOf(stepDistance.get("value"))));
				p.setPolyline((String) polyline.get("points"));
				p.setInstructions(step.get("html_instructions") + "\n");
				p.setMode((String) step.get("travel_mode"));
				this.routes.add(p);
			}
			this.instructions = instructions;
			this.duration = Integer.parseInt(String.valueOf(duration.get("value")));
			this.distance = Integer.parseInt(String.valueOf(distance.get("value")));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String toString() {
		return "Property [from_longitude=" + from_longitude + ", from_latitude=" + from_latitude + ", to_longitude="
				+ to_longitude + ", to_latitude=" + to_latitude + ", mode=" + mode + ", distance=" + distance
				+ ", duration=" + duration + ", routes=" + routes + ", instructions=" + instructions + ", polyline="
				+ polyline + "]";
	}

	public String getPolyline() {
		return polyline;
	}

	public void setPolyline(String polyline) {
		this.polyline = polyline;
	}

	public double getFrom_longitude() {
		return from_longitude;
	}

	public void setFrom_longitude(double from_longitude) {
		this.from_longitude = from_longitude;
	}

	public double getFrom_latitude() {
		return from_latitude;
	}

	public void setFrom_latitude(double from_latitude) {
		this.from_latitude = from_latitude;
	}

	public double getTo_longitude() {
		return to_longitude;
	}

	public void setTo_longitude(double to_longitude) {
		this.to_longitude = to_longitude;
	}

	public double getTo_latitude() {
		return to_latitude;
	}

	public void setTo_latitude(double to_latitude) {
		this.to_latitude = to_latitude;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setRoutes(ArrayList<Property> routes) {
		this.routes = routes;
	}

	public ArrayList<Property> getRoutes() {
		return routes;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	
	
}
