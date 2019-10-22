package models;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utils.Utils;

public class Test {
	
	

	public static void main(String[] args) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		try (FileReader reader = new FileReader("C:\\Users\\User\\Documents\\M2_IOT\\MISC\\misc_backend\\misc_server\\src\\main\\resources\\data.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray persons = (JSONArray) jsonObject.get("persons");
            for(Object o : persons) {
            	JSONObject person = (JSONObject) o;
            	String name = (String) person.get("name");
            	double longitude = Double.parseDouble((String) person.get("longitude"));
            	double latitude = Double.parseDouble((String) person.get("latitude"));
            	new Person(name,longitude,latitude);
            }
            JSONArray places = (JSONArray) jsonObject.get("places");
            for(Object o : places) {
            	JSONObject place = (JSONObject) o;
            	String name = (String) place.get("name");
            	double longitude = Double.parseDouble((String) place.get("longitude"));
            	double latitude = Double.parseDouble((String) place.get("latitude"));
            	new Place(name,longitude,latitude);
            }
            
//            MOD mod = new MOD();
//            Test.WriteObjectToFile(mod);
            MOD mod = (MOD) Utils.ReadObjectFromFile(Utils.filepath); 
            for (int i = 0; i < mod.locations.size(); i++) {
				ArrayList<Property> p = mod.matrix.get(i);
				for (Property property : p) {
					System.out.print(property.getDistance() + " ");
				}
				System.out.println();
				
			}
            System.out.println(mod.matrix.get(0).get(6).getInstructions());
            boolean e = mod.transitiveClosure();
            while(e)
            	e = mod.transitiveClosure();
            for (int i = 0; i < mod.locations.size(); i++) {
				ArrayList<Property> p = mod.matrix.get(i);
				for (Property property : p) {
					System.out.print(property.getDistance() + " ");
				}
				System.out.println();				
			}
            System.out.println(mod.matrix.get(0).get(6).getInstructions());
            Place place = mod.nearestLocation();
            System.out.println("nearest location: "+place.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
